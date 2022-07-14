package ejiayou.station.module.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ejiayou.station.module.R
import kotlinx.android.synthetic.main.station_ensd_detail_auto_ad_poll_item.view.*

/**
 * @author:
 * @created on: 2022/4/28 11:12
 * @description:
 */


class AutoRecycleViewPoll(private var mContext: Context, var list: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.station_ensd_detail_auto_ad_poll_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var p = position % list.size
        holder.itemView.tv_text.text = list[p]
    }

    override fun getItemCount(): Int {
        return return if (list.size > 0) {
            Integer.MAX_VALUE
        } else {
            0
        }
    }

//    fun startList(broadcastPrice: MutableList<String>, autoPollRecyclerView: AutoPollRecyclerView) {
//        list.clear()
//        list.addAll(broadcastPrice)
//        autoPollRecyclerView.start()
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

