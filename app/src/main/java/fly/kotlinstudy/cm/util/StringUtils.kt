@file:Suppress("UseExpressionBody", "ReplaceSizeZeroCheckWithIsEmpty")

package fly.kotlinstudy.cm.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.regex.Pattern

/**
 * Created by Fly to 2117.8.9.
 * Version 2.1.2.
 * Email m15896847719@163.com
 */
object StringUtils {
    private val SEP1 = "#"
    private val SEP2 = "|"
    private val SEP3 = "="

    ////////////////////////字符串与时间日期之间的转换////////////////

    private val emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
    // private final static SimpleDateFormat dateFormater = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // private final static SimpleDateFormat dateFormater2 = new
    // SimpleDateFormat("yyyy-MM-dd");

    private val dateFormater = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }
    }

    private val dateFormater2 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd")
        }
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    fun toDate(sdate: String): Date? {
        try {
            return dateFormater.get().parse(sdate)
        } catch (e: ParseException) {
            return null
        }

    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    fun friendly_time(sdate: String): String {
        val time = toDate(sdate) ?: return "Unknown"
        var ftime = ""
        val cal = Calendar.getInstance()

        // 判断是否是同一天
        val curDate = dateFormater2.get().format(cal.time)
        val paramDate = dateFormater2.get().format(time)
        if (curDate == paramDate) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
            return ftime
        }

        val lt = time.time / 86400000
        val ct = cal.timeInMillis / 86400000
        val days = (ct - lt).toInt()
        if (days == 0) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
        } else if (days == 1) {
            ftime = "昨天"
        } else if (days == 2) {
            ftime = "前天"
        } else if (days > 2 && days <= 10) {
            ftime = days.toString() + "天前"
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time)
        }
        return ftime
    }

    @SuppressLint("SimpleDateFormat")
            /**
     * 当前时间精确到毫秒数
     *
     * @return boolean
     */
    fun longdate(): String {

        val formatter = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS")
        return formatter.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
            /**
     * 当前时间精确到毫秒数并转化为date
     *
     * @return boolean
     */
    fun longdate2(): Date? {
        var time: Date? = null
        val formatter = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS")
        try {
            time = formatter.parse(formatter.format(Date()))
        } catch (e: ParseException) {
        }

        return time
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    fun isToday(sdate: String): Boolean {
        var b = false
        val time = toDate(sdate)
        val today = Date()
        if (time != null) {
            val nowDate = dateFormater2.get().format(today)
            val timeDate = dateFormater2.get().format(time)
            if (nowDate == timeDate) {
                b = true
            }
        }
        return b
    }

    /**
     * 判断String数组是否为空
     *
     * @return boolean
     */
    fun isNull(v: Array<String>): Boolean {
        for (i in 0..4)
            if (v[i] != null)
                return false
        return true
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input)
            return true

        for (i in 0 until input.length) {
            val c = input[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
            /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    fun isEmail(email: String?): Boolean {
        return if (email == null || email.trim { it <= ' ' }.length == 0) false else emailer.matcher(email).matches()
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    fun toInt(str: String, defValue: Int): Int {
        try {
            return Integer.parseInt(str)
        } catch (e: Exception) {
        }

        return defValue
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toInt(obj: Any?): Int {
        return if (obj == null) 0 else toInt(obj.toString(), 0)
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toLong(obj: String): Long {
        try {
            return java.lang.Long.parseLong(obj)
        } catch (e: Exception) {
        }

        return 0
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    fun toBool(b: String): Boolean {
        try {
            return java.lang.Boolean.parseBoolean(b)
        } catch (e: Exception) {
        }

        return false
    }

    /**
     * 将字符串中间用星号隐藏
     *
     * @param start 从哪一位开始隐藏
     * @param end   最后剩几位
     */
    fun hideString(str: String, start: Int, end: Int): String {
        if (start < 0 || end < 0) {
            return str
        } else if (start + end > str.length) {
            return str
        } else {
            val length = str.length
            val strStart = str.substring(0, start)
            val strMid = str.substring(start, length - end)
            val strEnd = str.substring(start + strMid.length, length)
            val s = StringBuffer()
            for (i in 0 until strMid.length) {
                s.append("*")
            }
            return strStart + s.toString() + strEnd
        }
    }

    /**
     * 判断调用toString方法的对象是不是null，避免造成空指针异常
     *
     * @return 如果传递的参数是null，返回空串，否则返回obj.toString()
     *
     *
     * null可以理解为原始类型至于可以把null作为参数只是特殊规定
     */
    fun object2String(obj: Any?): String {
        return obj?.toString() ?: ""
    }

    //////////////////////////////////字符串与集合间的转换/////////////////////////////

    /**
     * List转换String
     *
     * @param list :需要转换的List
     * @return String转换后的字符串
     */
    fun ListToString(list: List<*>?): String {
        val sb = StringBuffer()
        if (list != null && list.size > 0) {
            for (i in list.indices) {
                if (list[i] == null || list[i] === "") {
                    continue
                }
                // 如果值是list类型则调用自己
                if (list[i] is List<*>) {
                    sb.append(ListToString(list[i] as List<*>))
                    sb.append(SEP1)
                } else if (list[i] is Map<*, *>) {
                    sb.append(MapToString(list[i] as Map<*, *>))
                    sb.append(SEP1)
                } else {
                    sb.append(list[i])
                    sb.append(SEP1)
                }
            }
        }
        return "L" + sb.toString()
    }

    /**
     * Map转换String
     *
     * @param map :需要转换的Map
     * @return String转换后的字符串
     */
    fun MapToString(map: Map<*, *>): String {
        val sb = StringBuffer()
        // 遍历map
        for (obj in map.keys) {
            if (obj == null) {
                continue
            }
            val value = map[obj]
            if (value is List<*>) {
                sb.append(obj.toString() + SEP1 + ListToString(value))
                sb.append(SEP2)
            } else if (value is Map<*, *>) {
                sb.append(obj.toString() + SEP1
                        + MapToString(value))
                sb.append(SEP2)
            } else {
                sb.append(obj.toString() + SEP3 + value.toString())
                sb.append(SEP2)
            }
        }
        return "M" + sb.toString()
    }

    /**
     * String转换Map
     *
     * @param mapText :需要转换的字符串
     * @return Map<String></String>,Object>
     */
    fun StringToMap(mapText: String?): Map<String, Any>? {
        var mapText = mapText

        if (mapText == null || mapText == "") {
            return null
        }
        mapText = mapText.substring(1)

        val map = HashMap<String, Any>()
        val text = mapText.split(("\\" + SEP2).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // 转换为数组
        for (str in text) {
            val keyText = str.split(SEP3.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // 转换key与value的数组
            if (keyText.size < 1) {
                continue
            }
            val key = keyText[0] // key
            val value = keyText[1] // value
            if (value[0] == 'M') {
                val map1 = StringToMap(value)
                if (map1 != null) {
                    map.put(key, map1)
                }
            } else if (value[0] == 'L') {
                val list = StringToList(value)
                if (list != null) {
                    map.put(key, list)
                }
            } else {
                map.put(key, value)
            }
        }
        return map
    }

    /**
     * String转换List
     *
     * @param listText :需要转换的文本
     * @return List<Object>
    </Object> */
    fun StringToList(listText: String?): List<Any>? {
        var listText = listText
        if (listText == null || listText == "") {
            return null
        }
        listText = listText.substring(1)

        val list = ArrayList<Any>()
        val text = listText.split(SEP1.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (str in text) {
            if (str[0] == 'M') {
                val map = StringToMap(str)
                if (map != null) {
                    list.add(map)
                }
            } else if (str[0] == 'L') {
                val lists = StringToList(str)
                if (lists != null) {
                    list.add(lists)
                }
            } else {
                list.add(str)
            }
        }
        return list
    }
}
