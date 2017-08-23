package com.punuo.sys.app.imgdot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.punuo.sys.app.imgdot.R;

import biz.zhidu.zdsdk.RecordInfo;

/**
 * Created by yangyuehui on 17/8/22.
 */

public class RecordAdapter extends BaseAdapter {
    private Context mContext;
    private RecordInfo[] list;
    public RecordAdapter(Context mContext, RecordInfo[] list){
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.record_list_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name.setText(list[position].toString());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        ViewHolder(View view){
            name = (TextView)view.findViewById(R.id.record_name);;
        }
    }
}
