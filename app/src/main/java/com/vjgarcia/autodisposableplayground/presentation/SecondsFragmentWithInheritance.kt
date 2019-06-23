package com.vjgarcia.autodisposableplayground.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vjgarcia.autodisposableplayground.R
import kotlinx.android.synthetic.main.fragment_autodisposable_sample.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondsFragmentWithInheritance : Fragment() {

    private val viewModel by viewModel<SecondsViewModelWithInheritance>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_autodisposable_sample, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = getString(R.string.view_model_with_inheritance_sample)
        forceViewModelStartEvent.setOnClickListener { viewModel.start() }
        forceViewModelStopEvent.setOnClickListener { viewModel.stop() }
        bindViewModel()
        viewModel.start()
    }

    override fun onDestroyView() {
        viewModel.stop()
        super.onDestroyView()
    }

    private fun bindViewModel() {
        viewModel.seconds.observe(this::getLifecycle) { secondsValue ->
            seconds.text = secondsValue.toString()
        }
        viewModel.isDisposed.observe(::getLifecycle) { isDisposed ->
            if (isDisposed) {
                observableDisposed.visibility = View.VISIBLE
                forceViewModelStartEvent.isEnabled = true
                forceViewModelStopEvent.isEnabled = false
            } else {
                observableDisposed.visibility = View.GONE
                forceViewModelStartEvent.isEnabled = false
                forceViewModelStopEvent.isEnabled = true
            }
        }
    }

    companion object {
        fun instance(): Fragment = SecondsFragmentWithInheritance()
    }
}