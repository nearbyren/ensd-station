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
 * @description: 100 200 300
 */
class StationCouponAdapter : BaseBindRecyclerAdapter<StationEnsdDetailCouponItemBinding, CouponDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_coupon_item
    }

    override fun onBindingItem(binding: StationEnsdDetailCouponItemBinding, item: CouponDto, position: Int) {
        //标记券深色  浅色
        if (item.isCheck && item.isCoupon) {
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_select)
            binding.stationIvIsCoupon.setBackgroundResource(R.drawable.station_ensd_detail_coupon_is_check)
            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.orange.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.orange.toColor(binding.stationTvCouponPrice.context))
        } else if (item.isCheck && !item.isCoupon) {
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_select)
            binding.stationIvIsCoupon.isVisible = false
            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.orange.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.background_color.toColor(binding.stationTvCouponPrice.context))
        } else if (!item.isCheck && item.isCoupon) {
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_select)
            binding.stationIvIsCoupon.setBackgroundResource(R.drawable.station_ensd_detail_coupon_not_check)
            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.orange.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.background_color.toColor(binding.stationTvCouponPrice.context))
        } else if (!item.isCheck && !item.isCoupon) {
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_un_select)
            binding.stationIvIsCoupon.isVisible = false
            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.background_color.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.background_color.toColor(binding.stationTvCouponPrice.context))

        }
        //优惠券金额
        binding.stationTvCouponPrice.text = item.discount
        //价格
        binding.stationTvMarkedPrice.text = item.markPrice

    }

}