package ejiayou.station.module.dialog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.module.R
import ejiayou.station.module.adapter.StationAdAdapter
import ejiayou.station.module.adapter.StationEplusAdapter
import ejiayou.station.module.databinding.StationEnsdDetailAdDialogBinding
import ejiayou.station.module.model.AdItemDto
import ejiayou.station.module.model.EPlusItemDto
import ejiayou.uikit.module.dialog.BaseBindDialogFragment
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter
import ejiayou.uikit.module.recyclerview.SpaceItemDecoration
import java.util.ArrayList

/**
 * @author:
 * @created on: 2022/7/14 14:01
 * @description:
 */
class StationEnsdDetailAdDialog : BaseBindDialogFragment<StationEnsdDetailAdDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.station_ensd_detail_ad_dialog
    }
    private val stationAdAdapter by lazy { StationAdAdapter() }
    var array = ArrayList<EPlusItemDto>()
    override fun initialize(view: View, savedInstanceState: Bundle?) {

        stationAdAdapter.setItems(array)
        binding.stationRecyclerAdDialog.adapter = stationAdAdapter
        binding.stationRecyclerAdDialog.layoutManager = LinearLayoutManager(requireContext())
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationRecyclerAdDialog.addItemDecoration(spaceItemDecoration)
        binding.stationRecyclerAdDialog.setHasFixedSize(true)
        stationAdAdapter.setOnItemClickListener(listener = object : BaseRecyclerAdapter.OnItemClickListener<EPlusItemDto> {
            override fun onItemClick(holder: Any, item: EPlusItemDto, position: Int) {

             }
        })
    }
}