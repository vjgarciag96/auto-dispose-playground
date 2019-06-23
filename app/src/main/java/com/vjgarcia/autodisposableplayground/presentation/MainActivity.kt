package com.vjgarcia.autodisposableplayground.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vjgarcia.autodisposableplayground.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val autoDisposablePagerAdapter by inject<AutoDisposablePagerAdapter> { parametersOf(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = autoDisposablePagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.text = "VM composition"
        tabLayout.getTabAt(1)?.text = "VM inheritance"
    }
}
