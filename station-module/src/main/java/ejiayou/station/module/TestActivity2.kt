package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import ejiayou.common.module.base.BaseAppBindActivity
import ejiayou.station.module.databinding.StationActivityBinding

/**
 * @author:
 * @created on: 2022/7/8 13:51
 * @description:
 */
class TestActivity2 : BaseAppBindActivity<StationActivityBinding>() {
    override fun initialize(savedInstanceState: Bundle?) {
    }

    override fun layoutRes(): Int {
        return R.layout.station_activity
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }

    override fun layoutView(): View? {
        return null
    }


}


