package ejiayou.station.module.dialog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ejiayou.station.module.R
import ejiayou.station.module.adapter.StationAdDialogAdapter
import ejiayou.station.module.databinding.StationEnsdDetailAdDialogBinding
import ejiayou.station.module.model.AdDialogDetailItem
import ejiayou.station.module.model.EPlusItemDto
import ejiayou.uikit.module.dialog.BaseBindDialogFragment
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter
import ejiayou.uikit.module.recyclerview.SpaceItemDecoration
import java.util.ArrayList

/**
 * @author:
 * @created on: 2022/7/14 14:01
 * @description:广告明细
 */
class StationEnsdDetailAdDialog : BaseBindDialogFragment<StationEnsdDetailAdDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.station_ensd_detail_ad_dialog
    }

    private val stationDialogAdapter by lazy { StationAdDialogAdapter() }

    var adDialogDetailItems = ArrayList<AdDialogDetailItem>()

    override fun initialize(view: View, savedInstanceState: Bundle?) {


        adDialogDetailItems.add(AdDialogDetailItem(viewType = 1, title = "活动明细"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text = "加油日", text2 = "前10升享¥7.39/L，超出部分享原价"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text = "农行满立减", text2 = "使用农行银行卡，满200-50"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text = "免费洗车", text2 = "加油满200，赠免费洗车一次"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text = "加油送水", text2 = "加油满150，赠矿泉水一瓶"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 1, title = "油站公告"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text2 = "1、站内支持开票，如有疑问请向加油员询问~"))
        adDialogDetailItems.add(AdDialogDetailItem(viewType = 2, text2 = "2、进入油站后，请前往安全区域使用手机"))
        stationDialogAdapter.addAdapterItemData(adDialogDetailItems)
        binding.stationRecyclerAdDialog.adapter = stationDialogAdapter
        binding.stationRecyclerAdDialog.layoutManager = LinearLayoutManager(requireContext())
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationRecyclerAdDialog.addItemDecoration(spaceItemDecoration)
        binding.stationRecyclerAdDialog.setHasFixedSize(true)
    }
}