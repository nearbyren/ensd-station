package ejiayou.station.module.adapter

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import ejiayou.station.module.R
import ejiayou.station.module.model.AdDialogDetailItem
import ejiayou.uikit.module.recyclerview.BaseMultiRecyclerAdapter
import ejiayou.uikit.module.recyclerview.BaseViewHolder
import java.util.ArrayList


/**
 * @author:
 * @created on: 2022/7/13 19:00
 * @description:
 */
class StationAdDialogAdapter : BaseMultiRecyclerAdapter() {
    var ads: ArrayList<AdDialogDetailItem>? = null

    fun addAdapterItemData(ads: ArrayList<AdDialogDetailItem>) {
        this.ads = ads
    }

    override fun getItemCount(): Int {
        return ads?.let { it.size } ?: 0
    }

    override fun getLayoutId(viewType: Int): Int {
        if (viewType == 1) {
            return R.layout.station_ensd_detail_ad_dialog_item_title
        } else if (viewType == 2) {
            return R.layout.station_ensd_detail_ad_dialog_item_content
        }
        return 0
    }

    override fun onBindItem(holder: BaseViewHolder, viewType: Int, position: Int) {
        if (viewType == 1) {
            holder.getView<TextView>(R.id.station_ad_title).text = ads?.let { it[position].title }
        } else if (viewType == 2) {
            ads?.let {
                if (!TextUtils.isEmpty(it[position].text)) {
                    var t = holder.getView<TextView>(R.id.station_ad_left)
                    t.visibility = View.VISIBLE
                    t.text = it[position].text
                }
                holder.getView<TextView>(R.id.station_ad_right).text = it[position].text2
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return ads?.let { it[position].viewType } ?: 1
    }


}