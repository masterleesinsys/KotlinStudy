package fly.kotlinstudy.cm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fly.kotlinstudy.cm.R


/**
 * Created by Fly on 2017
 * Version 1.0.1
 * Mailbox 3256765723@qq.com
 * Blog m15896847719@163.com
 * QQ Group 668524118
 * Welcome friends who like innovation and love life, join us and grow up together!
 * End Thank you for your use.
 */
class LanguageAdvantageAdapter : RecyclerView.Adapter<LanguageAdvantageAdapter.ViewHolder>() {
    //创建新View，被LayoutManager所调用
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_languageadvantage, viewGroup, false)
        return ViewHolder(view)
    }

    //将数据与界面进行绑定的操作
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.mTextView.text = "语言优势说明（一）"
    }

    //获取数据的数量
    override fun getItemCount(): Int = 9

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTextView: TextView = view.findViewById(R.id.tv_test) as TextView
    }
}
