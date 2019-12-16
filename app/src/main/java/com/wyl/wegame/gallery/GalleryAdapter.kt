/**
 * created by 江心才子, 2019/12/12 0012
 * Copyright (c) 2019, 270628297@qq.com All Rights Reserved.
 * #                   *********                            #
 * #                  ************                          #
 * #                  *************                         #
 * #                 **  ***********                        #
 * #                ***  ****** *****                       #
 * #                *** *******   ****                      #
 * #               ***  ********** ****                     #
 * #              ****  *********** ****                    #
 * #            *****   ***********  *****                  #
 * #           ******   *** ********   *****                #
 * #           *****   ***   ********   ******              #
 * #          ******   ***  ***********   ******            #
 * #         ******   **** **************  ******           #
 * #        *******  ********************* *******          #
 * #        *******  ******************************         #
 * #       *******  ****** ***************** *******        #
 * #       *******  ****** ****** *********   ******        #
 * #       *******    **  ******   ******     ******        #
 * #       *******        ******    *****     *****         #
 * #        ******        *****     *****     ****          #
 * #         *****        ****      *****     ***           #
 * #          *****       ***        ***      *             #
 * #            **       ****        ****                   #
 */
package com.wyl.wegame.gallery

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyl.wegame.R
import com.wyl.wegame.bean.GirlItem
import kotlinx.android.synthetic.main.gallery_cell.view.*
import me.panpf.sketch.decode.ImageAttrs
import me.panpf.sketch.request.CancelCause
import me.panpf.sketch.request.DisplayListener
import me.panpf.sketch.request.ErrorCause
import me.panpf.sketch.request.ImageFrom
import net.moyokoo.diooto.Diooto
import net.moyokoo.diooto.config.DiootoConfig
import net.moyokoo.diooto.interfaces.CircleIndexIndicator
import net.moyokoo.diooto.interfaces.DefaultCircleProgress

/**
 * 项目名称：WeGame
 * 创建人：江心才子
 * 创建时间：2019-12-12 17:47
 * 内容描述：
 * 修改说明：
 */
class GalleryAdapter : ListAdapter<GirlItem, GalleryAdapter.ViewHolder>(DiffCallback) {
    private lateinit var mRecyclerView: RecyclerView
    private var mList: Array<String>? = null

    companion object DiffCallback : DiffUtil.ItemCallback<GirlItem>() {
        override fun areItemsTheSame(oldItem: GirlItem, newItem: GirlItem): Boolean {
            return newItem._id == oldItem._id
        }

        override fun areContentsTheSame(oldItem: GirlItem, newItem: GirlItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.gallery_cell,
            parent,
            false
        )

        val holder = ViewHolder(view)

        view.setOnClickListener {
            //            view.findNavController().navigate(
//                R.id.action_galleryFragment_to_photoFragment,
//                bundleOf(Girl to getItem(holder.adapterPosition))
//            )

            Diooto(parent.context)
                .urls(mList)
                // 图片或者视频
                .type(DiootoConfig.PHOTO)
                //当前的Activity是否为沉浸式,默认为false
//                .immersive(true)
                .fullscreen(true)
                //点击的位置 如果你的RecyclerView有头部View  则使用 .position(holder.getAdapterPosition(),headSize) headSize为头部布局数量
                .position(holder.adapterPosition)
                //可以传recylcerview自动识别(需要传在item布局中的viewId)  也可以手动传view数组
                .views(mRecyclerView, R.id.imageView2)
                //设置选择器 默认CircleIndexIndicator  可实现IIndicator接口自定义
                .setIndicator(CircleIndexIndicator())
                .indicatorVisibility(View.GONE)
                //设置进度条样式  默认DefaultProgress 可实现IProgress接口自定义
                .setProgress(DefaultCircleProgress())
                //在显示原图之前显示的图片  如果你列表使用Glide加载  这里也使用Glide加载
                .loadPhotoBeforeShowBigImage { sketchImageView, position ->
                    sketchImageView.displayImage(getItem(position).url)
                }
                .start()

        }

        return holder
    }


    override fun submitList(list: MutableList<GirlItem>?) {
        super.submitList(list)
        mList = list?.map { it.url }?.toTypedArray()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 这个闪烁控件可以设置 闪烁时间 颜色 角度……
        val item = getItem(position)
        holder.imageView.apply {
            options.setLoadingImage(R.drawable.ic_photo_gray_24dp)
            displayListener = object : DisplayListener {
                override fun onStarted() {
                    holder.shimmerLayout.startShimmerAnimation()
                }

                override fun onCanceled(cause: CancelCause) {

                }

                override fun onError(cause: ErrorCause) {
                    Log.d("TAG", cause.name + ": " + item.url)
                    holder.shimmerLayout.stopShimmerAnimation()
                }

                override fun onCompleted(
                    drawable: Drawable,
                    imageFrom: ImageFrom,
                    imageAttrs: ImageAttrs
                ) {
                    holder.shimmerLayout.stopShimmerAnimation()
                }

            }
            displayImage(item.url)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shimmerLayout = itemView.shimmerLayout!!
        val imageView = itemView.imageView2!!
    }

}