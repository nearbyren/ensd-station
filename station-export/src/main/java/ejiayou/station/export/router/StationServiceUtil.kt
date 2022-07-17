package ejiayou.station.export.router

import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author: lr
 * @created on: 2022/7/16 4:26 下午
 * @description: 提供启动activity  service 等动作
 */
open class StationServiceUtil {


    companion object {


        fun navigateStationDetailPage() {
            ARouter.getInstance().build(StationRouterTable.PATH_RECOMMEND)
                .navigation()
        }

        fun navigate0086(): Int {
            return 10086

        }
    }
}