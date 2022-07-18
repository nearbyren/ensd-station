package ejiayou.station.module.adapter

import androidx.core.view.isVisible
import ejiayou.common.module.exts.toColor
import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailCouponItemBinding
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
        if (item.isCheck && item.isCoupon && item.input) { //选中有券
            //item背景
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_select_coupon)
            //劵标记
            binding.stationTvIsCoupon.isVisible = true
            binding.stationTvIsCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_item_coupon2)
            binding.stationTvIsCoupon.setTextColor(R.color.orange.toColor(binding.stationTvCouponPrice.context))
            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.orange.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.orange.toColor(binding.stationTvCouponPrice.context))

        } else if (item.isCheck && !item.isCoupon && item.input) {//选中没券
            //item背景
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_select_coupon)

            //劵标记
            binding.stationTvIsCoupon.isVisible = false

            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.orange.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.orange.toColor(binding.stationTvCouponPrice.context))
        } else if (!item.isCheck && item.isCoupon && item.input) {//未选中有券
            //item背景
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_un_select_coupon)

            //劵标记
            binding.stationTvIsCoupon.isVisible = true
            binding.stationTvIsCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_item_coupon2)
            binding.stationTvIsCoupon.setTextColor(R.color.orange.toColor(binding.stationTvCouponPrice.context))

            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.background_color.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.background_color.toColor(binding.stationTvCouponPrice.context))
        } else if (!item.isCheck && !item.isCoupon && !item.input) {//未选中没券
            //item背景
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_un_select_coupon)
            //劵标记
            binding.stationTvIsCoupon.isVisible = false

            //标价格
            binding.stationTvMarkedPrice.setTextColor(R.color.background_color.toColor(binding.stationTvMarkedPrice.context))
            //优惠价格
            binding.stationTvCouponPrice.setTextColor(R.color.background_color.toColor(binding.stationTvCouponPrice.context))

        } else {
            //item背景
            binding.stationRlCoupon.setBackgroundResource(R.drawable.station_ensd_station_detail_shape_un_select_coupon)
            //劵标记
            binding.stationTvIsCoupon.isVisible = false
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