package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import ejiayou.common.module.base.BaseAppBVMFragment
import ejiayou.station.module.databinding.StationCommonDialogLoadingBinding

/**
 * @author:
 * @created on: 2022/7/8 12:51
 * @description:
 */
class BFragmentMessage :  BaseAppBVMFragment<StationCommonDialogLoadingBinding, AFragmentMessageViewModel>() {

    override fun layoutRes(): Int {
        return R.layout.station_fragment_dynamicb
    }

    override fun layoutViewGroup(): ViewGroup? {
      return null
    }

    override fun layoutView(): View? {
        return null
    }

    override fun createViewModel(): AFragmentMessageViewModel {
       return AFragmentMessageViewModel()
    }

    override fun initialize(savedInstanceState: Bundle?) {
    }

}