package com.freedev.hmiyh.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.datas.Graphix;
import com.freedev.hmiyh.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import ir.farshid_roohi.linegraph.ChartEntity;
import ir.farshid_roohi.linegraph.LineChart;

public class AdapterGraphix extends ArrayAdapter<Graphix> {
    public AdapterGraphix(Context context, ArrayList<Graphix> graphixes) {
        super(context, 0, graphixes);
    }

    private float[] grapx1 = new float[7];
    private float[] grapx2 = new float[7];
    private String[] grapxName = new String[7];

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Graphix graphix = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_graphix_senter, parent, false);
        }
        // Lookup view for data population
        RelativeLayout progress_layout = convertView.findViewById(R.id.progress_layout);
        RelativeLayout graphix_layout = convertView.findViewById(R.id.graphix_layout);

        TextView textOfProgBar = (TextView) convertView.findViewById(R.id.textOfProgBar);
        TextView onProgSpeed = (TextView) convertView.findViewById(R.id.onProgSpeed);
        ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.progress);

        TextView textGraf = (TextView) convertView.findViewById(R.id.textGraf);
        //graphix
        LineChart lineChart = (LineChart) convertView.findViewById(R.id.lineChart);
        ArrayList list = new ArrayList<ChartEntity>();
        if (graphix.list!=null){
            for (int i = 0; i<graphix.list.size();i++){
                grapx1[i] = Float.parseFloat(graphix.list.get(i));
            }

            ChartEntity firstChartEntity = new ChartEntity(Color.WHITE,grapx1);//invisible
            list.add(firstChartEntity);
        }
        if (graphix.list2!=null){
            for (int i = 0; i<graphix.list2.size();i++){
                grapx2[i] = Float.parseFloat(graphix.list2.get(i));
            }
            ChartEntity secondChartEntity = new ChartEntity(Color.CYAN, grapx2);
            list.add(secondChartEntity);
        }

//        grapxName[0];
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+HomeActivity.nowData());


        if (graphix.name_progress.equals(""))progress_layout.setVisibility(View.GONE);
        if (graphix.name_graphic.equals(""))graphix_layout.setVisibility(View.GONE);

        textOfProgBar.setText(graphix.name_progress);
        onProgSpeed.setText(graphix.titelOnProgresBar);
        progress.setMax(Integer.parseInt(graphix.targetMonthCost));
        progress.setProgress(Integer.parseInt(graphix.progress));

        textGraf.setText(graphix.name_graphic);
        lineChart.setList(list);
        lineChart.setLegend(Arrays.asList(legendArr));
        // Return the completed view to render on screen
        return convertView;
    }
    private String[] legendArr = {"05/18", "05/19", "05/20", "05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28"};
}