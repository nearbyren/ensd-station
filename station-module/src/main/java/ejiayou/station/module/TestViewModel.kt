package ejiayou.station.module

import androidx.lifecycle.MutableLiveData
import ejiayou.common.module.base.BaseAppViewModel
import ejiayou.station.module.model.CouponDto

/**
 * @author: lr
 * @created on: 2022/7/10 2:58 下午
 * @description:
 */
class TestViewModel : BaseAppViewModel() {

    val couponDto = MutableLiveData<ArrayList<CouponDto>>()


}