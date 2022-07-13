package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import ejiayou.common.module.base.BaseAppBVMFragment
import ejiayou.station.module.databinding.StationFragmentDynamicBinding

/**
 * @author:
 * @created on: 2022/7/8 12:51
 * @description:
 */
class AFragmentMessage : BaseAppBVMFragment<StationFragmentDynamicBinding, AFragmentMessageViewModel>() {

    override fun layoutRes(): Int {
        return R.layout.station_fragment_dynamic
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

        binding.stationTv.setOnClickListener {
            viewModel.getArticleList()
        }

    }

}