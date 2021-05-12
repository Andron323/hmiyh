package com.freedev.hmiyh.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import com.freedev.hmiyh.HistoryTimer;
import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String APP_PREFERENCES_NAME ="UNIK_ID" ;
    private static final String APP_PREFERENCES = "setfile";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView timerView, btn_start, money;
    private Chronometer mChronometer;
    private Boolean start_stop = true;
    private int tikMon = 0;
    private int sec = 0;
    private double resMany = 0.0;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public TimerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timer, container, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("HistoryTimer");

        mChronometer = v.findViewById(R.id.timer22);

        money = v.findViewById(R.id.money);
        btn_start = v.findViewById(R.id.btn_start);

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                long base = SystemClock.elapsedRealtime() - mChronometer.getBase();
                int seconds = (int) (base / 1000) % 60;
                int minutes = (int) ((base / (1000 * 60)) % 60);
                int hours = (int) ((base / (1000 * 60 * 60)) % 24);

//                TODO
                int navStoit = 8;

                resMany = (double) navStoit * seconds / 3600;
                resMany = (double) navStoit * minutes / 60;
                resMany = resMany + navStoit * hours;

                Log.d("@@@@@@@@@@@@@@@@@@@@", String.valueOf(seconds) + " " + resMany);
                money.setText(String.valueOf(String.format("%8.2f", resMany)) + " $");

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (!start_stop) {
                    btn_start.setText("Start");
                    start_stop = true;
                    mChronometer.stop();

                    long base = SystemClock.elapsedRealtime() - mChronometer.getBase();
                    int seconds = (int) (base / 1000) % 60;
                    int minutes = (int) ((base / (1000 * 60)) % 60);
                    int hours = (int) ((base / (1000 * 60 * 60)) % 24);
                    Log.d("@@@@@@@@@@@@@@@@@@@@", String.valueOf(hours + " " + minutes + " " + " " + seconds));

                    SharedPreferences mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    String id = mSettings.getString(APP_PREFERENCES_NAME, "UNIK_ID");


                    HistoryTimer historyTimer = new HistoryTimer(id,hours + "h " + minutes + "m " + seconds+"s" , String.valueOf(String.format("%8.2f", resMany)), ((HomeActivity) Objects.requireNonNull(getActivity())).nowData());
                    myRef.push().setValue(historyTimer);
                } else {
                    btn_start.setText("Stop");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mChronometer.start();
                            mChronometer.setBase(SystemClock.elapsedRealtime());
                        }
                    }).start();
                    start_stop = false;
                    Log.d("@@@@@@@@@@@@@@@@@@@@", String.valueOf(start_stop));
                }
            }
        });
        return v;
    }
}