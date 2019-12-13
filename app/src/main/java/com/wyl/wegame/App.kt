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
package com.wyl.wegame

import android.app.Application
import com.lzy.okgo.OkGo
import com.tencent.bugly.Bugly

/**
 *
 * INSTALL_FAILED_UPDATE_INCOMPATIBLE
 * 项目名称：WeGame
 * 创建人：江心才子
 * 创建时间：2019-12-12 14:32
 * 内容描述：
 * 修改说明：
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Bugly.init(applicationContext, "7cf3a4a126", BuildConfig.DEBUG)

        OkGo.getInstance().init(this)
    }
}