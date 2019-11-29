package com.wyl.wegame.ui.magicnumber

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.wyl.wegame.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        loadData()

        btYes.setOnClickListener {
            viewModel.addNumber()
            nextStep()
        }

        btNo.setOnClickListener { nextStep() }
    }

    private fun nextStep() {
        if (viewModel.hasNextPage()) {
            animateImage3()
        } else {
            findNavController().navigate(
                R.id.action_mainFragment_to_resultFragment,
                bundleOf(
                    ResultFragment.Result to viewModel.answer()
                )
            )
        }
    }

    private fun loadData() {
        message.text = getString(R.string.magic_tips, viewModel.index + 1)
        imageView.setImageResource(viewModel.data().second)
    }

    private fun animateImage3() {
        var flag = true
        ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f),
            PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f),
            PropertyValuesHolder.ofFloat("rotationY", 0f, 720f, 0f)
        ).setDuration(1800).apply {
            addUpdateListener {
                //                Log.d("look", "animatedFraction = ${it.animatedFraction}")
                if (it.animatedFraction > 0.5f && flag) {
                    flag = false
                    loadData()
                }
            }
            start()
        }
    }

//    private fun animateImage() {
//        imageView.animate()
//            .scaleX(0f).scaleY(0f)
//            .rotationY(720f)
//            .setDuration(1200)
//            .setUpdateListener {
//            }
//            .withEndAction {
//
//            }
//    }
//
//    private fun animateImage2() {
//        imageView.animate()
//            .scaleX(1f).scaleY(1f)
//            .rotationY(0f)
//            .setDuration(1200)
//            .setUpdateListener {
//            }
//            .withEndAction {
//
//            }
//    }

}
