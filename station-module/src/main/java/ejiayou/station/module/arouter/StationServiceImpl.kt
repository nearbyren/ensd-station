package ejiayou.station.module.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import ejiayou.station.export.router.StationRouterTable
import ejiayou.station.export.router.service.IStationService

/**
 * @author: lr
 * @created on: 2022/7/16 4:03 下午
 * @description:
 */
@Route(path = StationRouterTable.PATH_SERVICE_STATION)
class StationServiceImpl : IStationService {
    override fun shareBoolean(): Boolean {
        return false
    }

    override fun init(context: Context?) {
        //初始化工作，服务注入时会调用，可忽略
    }
}