package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ejiayou.common.module.base.BaseAppBVMFragment
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.module.adapter.StationOilAdapter
import ejiayou.station.module.databinding.StationOilFragmentBinding
import ejiayou.station.module.model.OilItemDto
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter
import ejiayou.uikit.module.recyclerview.SpaceItemDecoration
import java.util.ArrayList

/**
 * @author:
 * @created on: 2022/7/8 12:51
 * @description:
 */
class StationOilNulberFragment : BaseAppBVMFragment<StationOilFragmentBinding, StationOilViewModel>() {

    override fun layoutRes(): Int {
        return R.layout.station_oil_fragment
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }

    override fun layoutView(): View? {
        return null
    }

    override fun createViewModel(): StationOilViewModel {
        return StationOilViewModel()
    }

    var array = ArrayList<OilItemDto>()
    private val stationOilAdapter by lazy { StationOilAdapter() }

    override fun initialize(savedInstanceState: Bundle?) {
        for (i in 1..30) {
            array.add(OilItemDto("$i"))
        }
        stationOilAdapter.setItems(array)
        binding.stationRecyclerOilFragment.adapter = stationOilAdapter
        binding.stationRecyclerOilFragment.layoutManager = GridLayoutManager(requireContext(), 4)
        val spaceItemDecoration = SpaceItemDecoration(0, 2, 2)
        binding.stationRecyclerOilFragment.addItemDecoration(spaceItemDecoration)
        binding.stationRecyclerOilFragment.setHasFixedSize(true)
        stationOilAdapter.setOnItemClickListener(listener = object : BaseRecyclerAdapter.OnItemClickListener<OilItemDto> {
            override fun onItemClick(holder: Any, item: OilItemDto, position: Int) {
                item.oil?.let { ToastUtils.showToast(requireActivity(), it) }
            }
        })
    }

}