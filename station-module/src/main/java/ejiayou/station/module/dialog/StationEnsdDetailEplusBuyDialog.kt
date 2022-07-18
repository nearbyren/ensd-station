package ejiayou.station.module.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.module.R
import ejiayou.station.module.adapter.OpenEplusOnClickListener
import ejiayou.station.module.adapter.StationEplusAdapter
import ejiayou.station.module.databinding.StationEnsdDetailEplusBuyDialogBinding
import ejiayou.station.module.model.CouponDto
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
class StationEnsdDetailEplusBuyDialog : BaseBindDialogFragment<StationEnsdDetailEplusBuyDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.station_ensd_detail_eplus_buy_dialog
    }

    private val stationEplusAdapter by lazy { StationEplusAdapter() }
    var array = ArrayList<EPlusItemDto>()
    override fun initialize(view: View, savedInstanceState: Bundle?) {


        binding.stationTvEplusPrice.text = "¥22.99"
        binding.stationTvEplusDescribe.text = "易PLUS会员丨1个月"
        binding.stationRecyclerEplus
        array.add(EPlusItemDto(R.drawable.station_ensd_detail_eplus_zfb, 1, "支付宝支付", true))
        array.add(EPlusItemDto(R.drawable.station_ensd_detail_eplus_wx, 1, "微信支付", false))
        stationEplusAdapter.setItems(array)
        binding.stationRecyclerEplus.adapter = stationEplusAdapter
        binding.stationRecyclerEplus.layoutManager = LinearLayoutManager(requireContext())
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationRecyclerEplus.addItemDecoration(spaceItemDecoration)
        binding.stationRecyclerEplus.setHasFixedSize(true)
        binding.stationTvEplusClose.setOnClickListener { dismiss() }
        stationEplusAdapter.setOnItemClickListener(listener = object : BaseRecyclerAdapter.OnItemClickListener<EPlusItemDto> {
            override fun onItemClick(holder: Any, item: EPlusItemDto, position: Int) {
                item.text?.let { ToastUtils.showToast(requireActivity(), it) }
                if (item.isCheck) return
                var check = item.isCheck
                for (c in array) {
                    c.isCheck = false
                }
                array[position].isCheck = !check
                stationEplusAdapter.notifyDataSetChanged()
            }
        })
    }
}