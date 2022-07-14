package ejiayou.station.module

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import ejiayou.common.module.base.BaseAppBVMActivity
import ejiayou.common.module.exts.dpToPx
import ejiayou.common.module.exts.hideSoftInput
import ejiayou.common.module.exts.observeNonNull
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.module.adapter.OpenEplusOnClickListener
import ejiayou.station.module.adapter.StationAdAdapter
import ejiayou.station.module.adapter.StationCalculeDiscountAdapter
import ejiayou.station.module.adapter.StationCouponAdapter
import ejiayou.station.module.databinding.StationEnsdDetailBinding
import ejiayou.station.module.dialog.StationEnsdDetailAdDialog
import ejiayou.station.module.dialog.StationEnsdDetailEplusBuyDialog
import ejiayou.station.module.dialog.StationEnsdDetailEplusComfirmDialog
import ejiayou.station.module.dialog.StationEnsdDetailOilDialog
import ejiayou.station.module.model.CalculateDiscountDto
import ejiayou.station.module.model.CouponDto
import ejiayou.station.module.view.AutoRecycleViewPoll
import ejiayou.station.module.view.ScrollSpeedLinearLayoutManger
import ejiayou.station.module.view.StationKeyboardView
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter
import ejiayou.uikit.module.recyclerview.BaseRecyclerAdapter.OnItemClickListener
import ejiayou.uikit.module.recyclerview.SpaceItemDecoration
import kotlinx.android.synthetic.main.station_ensd_detail.*
import kotlinx.android.synthetic.main.station_ensd_detail_confirm.view.*
import kotlinx.android.synthetic.main.station_ensd_detail_oil_number.view.*
import java.lang.reflect.Method
import java.util.ArrayList
import kotlin.random.Random

/**
 * @author:
 * @created on: 2022/7/8 13:51
 * @description:
 */
class EnsdStationDetailAct : BaseAppBVMActivity<StationEnsdDetailBinding, TestViewModel>() {


    override fun layoutRes(): Int {
        return R.layout.station_ensd_detail
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }

    override fun layoutView(): View? {
        return null
    }

    override fun createViewModel(): TestViewModel {
        return TestViewModel()
    }

    override fun initialize(savedInstanceState: Bundle?) {
        var random: Int = 0
        binding.stationRlLeft.setOnClickListener {
            ToastUtils.showToast(this, "stationRlLeft")
        }
        binding.stationRlRight.setOnClickListener {
            ToastUtils.showToast(this, "stationRlRight")
            random = Random.nextInt(2)
        }
        //油站名称
        binding.stationTvDetailName.text = "加德士北环大厦加油站"
        //油站地址
        binding.stationTvDetailAddress.text = "南山区南海大道G4出口附近"
        //距离时间
        binding.stationTvDetailDistance.text = "2km · 5min"
        //油价格
        binding.stationTvDetailPrice.text = "￥8.53"
        //油站价格  国家价格
        binding.stationTvDetailDoublePrice.text = "油站价：¥8.66 国家价：¥8.66"
        //标记常去
//        binding.stationIvDetailLike
        //广告置
        var autoRecycleViewPollList = ArrayList<String>()
        autoRecycleViewPollList.add("1/1 站内支持开票，如有疑问请向加油员询问~")
        autoRecycleViewPollList.add("1/2 站内支持开票，如有疑问请向加油员询问~")
        autoRecycleViewPollList.add("1/3 站内支持开票，如有疑问请向加油员询问~")
        binding.stationAutoPollRecycler.layoutManager = ScrollSpeedLinearLayoutManger(this)
        binding.stationAutoPollRecycler.setHasFixedSize(true)
        binding.stationAutoPollRecycler.adapter = AutoRecycleViewPoll(this, autoRecycleViewPollList)
        binding.stationAutoPollRecycler.start(1)
//        binding.stationRecyclerAd.stop(1)
        Logger.d("TT", "random $random")
        //广告轮播提示
        if (random == 0) {
            binding.stationLlHint.visibility = View.GONE
        } else {
            binding.stationLlHint.visibility = View.VISIBLE
        }
        //点击展开块状
        binding.stationLlHintClick.setOnClickListener {
            ToastUtils.showToast(this, "stationLlHintClick")
            val adDialog = StationEnsdDetailAdDialog()
            adDialog.setGravity(Gravity.BOTTOM)
            adDialog.show(activity = EnsdStationDetailAct@ this, "ad_dialog")
        }
        //滚动文案广告提示
//        binding.stationAutoPollRecycler
        //油枪
//        binding.stationOilNumber.stationTvOilGun
        //油号
//        binding.stationOilNumber.stationTvOilNumber
        //输入金额
        binding.stationOilNumber.stationEtAmount.setOnFocusChangeListener { v, hasFocus ->
            Logger.d("TT", "hasFocus $hasFocus")

            detectEtAmount(hasFocus)
        }
        //计算最优折扣方式
        bestDiscount()


        //始终隐藏键盘
        enterInputOnFocus()
        binding.stationOilNumber.stationRlNumber.setOnClickListener {
            oilInitFragment()
        }
        binding.stationOilNumber.stationRlOil.setOnClickListener {
            oilInitFragment()
        }


        //标价 100 200 300
        markedPrice()



        //eplus
        binding.stationEplus.stationTvVipRule.setOnClickListener {
            ToastUtils.showToast(this, "stationEplus")
        }

        //支付金额
//        binding.stationConfirm.stationTvConfirmPrice
        //优惠
//        binding.stationConfirm.stationTvConfirmDiscount
        //确认订单
        binding.stationConfirm.stationBtnGo.setOnClickListener {
            goComfirmEplus()

        }
    }

    var calulates = ArrayList<CalculateDiscountDto>()

    private val stationCalculeDiscountAdapter by lazy { StationCalculeDiscountAdapter() }
    fun bestDiscount() {
        //合计优惠
        binding.stationOilNumber.stationCalculate.stationTvTotalDiscount.text = "¥15.05"
        calulates.add(CalculateDiscountDto(
            icon = R.drawable.station_ensd_detail_praise,
            text = "",
            discount = "",
            disType = ""
        ))
        calulates.add(CalculateDiscountDto(
            icon = R.drawable.station_ensd_detail_drop,
            text = "直降优惠",
            discount = "¥9.05",
            disType = "1"
        ))
        calulates.add(CalculateDiscountDto(
            icon = R.drawable.station_ensd_detail_praise,
            icon2 = R.drawable.station_ensd_detail_coupon,
            text = "优惠券",
            discount = "¥5",
            disType = "1"
        ))
        calulates.add(CalculateDiscountDto(
            icon = R.drawable.station_ensd_detail_sc,
            text = "服务费",
            discount = "-¥3.05",
            disType = "1"
        ))
        stationCalculeDiscountAdapter.setItems(calulates)
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.adapter =
            stationCalculeDiscountAdapter
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.layoutManager =
            LinearLayoutManager(EnsdStationDetailAct@ this)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.addItemDecoration(spaceItemDecoration)
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.setHasFixedSize(true)
        stationCalculeDiscountAdapter.setOnItemClickListener(listener = object : BaseRecyclerAdapter.OnItemClickListener<CalculateDiscountDto> {
            override fun onItemClick(holder: Any, item: CalculateDiscountDto, position: Int) {

            }
        })

    }

    fun markedPrice(){
        var couponDto1 = CouponDto()
        var couponDto2 = CouponDto()
        var couponDto3 = CouponDto()

        couponDto1.discount = "优惠 ¥3.11"
        couponDto2.discount = "优惠 ¥3.12"
        couponDto3.discount = "优惠 ¥3.13"


        couponDto1.markPrice = "100"
        couponDto2.markPrice = "200"
        couponDto3.markPrice = "300"

        couponDto1.isCheck = false
        couponDto2.isCheck = true
        couponDto3.isCheck = false


        couponDto1.isCoupon = false
        couponDto2.isCoupon = false
        couponDto3.isCoupon = false

        array.add(couponDto1)
        array.add(couponDto2)
        array.add(couponDto3)
        stationCouponAdapter.setItems(array)
        refresh()
        //标记 100 200 300
        binding.stationOilNumber.stationRecyclerCoupon.adapter = stationCouponAdapter
        binding.stationOilNumber.stationRecyclerCoupon.layoutManager = GridLayoutManager(this, 3)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationOilNumber.stationRecyclerCoupon.addItemDecoration(spaceItemDecoration)
        binding.stationOilNumber.stationRecyclerCoupon.setHasFixedSize(true)
        stationCouponAdapter.setOnItemClickListener(listener = object : OnItemClickListener<CouponDto> {
            override fun onItemClick(holder: Any, item: CouponDto, position: Int) {
                var check = item.isCheck
                for (c in array) {
                    c.isCheck = false
                }
                array[position].isCheck = !check
                viewModel.couponDto.value = array
            }
        })
    }
    fun oilInitFragment() {
        val adDialog = StationEnsdDetailOilDialog()
        adDialog.setGravity(Gravity.BOTTOM)
        var hight: Int = 343
        adDialog.setHeight(hight.dpToPx)
        adDialog.show(activity = EnsdStationDetailAct@ this, "oil_dialog")
    }

    /**
     * 处理进入直接关闭键盘
     */
    fun enterInputOnFocus() {
        try {
            val cls: Class<EditText> = EditText::class.java
            val setShowSoftInputOnFocus: Method
            setShowSoftInputOnFocus =
                cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = true
            setShowSoftInputOnFocus.invoke(binding.stationOilNumber.stationEtAmount, false)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    fun detectEtAmount(hasFocus: Boolean) {
        binding.stationOilNumber.stationEtAmount.hideSoftInput(EnsdStationDetailAct@ this)
        if (hasFocus) {
            binding.stationKeyBoard.visibility = View.VISIBLE
        } else {
            binding.stationKeyBoard.visibility = View.GONE
        }
    }

    /**
     * 确认购买eplus操作
     */
    fun goComfirmEplus() {
        comfirmDialog =
            StationEnsdDetailEplusComfirmDialog(object : OpenEplusOnClickListener {
                override fun oepn() {
                    comfirmDialog?.let {
                        it.dismiss()
                        goBuyEplus()
                    }
                }
            })
        comfirmDialog?.let {
            it.setGravity(Gravity.CENTER)
            it.show(activity = EnsdStationDetailAct@ this, "comfirm_eplus_dialog")
        }
    }

    /**
     * 购买eplus操作
     */
    fun goBuyEplus() {
        val eplusBuyDialog = StationEnsdDetailEplusBuyDialog()
        eplusBuyDialog.setGravity(Gravity.BOTTOM)
        eplusBuyDialog.show(activity = EnsdStationDetailAct@ this, "buy_eplus_dialog")
    }

    var array = ArrayList<CouponDto>()

    fun refresh() {
        viewModel.couponDto.observeNonNull(this) {
            stationCouponAdapter.refreshItems(it)
        }
    }

    var comfirmDialog: StationEnsdDetailEplusComfirmDialog? = null
    private val stationCouponAdapter by lazy { StationCouponAdapter() }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            if (it.action == MotionEvent.ACTION_DOWN) {
                var v = currentFocus
                v?.let {
                    if (isShouldHideInput(it, ev)) {
                        binding.stationOilNumber.stationEtAmount.clearFocus()
                    }
                }

            }
        }
        return super.dispatchTouchEvent(ev)


    }

    @SuppressLint("Range")
    fun isShouldHideInput(view: Any, event: MotionEvent): Boolean {
        view?.let {
            if (view is EditText) {
                //获取输入框当前的location位置
                var value = intArrayOf(0, 0)
                view.getLocationInWindow(value)
                var left = value[0]
                var top = value[1]
                var right = left + view.width
                var bottom = top + view.height
                return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
            } else if (view is StationKeyboardView) {
                return false
            }
        }
        return false
    }
}


