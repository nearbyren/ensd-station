package ejiayou.station.module.adapter

import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailOilDialogItemBinding
import ejiayou.station.module.model.OilItemDto
import ejiayou.uikit.module.recyclerview.BaseBindRecyclerAdapter


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description:
 */
class StationOilAdapter : BaseBindRecyclerAdapter<StationEnsdDetailOilDialogItemBinding, OilItemDto>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.station_ensd_detail_oil_dialog_item
    }

    override fun onBindingItem(binding: StationEnsdDetailOilDialogItemBinding, item: OilItemDto, position: Int) {
        binding.stationOilContent.text = item.oil
    }


}