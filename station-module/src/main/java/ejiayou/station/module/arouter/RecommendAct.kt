package ejiayou.station.module.arouter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import ejiayou.common.ui.BaseActivityKot
import ejiayou.station.export.router.StationRouterTable
import ejiayou.station.module.R
import kotlinx.android.synthetic.main.station_recommend_activity.*

/**
 * @author:
 * @created on: 2022/3/12 10:55
 * @description:
 */
@Route(path = "/station/Recommend")
class RecommendAct : BaseActivityKot() {

    @Autowired(name = "key1")
    lateinit var key1: String

    @Autowired(name = "key2")
    lateinit var key2: String
    override fun layoutRes(): Int {
        return R.layout.station_recommend_activity
    }

    override fun layoutView(): View? {
      return null
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }

    override fun showEmptyView(isShow: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showLoadingView(isShow: Boolean) {
        TODO("Not yet implemented")
    }

    override fun initialize(savedInstanceState: Bundle?) {
        station_btn.text = "我是分享module key = ${key1} - ${key2}"
    }
}