package ejiayou.station.export.router

import com.alibaba.android.arouter.launcher.ARouter
import ejiayou.station.export.router.service.IStationService

/**
 * @author: lr
 * @created on: 2022/7/16 4:26 下午
 * @description: 提供启动activity  service 等动作
 */
open class StationServiceUtil {


    companion object {


        fun navigateStationDetailPage() {
            ARouter.getInstance().build(StationRouterTable.PATH_STATION_UI_DETAIL)
                .withString("key1", "哈哈1")
                .withString("key2", "哈哈2").navigation()
        }

        fun getService(): IStationService? {

            var service =
                ARouter.getInstance().build(StationRouterTable.PATH_SERVICE_STATION).navigation()
            if (service is IStationService)
                return service
            return null
        }
    }


}