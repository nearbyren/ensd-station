package ejiayou.station

import android.app.Application
import ejiayou.common.module.http.CorHttp

/**
 * @author:
 * @created on: 2022/7/11 16:28
 * @description:
 */
class StationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CorHttp.getInstance().init(this)
    }
}