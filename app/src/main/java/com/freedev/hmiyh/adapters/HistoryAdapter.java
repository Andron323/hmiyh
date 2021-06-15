package com.freedev.hmiyh.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freedev.hmiyh.AdapterHandler;
import com.freedev.hmiyh.datas.HistoryTimer;
import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.datas.HistoryTransaction;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryAdapter extends ArrayAdapter<HistoryTransaction> {
    public HistoryAdapter(Context context, ArrayList<HistoryTransaction> histories) {
        super(context, 0, histories);
    }

    public AdapterHandler adapterhandler;
    public boolean controller = true;

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HistoryTransaction historyTransaction = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_left, parent, false);
        }


        // Lookup view for data population
        LinearLayout addLay = (LinearLayout) convertView.findViewById(R.id.addLay);
        TextView plas = (TextView) convertView.findViewById(R.id.plas);
        LinearLayout info_block =  convertView.findViewById(R.id.info_block);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvHome = (TextView) convertView.findViewById(R.id.summ);
        TextView tvData = (TextView) convertView.findViewById(R.id.data);
        // Populate the data into the template view using the data object

        if (position!=0) addLay.setVisibility(View.GONE);//TODO вдруг че было прост !0 и в фрагменте 0 место заполнялось
        if (position==0) addLay.setVisibility(View.VISIBLE);

        plas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("2222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+controller);
                    if (adapterhandler != null) adapterhandler.updateText(controller);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if(historyTransaction.id != null){
            tvName.setText(historyTransaction.name_time);
            tvHome.setText(historyTransaction.cost+" $");
            tvData.setText(historyTransaction.data);
            info_block.setVisibility(View.VISIBLE);
        }else info_block.setVisibility(View.GONE);

        // Return the completed view to render on screen
        return convertView;
    }
}