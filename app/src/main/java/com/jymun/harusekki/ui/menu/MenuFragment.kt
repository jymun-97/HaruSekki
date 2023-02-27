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
import com.jymun.harusekki.ui.extensions.toCalendarDay
import com.jymun.harusekki.ui.extensions.toLocalDate
import com.jymun.harusekki.util.resources.ResourcesProvider
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

    override val viewModel: MenuViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMenuBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuPageAdapter = MenuPageAdapter(requireActivity())
        curDate = args.date.toLocalDate() ?: LocalDate.now()
        curPosition =
            menuPageAdapter.defaultPosition + ChronoUnit.DAYS.between(LocalDate.now(), curDate)
                .toInt()

        initMenuPager()
        initCalendarView()
        initDeleteMenuButton()
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
    }

    private fun initDeleteMenuButton() = binding.deleteMenuButton.setOnClickListener {
        AlertDialog.Builder(requireActivity())
            .setTitle(resourcesProvider.getString(R.string.delete_menu))
            .setMessage("${curDate.year}년 ${curDate.monthValue}월 ${curDate.dayOfMonth}일자 식단을 모두 삭제하시겠습니까?")
            .setPositiveButton(resourcesProvider.getString(R.string.okay)) { _, _ ->
                requireActivity().supportFragmentManager.findFragmentByTag("f$curPosition")?.let {
                    (it as MenuPageFragment).deleteMenu()
                }
            }
            .setNegativeButton(resourcesProvider.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}