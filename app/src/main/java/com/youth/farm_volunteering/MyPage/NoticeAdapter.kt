package com.youth.farm_volunteering.MyPage

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.expandedView
import com.youth.farm_volunteering.R.id.tv_title
import kotlinx.android.synthetic.main.item_notice_group.view.*


class NoticeAdapter(var context: Context, var noticeexpandedView: ExpandableListView, var header: MutableList<String>, var body: MutableList<MutableList<String>>) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_notice_group, null)
        }
        val title = convertView!!.findViewById<TextView>(R.id.notice_title)
        title.text = getGroup(groupPosition)
        title?.setOnClickListener {
            if (noticeexpandedView.isGroupExpanded(groupPosition)) {
                noticeexpandedView.collapseGroup(groupPosition)
                //convertView!!.imageView.isPressed = false

            } else {
                noticeexpandedView.expandGroup(groupPosition)
                //convertView!!.imageView.isPressed = true
            }
        }
        if (isExpanded) {
            //title?.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_up, 0);
            convertView.notice_arrow.isPressed = true  // 버튼클릭시 이미지 UP으로 변경
        } else {
            //title?.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_down, 0);
            convertView.notice_arrow.isPressed = false // 버튼다시클릭시 이미지 Down으로  변경
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size

    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView



        if (convertView == null) {

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_notice_chlid, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.notice_title)
        title?.text = getChild(groupPosition, childPosition)
        title?.setOnClickListener {

        }

        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }
}

