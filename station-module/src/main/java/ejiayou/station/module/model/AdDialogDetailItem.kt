package ejiayou.station.module.model

/**
 * @author:
 * @created on: 2022/7/15 14:54
 * @description: 广告明细说明  viewType: 1.标题  2.内容
 */
data class AdDialogDetailItem(var viewType: Int = 1, var title: String = "", var text: String? = null, var text2: String? = null)