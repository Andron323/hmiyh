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

import com.freedev.hmiyh.Graphix;
import com.freedev.hmiyh.R;

import java.util.ArrayList;
import java.util.Arrays;

import ir.farshid_roohi.linegraph.ChartEntity;
import ir.farshid_roohi.linegraph.LineChart;

public class AdapterGraphix extends ArrayAdapter<Graphix> {
    public AdapterGraphix(Context context, ArrayList<Graphix> graphixes) {
        super(context, 0, graphixes);
    }

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
        if (graphix.graphic1!=null){
            ChartEntity firstChartEntity = new ChartEntity(Color.CYAN, graphix.graphic1);//invisible
            list.add(firstChartEntity);
        }
        if (graphix.graphic2!=null){
            ChartEntity secondChartEntity = new ChartEntity(Color.WHITE, graphix.graphic2);
            list.add(secondChartEntity);
        }


        if (graphix.name_progress.equals(""))progress_layout.setVisibility(View.GONE);
        if (graphix.name_graphic.equals(""))graphix_layout.setVisibility(View.GONE);

        // Populate the data into the template view using the data object
        textOfProgBar.setText(graphix.name_progress);
        onProgSpeed.setText(graphix.onProgSpeed);
        progress.setMax(100);
        progress.setProgress(graphix.progress);
        textGraf.setText(graphix.name_graphic);
        lineChart.setList(list);
        lineChart.setLegend(Arrays.asList(graphix.name_x_graphic));
        // Return the completed view to render on screen
        return convertView;
    }
}