package ejiayou.station.module

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ejiayou.common.module.base.BaseAppBVMActivity
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.module.adapter.StationCalculeDiscountAdapter
import ejiayou.station.module.adapter.PayAdapter
import ejiayou.station.module.databinding.PayActivityBinding
import ejiayou.station.module.model.CalculateDiscountDto
import ejiayou.station.module.model.PayItemDto
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter
import ejiayou.uikit.module.recyclerview.SpaceItemDecoration
import java.util.ArrayList

/**
 * @author:
 * @created on: 2022/7/18 20:27
 * @description:支付订单
 */
class PayActivity : BaseAppBVMActivity<PayActivityBinding, StationEnsdPayModel>() {

    override fun layoutRes(): Int {
        return R.layout.pay_activity
    }

    private val stationPayActivity by lazy { PayAdapter() }
    var payItemDtos = ArrayList<PayItemDto>()
    override fun createViewModel(): StationEnsdPayModel {
        return StationEnsdPayModel()
    }

    //计算最优折扣优惠券
    private var calulates = ArrayList<CalculateDiscountDto>()
    private val stationCalculeDiscountAdapter by lazy { StationCalculeDiscountAdapter() }

    fun bestDiscount() {
        //合计优惠
        calulates.add(
            CalculateDiscountDto(
                14,
                icon = R.drawable.station_ensd_detail_praise,
                text = "最优折扣方案",
                discount = "",
                disType = ""
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_drop,
                text = "直降优惠",
                discount = "¥9.05",
                disType = "1"
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_coupon,
                icon2 = R.drawable.station_ensd_detail_plus,
                text = "优惠券",
                discount = "¥5",
                disType = "1"
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_sc,
                text = "服务费",
                discount = "¥3.05",
                disType = "1"
            )
        )
        stationCalculeDiscountAdapter.setItems(calulates)
        binding.stationPayRecyclerCoupon.adapter =
            stationCalculeDiscountAdapter
        binding.stationPayRecyclerCoupon.layoutManager =
            LinearLayoutManager(EnsdStationDetailAct@ this)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationPayRecyclerCoupon.addItemDecoration(
            spaceItemDecoration
        )
        binding.stationPayRecyclerCoupon.setHasFixedSize(
            true
        )
        stationCalculeDiscountAdapter.setOnItemClickListener(listener = object :
            BaseRecyclerAdapter.OnItemClickListener<CalculateDiscountDto> {
            override fun onItemClick(holder: Any, item: CalculateDiscountDto, position: Int) {

            }
        })

    }


    override fun initialize(savedInstanceState: Bundle?) {
        toolBarBuilder?.let {
            it.setTitle("确认支付").setBack(true).build()

        }
        binding.paySettleAmount.text = "201.11"
        binding.paySettleDescribe.text = "中石化麒麟加油站 丨 92# 丨4号枪"
        //计算最优折扣方式
        bestDiscount()
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_zfb, 1, "支付宝支付", true))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 2, "微信支付", false))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 3, "云闪付APP支付", false))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 4, "建设银行APP支付", false))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 5, "农行APP支付", false))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 6, "工商银行APP支付", false))
        payItemDtos.add(PayItemDto(R.drawable.station_ensd_detail_eplus_wx, 7, "交通银行APP支付", false))
        stationPayActivity.setItems(payItemDtos)
        binding.stationPayRecyclerWay.adapter = stationPayActivity
        binding.stationPayRecyclerWay.layoutManager = LinearLayoutManager(StationPayActivity@ this)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationPayRecyclerWay.addItemDecoration(spaceItemDecoration)
        binding.stationPayRecyclerWay.setHasFixedSize(true)
        stationPayActivity.setOnItemClickListener(listener = object : BaseRecyclerAdapter.OnItemClickListener<PayItemDto> {
            override fun onItemClick(holder: Any, item: PayItemDto, position: Int) {
                item.text?.let { ToastUtils.showToast(applicationContext, it) }
                if (item.isCheck) return
                var check = item.isCheck
                for (c in payItemDtos) {
                    c.isCheck = false
                }
                payItemDtos[position].isCheck = !check
                stationPayActivity.notifyDataSetChanged()
            }
        })
    }


    override fun layoutView(): View? {
        return null
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }
}