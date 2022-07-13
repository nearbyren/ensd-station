package ejiayou.station

import android.app.Application
import ejiayou.common.module.http.FlyHttp

/**
 * @author:
 * @created on: 2022/7/11 16:28
 * @description:
 */
class app : Application() {
    override fun onCreate() {
        super.onCreate()
        FlyHttp.getInstance().init(this)
    }
}