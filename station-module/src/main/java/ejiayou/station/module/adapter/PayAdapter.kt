package ejiayou.station.module.adapter

import ejiayou.station.module.R
import ejiayou.station.module.databinding.*
import ejiayou.station.module.model.PayItemDto
import ejiayou.uikit.module.recyclerview.BaseBindRecyclerAdapter


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description:支付订单
 */
class PayAdapter : BaseBindRecyclerAdapter<PayItemBinding, PayItemDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.pay_item
    }

    override fun onBindingItem(binding: PayItemBinding, item: PayItemDto, position: Int) {
        binding.stationIvEplusIcon.setBackgroundResource(item.icon)
        binding.stationTvEplusTitle.text = item.text
        if (item.isCheck) {
            binding.stationIvEplusCheck.setBackgroundResource(R.drawable.station_ensd_detail_eplus_select)
        } else {
            binding.stationIvEplusCheck.setBackgroundResource(R.drawable.station_ensd_detail_eplus_un_select)
        }

    }


}