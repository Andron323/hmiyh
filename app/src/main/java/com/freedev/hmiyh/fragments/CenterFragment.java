package com.freedev.hmiyh.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.freedev.hmiyh.Graphix;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.adapters.AdapterGraphix;

import java.util.ArrayList;

import pl.pawelkleczkowski.customgauge.CustomGauge;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CenterFragment extends Fragment {

    private ImageView imgUp, imgDown;
    private RelativeLayout show_video, camera_view_rel, timerRel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CenterFragment newInstance(String param1, String param2) {
        CenterFragment fragment = new CenterFragment();
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

    //    private OnSwipeTouchListener onSwipeTouchListener;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_senter, container, false);
//        onSwipeTouchListener = new OnSwipeTouchListener(getContext(), v.findViewById(R.id.relativeLayout));



        CustomGauge customGauge = v.findViewById(R.id.headProgressBAQR);
        customGauge.setEndValue(1000);
        customGauge.setStartValue(0);
        customGauge.setPointSize(77);

        ListView lv = (ListView) v.findViewById(R.id.list_graphix);

        ArrayList<Graphix> arrayOfGraphix = new ArrayList<Graphix>();
        // Create the adapter to convert the array to views
        AdapterGraphix adapter = new AdapterGraphix(getContext(), arrayOfGraphix);

        adapter.add(new Graphix(
                "goal $/month",
                "35%",
                35,
                "work intensity",
                graph1,
                graph2,
                legendArr));
        adapter.add(new Graphix(
                "Progress in day",
                "12%",
                12,
                "",
                graph1,
                graph2,
                legendArr));
//        adapter.add(new Graphix(
//                "Test",
//                "90",
//                90,
//                "",
//                graph1,
//                graph2,
//                legendArr));
//        adapter.add(new Graphix(
//                "Test of progress",
//                "76",
//                76,
//                "Sam graphix",
//                null,
//                graph22,
//                legendArr));

        lv.setAdapter(adapter);

//        ProgressBar progressBar = v.findViewById(R.id.progress);
//        progressBar.setMax(100);
//        progressBar.setProgress(70,true);


//        LineChart chartEntity = v.findViewById(R.id.lineChart);
//        ChartEntity firstChartEntity = new ChartEntity(Color.WHITE, graph1);//invisible
//        ChartEntity secondChartEntity = new ChartEntity(Color.WHITE, graph2);
//
//        ArrayList list = new ArrayList<ChartEntity>();
//        list.add(firstChartEntity);
//        list.add(secondChartEntity);
//        chartEntity.setLegend(Arrays.asList(legendArr));
//        chartEntity.setList(list);


        imgUp = v.findViewById(R.id.imgUp);
        imgUp.setImageAlpha(400);
        imgDown = v.findViewById(R.id.imgDown);
        imgUp.setImageAlpha(400);
        show_video = v.findViewById(R.id.show_video);
        camera_view_rel = v.findViewById(R.id.camera_view_rel);
        timerRel = v.findViewById(R.id.timerRel);

        imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeUP();
            }
        });
        imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera_view_rel.getVisibility() == View.VISIBLE) {
                    final Animation hide = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.hide_video);
                    hide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
//                    timerRel.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            camera_view_rel.setVisibility(View.GONE);
//                            timerRel.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    show_video.startAnimation(hide);
                }
            }
        });

        Fragment timerFragment = new TimerFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_timer, timerFragment);
        ft.commit();


//        ImageView imgUp = v.findViewById(R.id.imgUp);
//        // Animation
//        Animation animUpDown;
//        // load the animation
//        animUpDown = AnimationUtils.loadAnimation(getContext(),
//                R.anim.up_dwon);
//        // start the animation
//        imgUp.startAnimation(animUpDown);

        return v;
    }

    private void swipeUP() {
        if (camera_view_rel.getVisibility() == View.GONE) {
            camera_view_rel.setVisibility(View.VISIBLE);
            final Animation show = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.show_video);
            show_video.startAnimation(show);
//                    timerRel.setVisibility(View.GONE);
        }
    }

//    public static class OnSwipeTouchListener implements View.OnTouchListener {
//        private final GestureDetector gestureDetector;
//        Context context;
//        OnSwipeTouchListener(Context ctx, View mainView) {
//            gestureDetector = new GestureDetector(ctx, new GestureListener());
//            mainView.setOnTouchListener(this);
//            context = ctx;
//        }
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            return gestureDetector.onTouchEvent(event);
//        }
//        public class GestureListener extends
//                GestureDetector.SimpleOnGestureListener {
//            private static final int SWIPE_THRESHOLD = 100;
//            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return true;
//            }
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                boolean result = false;
//                try {
//                    float diffY = e2.getY() - e1.getY();
//                    float diffX = e2.getX() - e1.getX();
//                    if (Math.abs(diffX) > Math.abs(diffY)) {
//                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                            if (diffX > 0) {
//                                onSwipeRight();
//                            } else {
//                                onSwipeLeft();
//                            }
//                            result = true;
//                        }
//                    }
//                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                        if (diffY > 0) {
//                            onSwipeBottom();
//                        } else {
//                            onSwipeTop();
//                        }
//                        result = true;
//                    }
//                }
//                catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//                return result;
//            }
//        }
//        void onSwipeRight() {
//            Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeRight();
//        }
//        void onSwipeLeft() {
//            Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeLeft();
//        }
//        void onSwipeTop() {
//            Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeTop();
//        }
//        void onSwipeBottom() {
//            Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeBottom();
//        }
//        interface onSwipeListener {
//            void swipeRight();
//            void swipeTop();
//            void swipeBottom();
//            void swipeLeft();
//        }
//        onSwipeListener onSwipe;
//      }

    private float[] graph1 = {111111f, 100000f, 1880f, 50000f, 3240f, 123440f, 144444f, 100000f, 100f, 200000f, 345432f};
    private float[] graph2 = {0f, 183000f, 188000f, 50000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f};
    private float[] graph22 = {0f, 100000f, 188f, 50f, 324f, 123440f, 144444f, 100000f, 100f, 500000f, 345432f};
    private String[] legendArr = {"05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31"};
}