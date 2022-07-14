package ejiayou.station.module.model

/**
 * @author:
 * @created on: 2022/7/13 15:07
 * @description: 100 200 300 适配器 data  isCoupon true 有券  false 无券 优惠金额 markPrice 100 200 300
 */
class CouponDto(var markPrice: String? = null, var discount: String? = null, var isCoupon: Boolean = false, var isCheck: Boolean = false)
