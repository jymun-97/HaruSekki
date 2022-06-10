package com.harusekki.jymun.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harusekki.jymun.R
import com.harusekki.jymun.data.model.Destination
import com.harusekki.jymun.data.model.Shortcut
import com.harusekki.jymun.databinding.ItemShortcutBinding

class ShortcutAdapter(
    private val shortcutGoButtonClicked: (Shortcut) -> Unit
) : RecyclerView.Adapter<ShortcutAdapter.ViewHolder>() {

    private val shortcutList by lazy {
        arrayListOf(
            Shortcut(
                title = "오늘의 식단",
                subTitle = "오늘의 식단 메뉴를 확인하세요!",
                image = R.drawable.img_shortcut_menu,
                destination = Destination.MENU
            ),
            Shortcut(
                title = "장보기 메모",
                subTitle = "필요한 재료를 쉽게 작성하세요!",
                image = R.drawable.img_shortcut_memo,
                destination = Destination.MEMO
            ),
            Shortcut(
                title = "나의 냉장고",
                subTitle = "나의 냉장고 속 재료를 관리하세요!",
                image = R.drawable.img_shortcut_refrigerator,
                destination = Destination.REFRIGERATOR
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortcutAdapter.ViewHolder {
        return ViewHolder(
            ItemShortcutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShortcutAdapter.ViewHolder, position: Int) {
        holder.bind(shortcutList[position])
    }

    override fun getItemCount(): Int {
        return shortcutList.size
    }

    inner class ViewHolder(
        private val binding: ItemShortcutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shortcut: Shortcut) {
            binding.shortcut = shortcut
            binding.goButton.setOnClickListener {
                shortcutGoButtonClicked(shortcut)
            }
        }
    }
}