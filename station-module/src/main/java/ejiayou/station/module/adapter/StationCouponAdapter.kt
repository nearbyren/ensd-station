package ejiayou.station.module.adapter

import android.view.View
import androidx.core.view.isVisible
import ejiayou.common.module.exts.toColor
import ejiayou.station.module.R
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
class StationCouponAdapter : BaseBindRecyclerAdapter<StationEnsdDetailCouponItemBinding, CouponDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_coupon_item
    }

    override fun onBindingItem(binding: StationEnsdDetailCouponItemBinding, item: CouponDto, position: Int) {


    }

}