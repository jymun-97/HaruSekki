package com.jymun.harusekki.ui.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.FragmentMenuBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.extensions.getFragment
import com.jymun.harusekki.ui.extensions.toCalendarDay
import com.jymun.harusekki.ui.extensions.toLocalDate
import com.jymun.harusekki.util.resources.ResourcesProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : BaseFragment<MenuViewModel, FragmentMenuBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val args by navArgs<MenuFragmentArgs>()

    private lateinit var menuPageAdapter: MenuPageAdapter
    private lateinit var curDate: LocalDate
    private var curPosition = 0
    private var copiedDate: LocalDate? = null

    override val viewModel: MenuViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMenuBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuPageAdapter = MenuPageAdapter(requireActivity()) {
            viewModel.addDate(curDate.toCalendarDay())
        }
        curDate = args.date.toLocalDate() ?: LocalDate.now()
        curPosition =
            menuPageAdapter.defaultPosition + ChronoUnit.DAYS.between(LocalDate.now(), curDate)
                .toInt()

        initMenuPager()
        initCalendarView()
        initDeleteMenuButton()
        initCopyMenuButton()
        initPasteMenuButton()

        viewModel.loadDate(curDate.year, curDate.monthValue)
        viewModel.dateSet.observe(viewLifecycleOwner) {
            it ?: return@observe

            binding.calendarView.removeDecorators()
            binding.calendarView.addDecorator(object : DayViewDecorator {
                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    return it.contains(day)
                }

                override fun decorate(view: DayViewFacade?) {
                    view?.addSpan(
                        DotSpan(5f, resourcesProvider.getColor(R.color.app_signature_bright))
                    )
                }
            })
        }
    }

    override fun setUpBinding() = binding.apply {
        viewModel = this@MenuFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun initMenuPager() = binding.menuViewPager.apply {
        adapter = menuPageAdapter
        setCurrentItem(curPosition, false)

        registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                curDate = curDate.plusDays(position - curPosition.toLong())
                curPosition = position

                binding.calendarView.apply {
                    selectedDate = curDate.toCalendarDay()
                    setCurrentDate(curDate.toCalendarDay(), true)
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun initCalendarView() = binding.calendarView.apply {
        selectedDate = curDate.toCalendarDay()
        setCurrentDate(curDate.toCalendarDay(), false)

        setOnDateChangedListener { _, calendarDate, _ ->
            val newDate = calendarDate.toLocalDate()
            binding.menuViewPager.setCurrentItem(
                (curPosition + ChronoUnit.DAYS.between(curDate, newDate)).toInt(),
                false
            )
            curDate = newDate
            curPosition = binding.menuViewPager.currentItem
        }
        setOnMonthChangedListener { _, date ->
            viewModel.loadDate(date.year, date.month + 1)
        }
    }

    private fun initDeleteMenuButton() = binding.deleteMenuButton.setOnClickListener {
        AlertDialog.Builder(requireActivity())
            .setTitle(resourcesProvider.getString(R.string.delete_menu))
            .setMessage("${curDate.year}년 ${curDate.monthValue}월 ${curDate.dayOfMonth}일자 식단을 모두 삭제하시겠습니까?")
            .setPositiveButton(resourcesProvider.getString(R.string.okay)) { _, _ ->
                binding.menuViewPager.getFragment(
                    curPosition,
                    requireActivity().supportFragmentManager
                )?.let {
                    (it as MenuPageFragment).deleteMenu()
                    viewModel.deleteDate(curDate.toCalendarDay())
                }
            }
            .setNegativeButton(resourcesProvider.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun initCopyMenuButton() = binding.copyMenuButton.apply {
        visibility = View.VISIBLE
        setOnClickListener {
            visibility = View.GONE
            binding.pasteMenuButton.visibility = View.VISIBLE
            copiedDate = curDate

            Toast.makeText(
                requireActivity(),
                resourcesProvider.getString(R.string.toast_copy_menu),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initPasteMenuButton() = binding.pasteMenuButton.apply {
        visibility = View.GONE
        setOnClickListener {
            visibility = View.GONE
            binding.copyMenuButton.visibility = View.VISIBLE
            copiedDate?.let { copiedDate ->
                binding.menuViewPager.getFragment(
                    curPosition,
                    requireActivity().supportFragmentManager
                )?.let {
                    (it as MenuPageFragment).pasteMenu(copiedDate)
                    viewModel.addDate(curDate.toCalendarDay())
                }
            }
            copiedDate = null
        }
    }
}