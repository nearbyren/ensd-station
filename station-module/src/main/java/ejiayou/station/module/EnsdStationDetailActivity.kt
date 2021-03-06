package ejiayou.station.module

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.appbar.AppBarLayout
import com.orhanobut.logger.Logger
import ejiayou.common.module.base.BaseAppBVMActivity
import ejiayou.common.module.exts.dpToPx
import ejiayou.common.module.exts.hideSoftInput
import ejiayou.common.module.exts.observeNonNull
import ejiayou.common.module.exts.toColor
import ejiayou.common.module.ui.BarHelperConfig
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
 * @description: ????????????
 */
@Route(path = StationRouterTable.PATH_STATION_UI_DETAIL)
class EnsdStationDetailActivity : BaseAppBVMActivity<StationEnsdDetailBinding, StationEnsdDetailModel>() {


    override fun layoutRes(): Int {
        return R.layout.station_ensd_detail
    }

    override fun layoutViewGroup(): ViewGroup? {
        return null
    }

    override fun layoutView(): View? {
        return null
    }

    override fun initBarHelperConfig(): BarHelperConfig? {
        return BarHelperConfig.builder().setTitle("??????", Gravity.LEFT).build()
    }

    override fun createViewModel(): StationEnsdDetailModel {
        return StationEnsdDetailModel()
    }

    override fun initialize(savedInstanceState: Bundle?) {

        binding.stationRlLeft.setOnClickListener {
            ToastUtils.showToast(this, "stationRlLeft")
        }
        binding.stationRlRight.setOnClickListener {
            ToastUtils.showToast(this, "stationRlRight")
        }
        //????????????
        stationInfo()

        //??????
        advertiseInfo()

        //?????? ??????
        oilOptionInfo()

        //?????? 100 200 300
        markedPrice()

        //????????????????????????
        bestDiscount()

        //??????????????????
        enterInputOnFocus()
        //eplus
        eplusInfo()

        bottomInfo()


    }

    //???????????????????????????
    private var calulates = ArrayList<CalculateDiscountDto>()
    private val stationCalculeDiscountAdapter by lazy { StationCalculeDiscountAdapter() }

    // 100 200 300
    private var couponDtos = ArrayList<CouponDto>()
    private val stationCouponAdapter by lazy { StationCouponAdapter() }

    //??????????????????eplus
    private var comfirmDialog: StationEnsdDetailEplusComfirmDialog? = null

    //???????????? ?????? ?????? ?????? ?????????
    private fun stationInfo() {
        //????????????
        binding.stationTvDetailName.text = "??????????????????????????????"
        binding.stationSuspendedName.text = "??????????????????????????????"
        //????????????
        binding.stationTvDetailAddress.text = "?????????????????????G4????????????"
        //????????????
        binding.stationTvDetailDistance.text = "2km ?? 5min"
        //?????????
        binding.stationTvDetailPrice.text = "???8.53"
        //????????????  ????????????
        binding.stationTvDetailDoublePrice.text = "??????????????8.66 ??????????????8.66"
        //??????
        binding.stationIvDetailLike.setOnClickListener {
            ToastUtils.showToast(EnsdStationDetailAct@ this, "??????..")
            ARouter.getInstance().build(StationRouterTable.PATH_STATION_UI_DETAIL)
                    .withString("key1", "??????1")
                    .withString("key2", "??????2").navigation()
        }

        binding.stationCoordinatorLayout.setmMoveView(binding.stationMove1, binding.stationMove2)
        binding.stationCoordinatorLayout.setmZoomView(binding.stationIvStretch)
        binding.stationCenterAppbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                appBarLayout?.let {
                    //verticalOffset?????????0???????????????

                    if (verticalOffset < 0) {
                        binding.stationRlSuspended1.isVisible = false
                        binding.stationRlSuspended2.isVisible = true
                    } else {
                        binding.stationRlSuspended1.isVisible = true
                        binding.stationRlSuspended2.isVisible = false
                    }
                    val percent = Math.abs(verticalOffset * 1.0f) / it.totalScrollRange
                    binding.stationRlSuspended2.setBackgroundColor(changeAlpha(R.color.white.toColor(binding.stationRlSuspended1.context), percent))

                }

            }

        })

    }

    /**
     * ????????????????????????????????????
     */
    fun changeAlpha(color: Int, fraction: Float): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val alpha = (Color.alpha(color) * fraction).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    private fun advertiseInfo() {
        //????????????????????????
        var autoRecycleViewPollList = ArrayList<String>()
        autoRecycleViewPollList.add("1/1 ??????????????????????????????????????????????????????~")
        autoRecycleViewPollList.add("1/2 ??????????????????????????????????????????????????????~")
        autoRecycleViewPollList.add("1/3 ??????????????????????????????????????????????????????~")
        binding.stationAutoPollRecycler.layoutManager = ScrollSpeedLinearLayoutManger(this)
        binding.stationAutoPollRecycler.setHasFixedSize(true)
        binding.stationAutoPollRecycler.adapter = AutoRecycleViewPoll(this, autoRecycleViewPollList)
        binding.stationAutoPollRecycler.start(1)
//        binding.stationRecyclerAd.stop(1)
        //??????????????????
        if (Random.nextInt(2) == 1) {
            binding.stationLlHint.visibility = View.GONE
        } else {
            binding.stationLlHint.visibility = View.VISIBLE
        }
        //??????????????????????????????
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
        //??????
//        binding.stationOilNumber.stationTvOilGun
        //??????
//        binding.stationOilNumber.stationTvOilNumber
        //??????
//        binding.stationOilNumber.stationTvOilNumber
        //????????????
        binding.stationOilNumber.stationEtAmount.setOnFocusChangeListener { v, hasFocus ->
            Logger.d("TT", "hasFocus $hasFocus")
            detectEtAmount(hasFocus)
        }
        //????????????
        binding.stationOilNumber.stationRlOil.setOnClickListener {
            oilInitFragment()
        }
        //????????????
        binding.stationOilNumber.stationRlNumber.setOnClickListener {
            oilInitFragment()
        }

    }

    fun detectEtAmount(hasFocus: Boolean) {
        binding.stationOilNumber.stationEtAmount.hideSoftInput(EnsdStationDetailAct@ this)
        if (hasFocus) {
            //????????????
            binding.stationKeyBoard.visibility = View.VISIBLE
            //????????????????????????
            binding.stationOilNumber.stationCalculate.stationLlCalculate.visibility = View.GONE

        } else {
            //????????????
            binding.stationKeyBoard.visibility = View.GONE
            //????????????????????????
            binding.stationOilNumber.stationCalculate.stationLlCalculate.visibility = View.VISIBLE

        }
    }


    fun bestDiscount() {
        //????????????
        binding.stationOilNumber.stationCalculate.stationTvTotalDiscount.text = "??15.105"
        calulates.add(
            CalculateDiscountDto(
                14,
                icon = R.drawable.station_ensd_detail_praise,
                text = "??????????????????",
                discount = "",
                disType = ""
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_drop,
                text = "????????????",
                discount = "??9.05",
                disType = "1"
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_coupon,
                icon2 = R.drawable.station_ensd_detail_plus,
                text = "?????????",
                discount = "??5",
                disType = "1"
            )
        )
        calulates.add(
            CalculateDiscountDto(
                12,
                icon = R.drawable.station_ensd_detail_sc,
                text = "?????????",
                discount = "??3.05",
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
                index = 0,
                discount = "?????? ??3.11",
                markPrice = "100",
                isCheck = false,
                isCoupon = false,
                input = true
            )
        )
        couponDtos.add(
            CouponDto(
                index = 1,
                discount = "?????? ??3.12",
                markPrice = "200",
                isCheck = false,
                isCoupon = false,
                input = true

            )
        )
        couponDtos.add(
            CouponDto(
                index = 2,
                discount = "?????? ??3.13",
                markPrice = "300",
                isCheck = false,
                isCoupon = true,
                input = true

            )
        )


        stationCouponAdapter.setItems(couponDtos)

        viewModel.couponDto.observeNonNull(this) {
            stationCouponAdapter.refreshItems(it)
        }

        //?????? 100 200 300
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
     * ??????????????????????????????
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
            ToastUtils.showToast(this, "????????????")
        }

        binding.stationEplus.stationIvEPlusCheck.setOnClickListener {
            confirmEPlus()
        }
    }

    private fun bottomInfo() {
        //????????????
//        binding.stationConfirm.stationTvConfirmPrice
        //??????
//        binding.stationConfirm.stationTvConfirmDiscount
        //????????????
        binding.stationConfirm.stationBtnGo.setOnClickListener {
        }
    }

    /**
     * ????????????eplus??????
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
     * ??????eplus??????
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
                //????????????????????????location??????
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


