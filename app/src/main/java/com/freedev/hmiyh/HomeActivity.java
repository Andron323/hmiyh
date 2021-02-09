package com.freedev.hmiyh;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

import ir.farshid_roohi.linegraph.ChartEntity;
import ir.farshid_roohi.linegraph.LineChart;
import pl.pawelkleczkowski.customgauge.CustomGauge;

public class HomeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CustomGauge customGauge = findViewById(R.id.headProgressBAQR);
        customGauge.setEndValue(1000);
        customGauge.setStartValue(0);
        customGauge.setPointSize(200);

        ProgressBar progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);
        progressBar.setProgress(80,true);



//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);

        LineChart chartEntity = findViewById(R.id.lineChart);
        ChartEntity firstChartEntity = new ChartEntity(Color.WHITE, graph1);//invisible
        ChartEntity secondChartEntity = new ChartEntity(Color.WHITE, graph2);

        ArrayList list = new ArrayList<ChartEntity>();
        list.add(firstChartEntity);
        list.add(secondChartEntity);
        chartEntity.setLegend(Arrays.asList(legendArr));
        chartEntity.setList(list);
    }

    private  float[] graph1 = {0f, 183000f, 188000f, 50000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f};
    private float[] graph2 = {0f, 183000f, 188000f, 50000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f};
    private String[] legendArr = {"05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31"};
}