package ejiayou.station.module.adapter

import android.view.View
import androidx.core.view.isVisible
import ejiayou.common.module.exts.toColor
import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailConfirmBinding
import ejiayou.station.module.databinding.StationEnsdDetailCouponItemBinding
import ejiayou.station.module.databinding.StationEnsdDetailEplusBuyItemBinding
import ejiayou.station.module.model.CalculateDiscountDto
import ejiayou.station.module.model.CouponDto
import ejiayou.station.module.model.EPlusItemDto
import ejiayou.uikit.module.recyclerview.BaseBindRecyclerAdapter


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description:
 */
class StationEplusAdapter : BaseBindRecyclerAdapter<StationEnsdDetailEplusBuyItemBinding, EPlusItemDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_eplus_buy_item
    }

    override fun onBindingItem(binding: StationEnsdDetailEplusBuyItemBinding, item: EPlusItemDto, position: Int) {
        binding.stationIvEplusIcon.setBackgroundResource(item.icon)
        binding.stationTvEplusTitle.text = item.text
        if (item.isCheck) {
            binding.stationIvEplusCheck.setBackgroundResource(R.drawable.station_ensd_detail_eplus_select)
        } else {
            binding.stationIvEplusCheck.setBackgroundResource(R.drawable.station_ensd_detail_eplus_un_select)
        }
    }


}