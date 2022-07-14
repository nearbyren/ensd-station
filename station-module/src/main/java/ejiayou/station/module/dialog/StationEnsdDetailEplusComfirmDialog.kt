package ejiayou.station.module.dialog

import android.os.Bundle
import android.view.View
import ejiayou.station.module.R
import ejiayou.station.module.adapter.OpenEplusOnClickListener
import ejiayou.station.module.databinding.StationEnsdDetailEplusBuyDialogBinding
import ejiayou.station.module.databinding.StationEnsdDetailEplusConfirmDialogBinding
import ejiayou.station.module.databinding.StationEnsdDetailOilDialogBinding
import ejiayou.uikit.module.dialog.BaseBindDialogFragment

/**
 * @author:
 * @created on: 2022/7/14 14:01
 * @description:
 */
class StationEnsdDetailEplusComfirmDialog(var listener: OpenEplusOnClickListener) : BaseBindDialogFragment<StationEnsdDetailEplusConfirmDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.station_ensd_detail_eplus_confirm_dialog
    }

    override fun initialize(view: View, savedInstanceState: Bundle?) {

        binding.stationBtnEPlusAgain.setOnClickListener {
            this.dismiss()
        }
        binding.stationBtnEPlusOpen.setOnClickListener {
            listener?.let {
                listener.oepn()
            }
        }
    }
}