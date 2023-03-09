package com.jymun.harusekki.ui.memo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.databinding.FragmentMemoBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.custom_view.OnMemoChangedListener
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MemoFragment : BaseFragment<MemoViewModel, FragmentMemoBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider
    private lateinit var memoAdapter: ModelRecyclerAdapter<Memo>
    private var onMemoTextChangedResult: ((List<Ingredient>) -> Unit)? = null

    override val viewModel: MemoViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMemoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMemoSeekbar()
        initMemoRecyclerView()
        initCheckAllButton()

        viewModel.loadAllMemo()
        viewModel.ingredientList.observe(viewLifecycleOwner) {
            if (it != null && onMemoTextChangedResult != null) {
                onMemoTextChangedResult!!(it)
            }
        }
    }

    override fun setUpBinding() = binding.apply {
        viewModel = this@MemoFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun initMemoSeekbar() = binding.memoSeekbar.apply {
        isEnabled = false
    }

    private fun initCheckAllButton() = binding.checkAllButton.apply {
        setOnClickListener {

        }
    }

    private fun initMemoRecyclerView() = binding.memoRecyclerView.apply {
        layoutManager = GridLayoutManager(requireActivity(), 2, VERTICAL, false)
        adapter = ModelRecyclerAdapter<Memo>(resourcesProvider).also {
            memoAdapter = it
            it.addAdapterListener(object : MemoAdapterListener {
                override val onMemoChangedListener: OnMemoChangedListener
                    get() = memoChangedListener
            })
        }
    }

    private val memoChangedListener = object : OnMemoChangedListener {
        override fun onMemoCheckedChanged(memo: Memo) {
            viewModel.insertMemo(memo)
        }

        override fun onMemoUpdated(memo: Memo) {
            viewModel.insertMemo(memo)
        }

        override fun onMemoDeleted(memo: Memo) {
            viewModel.deleteMemo(memo)
        }

        override fun onMemoTextChanged(
            newText: String,
            onSearchResult: (List<Ingredient>) -> Unit
        ) {
            if (newText.isEmpty()) return

            onMemoTextChangedResult = onSearchResult
            viewModel.searchIngredient(newText)
        }
    }
}