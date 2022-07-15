package ejiayou.station.module.model

/**
 * @author:
 * @created on: 2022/7/13 15:07
 * @description:计算金额后的优惠券 配器 data disType 类型
 */
data class CalculateDiscountDto(var size: Int = 12, var icon: Int = 0, var icon2: Int = 0, var text: String = "", var discount: String = "", var disType: String = "")
