package ejiayou.station.module.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import java.lang.ref.WeakReference

/**
 * @author:
 * @created on: 2022/5/5 15:39
 * @description:
 */
class AutoPollRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context!!, attrs) {
    private val autoPollTask: AutoPollTask = AutoPollTask(this)
    private val mutableTasks: MutableMap<Int, AutoPollTask> = HashMap()
    private var index = 0
    var indexType = 0
    var running = false
    private var canRun = false

    internal inner class AutoPollTask(reference: AutoPollRecyclerView) : Runnable {
        private val mReference: WeakReference<AutoPollRecyclerView> =
            WeakReference<AutoPollRecyclerView>(reference)

        override fun run() {
            val recyclerView = mReference.get()
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
//                Logger.d("测试数据  autoPollTask - " + recyclerView.autoPollTask + "  - running " + recyclerView.running + " - canRun " + recyclerView.canRun + "  -  Thread name " + Thread.currentThread().name + " - indexType - " + indexType)
                recyclerView.smoothScrollToPosition(++recyclerView.index)
                recyclerView.postDelayed(recyclerView.mutableTasks[indexType], TIME_AUTO_POLL)
            }
        }

    }

    fun start(indexType: Int) {
        var t = mutableTasks[indexType]
        if (t == null) {
            mutableTasks[indexType] = autoPollTask
        }
        if (running) stop(indexType)
        this.indexType = indexType
        canRun = true
        running = true

        postDelayed(mutableTasks[indexType], TIME_AUTO_POLL)
    }

    fun stop(indexType: Int) {
        running = false
        var t = mutableTasks[indexType]
        if (t != null) {
            removeCallbacks(t)
        }

    }

    companion object {
        private const val TIME_AUTO_POLL: Long = 3000
    }

}
