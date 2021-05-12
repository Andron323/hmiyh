package com.freedev.hmiyh.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freedev.hmiyh.HistoryTimer;
import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.R;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryAdapter extends ArrayAdapter<HistoryTimer> {
    public HistoryAdapter(Context context, ArrayList<HistoryTimer> histories) {
        super(context, 0, histories);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HistoryTimer historyTimer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_left, parent, false);
        }
        // Lookup view for data population
        LinearLayout addLay = (LinearLayout) convertView.findViewById(R.id.addLay);
        TextView plas = (TextView) convertView.findViewById(R.id.plas);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvHome = (TextView) convertView.findViewById(R.id.summ);
        TextView tvData = (TextView) convertView.findViewById(R.id.data);
        // Populate the data into the template view using the data object

        if (position!=0) addLay.setVisibility(View.GONE);
        if (position==0) addLay.setVisibility(View.VISIBLE);

        plas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("2222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//                    HomeActivity ha = new HomeActivity();
//                    ((HomeActivity) Objects.requireNonNull(ha)).showDire();
                    View view = new LayoutInflater(getContext()) {
                        @Override
                        public LayoutInflater cloneInContext(Context newContext) {
                            ((HomeActivity) Objects.requireNonNull(newContext)).showDire();
                            return null;
                        }
                    }.inflate(R.layout.activity_home,null);

// Получаем доступ к вью элементам
                    RelativeLayout regWindow = view.findViewById(R.id.regWindow);
                    regWindow.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tvName.setText(historyTimer.name_time);
        tvHome.setText(historyTimer.cost+" $");
        tvData.setText(historyTimer.data);
        // Return the completed view to render on screen
        return convertView;
    }
}