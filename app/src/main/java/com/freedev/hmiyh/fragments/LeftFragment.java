package com.freedev.hmiyh.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freedev.hmiyh.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeftFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftFragment newInstance(String param1, String param2) {
        LeftFragment fragment = new LeftFragment();
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

        View v = inflater.inflate(R.layout.fragment_left, container, false);

        ChipNavigationBar chip_bar = v.findViewById(R.id.chip_bar);

        chip_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {

                Fragment selfragment = null;

                switch (i){
                    case R.id.time_h:
                        selfragment = new LeftTimerHFragment();
                             break;
                    case R.id.transak_h:
                        selfragment = new LeftTransactionHFragment();
                        break;
                }
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selfragment).commit();
            }
        });
        chip_bar.setItemSelected(R.id.time_h,true);


//        ListView lv = (ListView) v.findViewById(R.id.listHead);
//        ListView lvb = (ListView) v.findViewById(R.id.listBottom);
//
//        ArrayList<User> arrayOfUsers = new ArrayList<User>();
//        ArrayList<User> arrayOfUsers2 = new ArrayList<User>();
//        // Create the adapter to convert the array to views
//        HistoryAdapter adapter = new HistoryAdapter(getContext(), arrayOfUsers);
//        HistoryAdapterBottom adapter_bottom = new HistoryAdapterBottom(getContext(), arrayOfUsers2);
//
//        adapter.add(new User("Arabs Visa", "128.81$","13.03.2021"));
//        adapter.add(new User("Paymebt Hill Clab", "15$","01.09.2021"));
//        adapter.add(new User("NON name card", "13.75$","22.02.2021"));
//        adapter.add(new User("Transaction", "632.09$","15.11.2021"));
//        adapter.add(new User("Transaction", "632.09$","14.03.2021"));
//        adapter.add(new User("IT Osago24", "77$","27.08.2021"));
//
//
//        adapter_bottom.add(new User("0ch 2m 12s", "0.18$","5.09.2021"));
//        adapter_bottom.add(new User("1ch 18m 55s", "12.54$","4.09.2021"));
//        adapter_bottom.add(new User("1ch 54m 3s", "12.54$","28.08.2021"));
//        adapter_bottom.add(new User("6ch 6m 0s", "12.54$","28.08.2021"));
//        adapter_bottom.add(new User("2ch 14m 58s", "12.54$","27.08.2021"));
//        adapter_bottom.add(new User("12ch 8m 5s", "12.54$","26.08.2021"));
//        adapter_bottom.add(new User("1ch 18m 33s", "12.54$","26.08.2021"));
//        adapter_bottom.add(new User("0ch 14m 29s", "12.54$","25.08.2021"));
//        adapter_bottom.add(new User("0ch 35m 0s", "12.54$","25.08.2021"));
//        adapter_bottom.add(new User("3ch 1m 5s", "12.54$","24.07.2021"));
//        adapter_bottom.add(new User("0ch 46m 2s", "12.54$","23.07.2021"));
//        adapter_bottom.add(new User("1ch 59m 34s", "12.54$","2.06.2021"));
//        lv.setAdapter(adapter);
//        lvb.setAdapter(adapter_bottom);




//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            // argument position gives the index of item which is clicked
//            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
//            {
//                String selectedmovie=your_array_list.get(position);
//                Toast.makeText(getContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
//            }
//        });

        // Inflate the layout for this fragment
        return v;
    }
}