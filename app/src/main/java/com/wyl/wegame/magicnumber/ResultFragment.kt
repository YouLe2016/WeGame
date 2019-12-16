package com.wyl.wegame.magicnumber


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wyl.wegame.R
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.main_fragment.message

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {
    companion object {
        const val Result = "Result"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        message.alpha = 0f

        message.animate()
            .alpha(1f)
            .setDuration(1800)
            .withEndAction {
                message.postOnAnimationDelayed({
                    val string = arguments?.getString(Result) ?: "未知错误了"
                    val scale = if (string.length < 4) 2f else 1.2f

                    message.text = string
                    message.animate()
                        .scaleX(scale)
                        .scaleY(scale)
                        .setInterpolator(OvershootInterpolator())
                        .withEndAction { restart?.visibility = View.VISIBLE }
                }, 3000)
            }

        restart.setOnClickListener { findNavController().navigateUp() }

        message.setOnClickListener { findNavController().navigate(R.id.action_resultFragment_to_nav_gallery) }
    }
}
