package com.freedev.hmiyh.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.freedev.hmiyh.datas.HistoryTimer;
import com.freedev.hmiyh.R;

import java.util.ArrayList;

public class HistoryAdapterBottom extends ArrayAdapter<HistoryTimer> {
    public HistoryAdapterBottom(Context context, ArrayList<HistoryTimer> histories) {
        super(context, 0, histories);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HistoryTimer historyTimer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_left, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.time);
        TextView tvHome = (TextView) convertView.findViewById(R.id.summ_bottom);
        TextView tvData = (TextView) convertView.findViewById(R.id.data_bottom);
        // Populate the data into the template view using the data object
        tvName.setText(historyTimer.name_time);
        tvHome.setText(String.format("%.2f",Double.parseDouble(historyTimer.cost))+" $");
        tvData.setText(historyTimer.data);
        // Return the completed view to render on screen
        return convertView;
    }
}