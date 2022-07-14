package ejiayou.station.module.dialog

import android.os.Bundle
import android.view.View
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import ejiayou.station.module.StationOilNulberFragment
import ejiayou.station.module.R
import ejiayou.station.module.databinding.StationEnsdDetailOilDialogBinding
import ejiayou.uikit.module.dialog.BaseBindDialogFragment

/**
 * @author:
 * @created on: 2022/7/14 14:01
 * @description:
 */
class StationEnsdDetailOilDialog : BaseBindDialogFragment<StationEnsdDetailOilDialogBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.station_ensd_detail_oil_dialog
    }

    override fun initialize(view: View, savedInstanceState: Bundle?) {
        val pagerItems = FragmentPagerItems.with(requireActivity().applicationContext)
                .add("92# ", StationOilNulberFragment::class.java)
                .add("95 #", StationOilNulberFragment::class.java)
                .add("0 # ", StationOilNulberFragment::class.java).create()
        binding.stationPager.offscreenPageLimit = pagerItems.size
        val adapter = FragmentPagerItemAdapter(childFragmentManager, pagerItems)
        binding.stationPager.adapter = adapter
        binding.stationPager.setScanScroll(true)
        binding.stationSmartTabLayout.setViewPager(binding.stationPager)
    }
}