package com.vjgarcia.autodisposableplayground.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AutoDisposablePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> SecondsFragmentWithComposition.instance()
        1 -> SecondsFragmentWithInheritance.instance()
        else -> throw IllegalArgumentException("There are only $FRAGMENTS_COUNT fragments available on the pager")
    }

    override fun getCount(): Int = FRAGMENTS_COUNT

    private companion object {
        const val FRAGMENTS_COUNT = 2
    }
}