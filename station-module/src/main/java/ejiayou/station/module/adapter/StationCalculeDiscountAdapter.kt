package ejiayou.station.module.adapter

import android.view.View
import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailCalculateDiscountItemBinding
import ejiayou.station.module.model.CalculateDiscountDto
import ejiayou.uikit.module.pxToSpF
import ejiayou.uikit.module.recyclerview.BaseBindRecyclerAdapter
import ejiayou.uikit.module.spToPxF


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description: 最优折扣方案
 */
class StationCalculeDiscountAdapter : BaseBindRecyclerAdapter<StationEnsdDetailCalculateDiscountItemBinding, CalculateDiscountDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_calculate_discount_item
    }

    override fun onBindingItem(binding: StationEnsdDetailCalculateDiscountItemBinding, item: CalculateDiscountDto, position: Int) {
        binding.stationTvContent.textSize = item.size.toFloat()
        binding.stationIvType.setBackgroundResource(item.icon)
        binding.stationTvContent.text = item.text
        binding.stationTvCalDisPrice.text = item.discount

        if (item.icon2 > 0) {
            binding.stationIvPlus.setBackgroundResource(item.icon2)
            binding.stationIvRight.visibility = View.VISIBLE
        }
    }

}