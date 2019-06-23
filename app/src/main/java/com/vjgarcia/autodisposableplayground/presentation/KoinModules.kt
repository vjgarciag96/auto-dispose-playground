package com.vjgarcia.autodisposableplayground.presentation

import androidx.fragment.app.FragmentManager
import com.vjgarcia.autodisposableplayground.autodispose.ViewModelScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val secondsPresentationModulew = module {
    factory { (fragmentManager: FragmentManager) -> AutoDisposablePagerAdapter(fragmentManager) }
    factory { ViewModelScope() }
    viewModel {
        SecondsViewModelWithComposition(get(), androidApplication())
    }
    viewModel {
        SecondsViewModelWithInheritance(androidApplication())
    }
}