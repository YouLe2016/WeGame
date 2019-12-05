package com.wyl.wegame


import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        animationView.apply {
            addAnimatorListener(
                object : Animator.AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                        println("xxx 222222")
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        println("xxx 44444444444")
                        startActivity(
                            Intent(requireContext(), MainActivity::class.java)
                        )
                        activity?.onBackPressed()
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        println("xxx 333333")
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        println("xxx 111111")
                    }
                }
            )
            setAnimation(R.raw.start)
            repeatCount = 1
            playAnimation()
        }
    }
}
