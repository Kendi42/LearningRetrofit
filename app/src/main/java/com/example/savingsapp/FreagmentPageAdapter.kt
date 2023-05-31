package com.example.savingsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FreagmentPageAdapter
    (
    fragmentManager: FragmentManager,
    lifecycle:Lifecycle
    ): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position==0)
            savings()
        else
            transactions()
    }
}
