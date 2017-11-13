package fly.kotlinstudy.cm.util

import android.annotation.SuppressLint
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Fly to 2117.8.9.
 * Version 2.1.2.
 * Email m15896847719@163.com
 */
class DateUtils
/**
 * 获取当前年月日N天后的年月日
 *
 * @param dateTime 当前年月日字符串（2017-8-9）
 * @param days     N天（如3，2017-8-12）
 */
(dateTime: String, days: Int) {
    ////**********获取当前年月日时分秒*****///////
    var year = 0
        get() {
            this.year = c.get(Calendar.YEAR)
            return field
        }
    var month = 0
        get() {
            this.month = c.get(Calendar.MONTH) + 1
            return field
        }
    var day = 0
        get() {
            this.day = c.get(Calendar.DAY_OF_MONTH)
            return field
        }
    var hour = 0
    var minute = 0
    var second = 0
    private val c = Calendar.getInstance()
    var oldYear = 0
    var oldMonth = 0
    var oldDay = 0

    init {
        getDateAfterNDays(dateTime, days)
    }

    /**
     * 获取给定日期N天后的日期
     *
     * @author GaoHuanjie
     */
    private fun getDateAfterNDays(dateTime: String, days: Int): String {
        val calendar = Calendar.getInstance()
        val dateTimeArray = dateTime.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val year = Integer.parseInt(dateTimeArray[0])
        val month = Integer.parseInt(dateTimeArray[1])
        val day = Integer.parseInt(dateTimeArray[2])
        calendar.set(year, month - 1, day)
        val time = calendar.timeInMillis
        calendar.timeInMillis = time + days * 1000 * 60 * 60 * 24

        this.oldYear = calendar.get(Calendar.YEAR)
        this.oldMonth = calendar.get(Calendar.MONTH) + 1
        this.oldDay = calendar.get(Calendar.DAY_OF_MONTH)

        return calendar.get(Calendar.YEAR).toString() + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
    }

    companion object {

        /**
         * 获取当前时间戳
         *
         * @return
         */
        val timeStamp: Long
            get() = System.currentTimeMillis()

        @SuppressLint("SimpleDateFormat")
                /**
         * 将字符串转为时间戳
         *
         * @param user_time
         * @return
         */
        fun getTime(user_time: String): String? {
            var re_time: String? = null
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val d: Date
            try {
                d = sdf.parse(user_time)
                val l = d.time
                val str = l.toString()
                re_time = str.substring(0, 10)
            } catch (e: ParseException) {
                // TODO Auto-generated catch block e.printStackTrace();
                Toast.makeText(Fly.app,e.toString(),Toast.LENGTH_SHORT).show()
            }
            return re_time
        }

        /**
         * 获取当前时间
         *
         * @return
         */
        val todayDateTime: String
            get() {
                val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                return format.format(Date())
            }

        /**
         * 获取当前年月日
         *
         * @return
         */
        val todayDate: String
            get() {
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                return format.format(Date())
            }
    }
}
