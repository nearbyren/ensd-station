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
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.Logger
import ejiayou.common.module.base.BaseAppBVMActivity
import ejiayou.common.module.exts.dpToPx
import ejiayou.common.module.exts.hideSoftInput
import ejiayou.common.module.exts.observeNonNull
import ejiayou.common.module.utils.ToastUtils
import ejiayou.station.export.router.StationRouterTable
import ejiayou.station.module.adapter.OpenEplusOnClickListener
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
import java.lang.reflect.Method
import kotlin.random.Random

/**
 * @author:
 * @created on: 2022/7/8 13:51
 * @description: 油站详情
 */
@Route(path = StationRouterTable.PATH_STATION_UI_DETAIL)
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

        binding.stationRlLeft.setOnClickListener {
            ToastUtils.showToast(this, "stationRlLeft")
        }
        binding.stationRlRight.setOnClickListener {
            ToastUtils.showToast(this, "stationRlRight")
        }
        //油站信息
        stationInfo()

        //广告
        advertiseInfo()

        //油枪 油号
        oilOptionInfo()

        //标价 100 200 300
        markedPrice()

        //计算最优折扣方式
        bestDiscount()

        //始终隐藏键盘
        enterInputOnFocus()
        //eplus
        eplusInfo()

        bottomInfo()


    }

    //计算最优折扣优惠券
    private var calulates = ArrayList<CalculateDiscountDto>()
    private val stationCalculeDiscountAdapter by lazy { StationCalculeDiscountAdapter() }

    // 100 200 300
    private var couponDtos = ArrayList<CouponDto>()
    private val stationCouponAdapter by lazy { StationCouponAdapter() }

    //确认提示购买eplus
    private var comfirmDialog: StationEnsdDetailEplusComfirmDialog? = null

    //油站名称 地址 距离 价格 国家价
    private fun stationInfo() {
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
        //收藏
        binding.stationIvDetailLike.setOnClickListener {
            ToastUtils.showToast(EnsdStationDetailAct@ this, "收藏..")
            ARouter.getInstance().build(StationRouterTable.PATH_STATION_UI_DETAIL)
                .withString("key1", "哈哈1")
                .withString("key2", "哈哈2").navigation()
        }
    }

    private fun advertiseInfo() {
        //滚动文案广告提示
        var autoRecycleViewPollList = ArrayList<String>()
        autoRecycleViewPollList.add("1/1 站内支持开票，如有疑问请向加油员询问~")
        autoRecycleViewPollList.add("1/2 站内支持开票，如有疑问请向加油员询问~")
        autoRecycleViewPollList.add("1/3 站内支持开票，如有疑问请向加油员询问~")
        binding.stationAutoPollRecycler.layoutManager = ScrollSpeedLinearLayoutManger(this)
        binding.stationAutoPollRecycler.setHasFixedSize(true)
        binding.stationAutoPollRecycler.adapter = AutoRecycleViewPoll(this, autoRecycleViewPollList)
        binding.stationAutoPollRecycler.start(1)
//        binding.stationRecyclerAd.stop(1)
        //广告轮播提示
        if (Random.nextInt(2) == 1) {
            binding.stationLlHint.visibility = View.GONE
        } else {
            binding.stationLlHint.visibility = View.VISIBLE
        }
        //点击展开详情广告块状
        binding.stationLlHintClick.setOnClickListener {
            ToastUtils.showToast(this, "stationLlHintClick")
            val adDialog = StationEnsdDetailAdDialog()
            var hight: Int = 343
            adDialog.setHeight(hight.dpToPx)
            adDialog.setGravity(Gravity.BOTTOM)
            adDialog.show(activity = EnsdStationDetailAct@ this, "ad_dialog")
        }

    }

    private fun oilOptionInfo() {
        //油枪
//        binding.stationOilNumber.stationTvOilGun
        //油号
//        binding.stationOilNumber.stationTvOilNumber
        //油号
//        binding.stationOilNumber.stationTvOilNumber
        //输入金额
        binding.stationOilNumber.stationEtAmount.setOnFocusChangeListener { v, hasFocus ->
            Logger.d("TT", "hasFocus $hasFocus")
            detectEtAmount(hasFocus)
        }
        //油枪点击
        binding.stationOilNumber.stationRlOil.setOnClickListener {
            oilInitFragment()
        }
        //油号点击
        binding.stationOilNumber.stationRlNumber.setOnClickListener {
            oilInitFragment()
        }

    }

    fun detectEtAmount(hasFocus: Boolean) {
        binding.stationOilNumber.stationEtAmount.hideSoftInput(EnsdStationDetailAct@ this)
        if (hasFocus) {
            //隐藏键盘
            binding.stationKeyBoard.visibility = View.VISIBLE
            //隐藏最优折扣优惠
            binding.stationOilNumber.stationCalculate.stationLlCalculate.visibility = View.GONE

        } else {
            //隐藏键盘
            binding.stationKeyBoard.visibility = View.GONE
            //隐藏最优折扣优惠
            binding.stationOilNumber.stationCalculate.stationLlCalculate.visibility = View.VISIBLE

        }
    }


    fun bestDiscount() {
        //合计优惠
        binding.stationOilNumber.stationCalculate.stationTvTotalDiscount.text = "¥15.105"
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
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.adapter =
            stationCalculeDiscountAdapter
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.layoutManager =
            LinearLayoutManager(EnsdStationDetailAct@ this)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.addItemDecoration(
            spaceItemDecoration
        )
        binding.stationOilNumber.stationCalculate.stationRecyclerCalculateCoupon.setHasFixedSize(
            true
        )
        stationCalculeDiscountAdapter.setOnItemClickListener(listener = object :
            BaseRecyclerAdapter.OnItemClickListener<CalculateDiscountDto> {
            override fun onItemClick(holder: Any, item: CalculateDiscountDto, position: Int) {

            }
        })

    }

    fun markedPrice() {

        couponDtos.add(
            CouponDto(
                discount = "优惠 ¥3.11",
                markPrice = "100",
                isCheck = false,
                isCoupon = false
            )
        )
        couponDtos.add(
            CouponDto(
                discount = "优惠 ¥3.12",
                markPrice = "100",
                isCheck = false,
                isCoupon = true
            )
        )
        couponDtos.add(
            CouponDto(
                discount = "优惠 ¥3.13",
                markPrice = "100",
                isCheck = false,
                isCoupon = false
            )
        )


        stationCouponAdapter.setItems(couponDtos)

        viewModel.couponDto.observeNonNull(this) {
            stationCouponAdapter.refreshItems(it)
        }

        //标记 100 200 300
        binding.stationOilNumber.stationRecyclerCoupon.adapter = stationCouponAdapter
        binding.stationOilNumber.stationRecyclerCoupon.layoutManager = GridLayoutManager(this, 3)
        val spaceItemDecoration = SpaceItemDecoration(0, 10, 35)
        binding.stationOilNumber.stationRecyclerCoupon.addItemDecoration(spaceItemDecoration)
        binding.stationOilNumber.stationRecyclerCoupon.setHasFixedSize(true)

        stationCouponAdapter.setOnItemClickListener(listener = object :
            OnItemClickListener<CouponDto> {
            override fun onItemClick(holder: Any, item: CouponDto, position: Int) {
                var check = item.isCheck
                for (c in couponDtos) {
                    c.isCheck = false
                }
                couponDtos[position].isCheck = !check
                viewModel.couponDto.value = couponDtos
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

    private fun eplusInfo() {
        //eplus
        binding.stationEplus.stationTvVipRule.setOnClickListener {
            ToastUtils.showToast(this, "会员规则")
        }

        binding.stationEplus.stationIvEPlusCheck.setOnClickListener {
            confirmEPlus()
        }
    }

    private fun bottomInfo() {
        //支付金额
//        binding.stationConfirm.stationTvConfirmPrice
        //优惠
//        binding.stationConfirm.stationTvConfirmDiscount
        //确认订单
        binding.stationConfirm.stationBtnGo.setOnClickListener {

        }
    }

    /**
     * 确认购买eplus操作
     */
    private fun confirmEPlus() {
        comfirmDialog =
            StationEnsdDetailEplusComfirmDialog(object : OpenEplusOnClickListener {
                override fun oepn() {
                    comfirmDialog?.let {
                        it.dismiss()
                        buyEPlus()
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
    private fun buyEPlus() {
        val eplusBuyDialog = StationEnsdDetailEplusBuyDialog()
        eplusBuyDialog.setGravity(Gravity.BOTTOM)
        eplusBuyDialog.show(activity = EnsdStationDetailAct@ this, "buy_eplus_dialog")
    }


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


