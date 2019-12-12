package com.wyl.wegame.magicnumber

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.REVERSE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
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

    private fun animateImage3() {
        ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            PropertyValuesHolder.ofFloat("scaleX", 1f, 0f),
            PropertyValuesHolder.ofFloat("scaleY", 1f, 0f),
            PropertyValuesHolder.ofFloat("rotationY", 0f, 720f)
        ).apply {
            duration = 900
            repeatMode = REVERSE
            repeatCount = 1
            addListener(onRepeat = {
                loadData()
            }, onStart = {
                btYes.isClickable = false
                btNo.isClickable = false
            }, onEnd = {
                btYes.isClickable = true
                btNo.isClickable = true
            })
            start()
        }
    }

    private fun loadData() {
        message.text = getString(R.string.magic_tips, viewModel.index + 1)
        imageView.setImageResource(viewModel.data().second)
    }


   /* private fun animateImage() {
        imageView.animate()
            .scaleX(0f).scaleY(0f)
            .rotationY(720f)
            .setDuration(1200)
            .setUpdateListener {
            }
            .withEndAction {

            }
    }

    private fun animateImage2() {
        imageView.animate()
            .scaleX(1f).scaleY(1f)
            .rotationY(0f)
            .setDuration(1200)
            .setUpdateListener {
            }
            .withEndAction {

            }
    }*/

}
