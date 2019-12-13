package com.wyl.wegame.gallery


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyl.wegame.R
import com.wyl.wegame.bean.GirlItem
import kotlinx.android.synthetic.main.fragment_photo.*
import me.panpf.sketch.decode.ImageAttrs
import me.panpf.sketch.request.CancelCause
import me.panpf.sketch.request.DisplayListener
import me.panpf.sketch.request.ErrorCause
import me.panpf.sketch.request.ImageFrom

/**
 * A simple [Fragment] subclass.
 */
class PhotoFragment : Fragment() {
    companion object {
        const val Girl = "girl"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getParcelable<GirlItem>(Girl)?.let {
            imageView3.apply {
                isZoomEnabled = true

                options.setLoadingImage(R.drawable.ic_photo_gray_24dp)
                displayListener = object : DisplayListener {
                    override fun onStarted() {
                        shimmerLayout.startShimmerAnimation()
                    }

                    override fun onCanceled(cause: CancelCause) {

                    }

                    override fun onError(cause: ErrorCause) {
                        Log.d("TAG", cause.name + ": " + it.url)
                        shimmerLayout.stopShimmerAnimation()
                    }

                    override fun onCompleted(
                        drawable: Drawable,
                        imageFrom: ImageFrom,
                        imageAttrs: ImageAttrs
                    ) {
                        shimmerLayout.stopShimmerAnimation()
                    }

                }
                displayImage(it.url)
            }

        }
    }

}
