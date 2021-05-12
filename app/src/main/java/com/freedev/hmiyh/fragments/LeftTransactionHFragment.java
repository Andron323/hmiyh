package com.freedev.hmiyh.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freedev.hmiyh.HistoryTimer;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.adapters.HistoryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftTransactionHFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftTransactionHFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeftTransactionHFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftTransactionHFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftTransactionHFragment newInstance(String param1, String param2) {
        LeftTransactionHFragment fragment = new LeftTransactionHFragment();
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
        View v = inflater.inflate(R.layout.fragment_left_transaction_h, container, false);

        ListView lv = (ListView) v.findViewById(R.id.listHead);

        ArrayList<HistoryTimer> arrayOfHistories = new ArrayList<HistoryTimer>();
        // Create the adapter to convert the array to views
        HistoryAdapter adapter = new HistoryAdapter(getContext(), arrayOfHistories);

        adapter.add(new HistoryTimer("Arabs Visa","Arabs Visa", "128.81$", "13.03.2021"));
        adapter.add(new HistoryTimer("Paymebt Hill Clab","Paymebt Hill Clab", "15$", "01.09.2021"));
        adapter.add(new HistoryTimer("NON name card","NON name card", "13.75$", "22.02.2021"));
        adapter.add(new HistoryTimer("Transaction","Transaction", "632.09$", "15.11.2021"));
        adapter.add(new HistoryTimer("Transaction","Transaction", "632.09$", "14.03.2021"));
        adapter.add(new HistoryTimer("IT Osago24","IT Osago24", "77$", "27.08.2021"));
        adapter.add(new HistoryTimer("Nas12","Nas12", "727$", "27.38.2021"));
        adapter.add(new HistoryTimer("IT Osago24","IT Osago24", "77$", "7.08.2021"));
        adapter.add(new HistoryTimer("Osago24","Osago24", "987$", "27.06.2021"));
        adapter.add(new HistoryTimer("Asdfr","Asdfr", "76$", "21.08.2021"));
        adapter.add(new HistoryTimer("Sev rev","Sev rev", "7437$", "2.08.2021"));

        lv.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }
}