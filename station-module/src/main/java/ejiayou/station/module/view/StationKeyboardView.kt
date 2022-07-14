package ejiayou.station.module.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import ejiayou.station.module.R
import kotlinx.android.synthetic.main.station_ensd_detail_input_view.view.*

/**
 * @ClassName: StationKeyboardView
 * @Description: java类作用描述
 */
class StationKeyboardView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var listener: OnChangeListener? = null
    private var sb = StringBuffer()

    init {
        LayoutInflater.from(context).inflate(R.layout.station_ensd_detail_input_view, this)
        initView()
    }

    private fun initView() {
        num_0.setOnClickListener(this)
        num_1.setOnClickListener(this)
        num_2.setOnClickListener(this)
        num_3.setOnClickListener(this)
        num_4.setOnClickListener(this)
        num_5.setOnClickListener(this)
        num_6.setOnClickListener(this)
        num_7.setOnClickListener(this)
        num_8.setOnClickListener(this)
        num_9.setOnClickListener(this)
        num_point.setOnClickListener(this)
        delete_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v) {
            num_0, num_1, num_2, num_3, num_4, num_5, num_6, num_7, num_8, num_9, num_point -> {
                val text = (v as TextView).text
                if (TextUtils.isEmpty(sb.toString()) && text == ".") {
                    return
                }
                if (!TextUtils.isEmpty(sb.toString()) && sb.toString().contains(".") && text == ".") {
                    return
                }
                if (sb.toString() == "0" && text == "0") {
                    return
                }
                if (!TextUtils.isEmpty(sb.toString()) && sb.toString().length >= 7) {
                    return
                }
                if (!TextUtils.isEmpty(sb.toString()) && sb.toString().contains(".")) {
                    val length = sb.toString().split(".")[1].length
                    if (length >= 2) {
                        return
                    }
                }
                sb.append(text)
            }
            delete_btn -> {
                if (!TextUtils.isEmpty(sb.toString())) {
                    sb.deleteCharAt(sb.length - 1)
                }
            }
        }
        listener?.onChange(sb.toString())
    }

    fun setOnChangeListener(l: OnChangeListener) {
        listener = l
    }

    fun clear() {
        sb.setLength(0)
    }

    fun getConfirmBtn(): TextView {
        return confirm_btn
    }

    interface OnChangeListener {
        fun onChange(text: String)
    }
}