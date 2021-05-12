package com.freedev.hmiyh.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freedev.hmiyh.HistoryTimer;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.adapters.HistoryAdapterBottom;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftTimerHFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftTimerHFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeftTimerHFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftTimerHFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftTimerHFragment newInstance(String param1, String param2) {
        LeftTimerHFragment fragment = new LeftTimerHFragment();
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
        View v = inflater.inflate(R.layout.fragment_left_timer_h, container, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("HistoryTimer");

        ListView lvb = (ListView) v.findViewById(R.id.listBottom);

        ArrayList<HistoryTimer> arrayOfUsers2 = new ArrayList<HistoryTimer>();
        HistoryAdapterBottom adapter_bottom = new HistoryAdapterBottom(getContext(), arrayOfUsers2);

        Query myQuery = myRef;
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    HistoryTimer historyTimer = snapshot.getValue(HistoryTimer.class);
                    adapter_bottom.add(historyTimer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        adapter_bottom.add(new History("0ch 2m 12s", "0.18$", "5.09.2021"));
//        adapter_bottom.add(new History("1ch 18m 55s", "12.54$", "4.09.2021"));
//        adapter_bottom.add(new History("1ch 54m 3s", "12.54$", "28.08.2021"));
//        adapter_bottom.add(new History("6ch 6m 0s", "12.54$", "28.08.2021"));
//        adapter_bottom.add(new History("2ch 14m 58s", "12.54$", "27.08.2021"));
//        adapter_bottom.add(new History("12ch 8m 5s", "12.54$", "26.08.2021"));
//        adapter_bottom.add(new History("1ch 18m 33s", "12.54$", "26.08.2021"));
//        adapter_bottom.add(new History("0ch 14m 29s", "12.54$", "25.08.2021"));
//        adapter_bottom.add(new History("0ch 35m 0s", "12.54$", "25.08.2021"));
//        adapter_bottom.add(new History("3ch 1m 5s", "12.54$", "24.07.2021"));
//        adapter_bottom.add(new History("0ch 46m 2s", "12.54$", "23.07.2021"));
//        adapter_bottom.add(new History("1ch 59m 34s", "12.54$", "2.06.2021"));

        lvb.setAdapter(adapter_bottom);
        // Inflate the layout for this fragment
        return v;
    }
}