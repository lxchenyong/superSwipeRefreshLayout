
package net.mobctrl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.mobctrl.bean.Bean;
import net.mobctrl.treerecyclerview.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 维修单列表显示时所使用的适配器
 */
public class RepairOrderAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private final LayoutInflater inflater;
    private List<Bean> data;

    public RepairOrderAdapter(Context context, List<Bean> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.sticky_item, null);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.textTile = (TextView) convertView.findViewById(R.id.text_title);
            convertView.setTag(holder);
        }

        Bean bean = (Bean) getItem(position);
        holder.textTile.setText(bean.getTitle());



        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.sticky_header, parent, false);
        }

        HeaderViewHolder holder = (HeaderViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new HeaderViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.sticky_head);
            convertView.setTag(holder);
        }
        Bean bean = (Bean) getItem(position);
        holder.text.setText(bean.getPage());

        return convertView;
    }

//    @Override
//    public long getHeaderId(int position) {
//        return vehicleSummaryGroups.get(position).getGroupName().hashCode() ;
//
//    }

    /**
     * ViewHolder
     */
    private final class ViewHolder {
        public TextView textTile;

    }

    /**
     * Header View Holder
     */
    private class HeaderViewHolder {
        TextView text; // 日期
    }
}
