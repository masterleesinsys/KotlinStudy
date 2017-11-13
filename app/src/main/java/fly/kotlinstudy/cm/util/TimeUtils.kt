@file:Suppress("UNUSED_VALUE")

package fly.kotlinstudy.cm.util

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
/**
 * Created by Fly to 2117.8.9.
 * Version 2.1.2.
 * Email m15896847719@163.com
 * 计算两个时间戳的差值
 */

object TimeUtils {
    /*
    *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
    * 根据差值返回多长之间前或多长时间后
    * */
    fun getDistanceTime(time1: Long, time2: Long): String {
        var day: Long = 0
        var hour: Long = 0
        var min: Long = 0
        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
        var sec: Long = 0
        val diff: Long
        val flag: String
        if (time1 < time2) {
            diff = time2 - time1
            flag = "前"
        } else {
            diff = time1 - time2
            flag = "后"
        }
        day = diff / (24 * 60 * 60 * 1000)
        hour = diff / (60 * 60 * 1000) - day * 24
        min = diff / (60 * 1000) - day * 24 * 60 - hour * 60
        sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        if (day != 0L) return day.toString() + "天" + flag
        if (hour != 0L) return hour.toString() + "小时" + flag
        return if (min != 0L) min.toString() + "分钟" + flag else "刚刚"
    }
}
