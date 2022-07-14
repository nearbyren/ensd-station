package ejiayou.station.module.adapter

import android.view.View
import androidx.core.view.isVisible
import ejiayou.common.module.exts.toColor
import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailCalculateDiscountBinding
import ejiayou.station.module.databinding.StationEnsdDetailCalculateDiscountItemBinding
import ejiayou.station.module.databinding.StationEnsdDetailConfirmBinding
import ejiayou.station.module.databinding.StationEnsdDetailCouponItemBinding
import ejiayou.station.module.model.AdItemDto
import ejiayou.station.module.model.CalculateDiscountDto
import ejiayou.station.module.model.CouponDto
import ejiayou.uikit.module.recyclerview.BaseBindRecyclerAdapter


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description:
 */
class StationCalculeDiscountAdapter : BaseBindRecyclerAdapter<StationEnsdDetailCalculateDiscountItemBinding, CalculateDiscountDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_calculate_discount_item
    }

    override fun onBindingItem(binding: StationEnsdDetailCalculateDiscountItemBinding, item: CalculateDiscountDto, position: Int) {
        binding.stationIvType.setBackgroundResource(item.icon)
        binding.stationTvContent.text = item.text
        binding.stationTvCalDisPrice.text = item.discount
        if (item.icon2 > 0) {
            binding.stationIvPlus.setBackgroundResource(item.icon2)

        }
    }

}