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

/**
 * 项目名称：WeGame
 * 创建人：江心才子
 * 创建时间：2019-12-12 17:47
 * 内容描述：
 * 修改说明：
 */
class GalleryAdapter : ListAdapter<GirlItem, GalleryAdapter.ViewHolder>(DiffCallback) {

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

        }

        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 这个闪烁控件可以设置 闪烁时间 颜色 角度……
        val item = getItem(position)
        holder.imageView.apply {
            options.setLoadingImage(R.drawable.ic_insert_photo_black_48dp)
            displayListener = object : DisplayListener {
                override fun onStarted() {
                    holder.shimmerLayout.startShimmerAnimation()
                }

                override fun onCanceled(cause: CancelCause) {

                }

                override fun onError(cause: ErrorCause) {
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
        val shimmerLayout = itemView.shimmerLayout
        val imageView = itemView.imageView2
    }

}