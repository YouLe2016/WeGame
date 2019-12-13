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

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.wyl.wegame.bean.GirlApi
import com.wyl.wegame.bean.GirlItem

/**
 * 项目名称：WeGame
 * 创建人：江心才子
 * 创建时间：2019-12-12 16:12
 * 内容描述：
 * 修改说明：
 */
class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<GirlItem>>()

    val photoListLive: LiveData<List<GirlItem>>
        get() = _photoListLive

    private var page = 1

    var isRefresh: Boolean = true

    fun fetchData(isRefresh: Boolean) {
        this.isRefresh = isRefresh
        if (isRefresh) page = 1
        OkGo.get<String>("${GirlUrl}20/$page")
            .tag(this)
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>) {
                    page++
                    _photoListLive.value =
                        Gson().fromJson<GirlApi>(response.body(), GirlApi::class.java).results
                }
            })
    }

}