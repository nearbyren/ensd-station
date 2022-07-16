package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import ejiayou.common.module.base.BaseAppBVMActivity
import ejiayou.station.export.router.StationRouterTable
import ejiayou.station.module.databinding.StationActivity2Binding
import kotlinx.android.synthetic.main.station_activity2.*

/**
 * @author:
 * @created on: 2022/7/8 13:51
 * @description:
 */
@Route(path = StationRouterTable.PATH_STATION_UI_DETAIL)
class EnsdStationDetailAct2 : BaseAppBVMActivity<StationActivity2Binding, TestViewModel>() {


    override fun layoutRes(): Int {
        return R.layout.station_activity2
    }

    override fun layoutViewGroup(): ViewGroup? {
       return null
    }

    override fun layoutView(): View? {
        return null
    }

    override fun createViewModel(): TestViewModel {
        return TestViewModel()
    }

    override fun initialize(savedInstanceState: Bundle?) {
        val pagerItems = FragmentPagerItems.with(applicationContext)
            .add("A", AFragmentMessage::class.java)
            .add("AA", BFragmentMessage::class.java).create()
        stationViewPager.offscreenPageLimit = pagerItems.size
        val adapter = FragmentPagerItemAdapter(supportFragmentManager, pagerItems)
        stationViewPager.adapter = adapter
        stationViewPager.setScanScroll(true)
        stationSmartTabLayout.setViewPager(stationViewPager)
    }

}


