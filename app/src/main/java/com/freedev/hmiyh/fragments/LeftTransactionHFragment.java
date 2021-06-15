package com.freedev.hmiyh.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freedev.hmiyh.AdapterHandler;
import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.OnBackPressedListener;
import com.freedev.hmiyh.datas.HistoryTimer;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.adapters.HistoryAdapter;
import com.freedev.hmiyh.datas.HistoryTransaction;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftTransactionHFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftTransactionHFragment extends Fragment implements OnBackPressedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String APP_PREFERENCES_NAME ="UNIK_ID" ;
    private static final String APP_PREFERENCES = "setfile";
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRefUser;
    private ListView lv;
    private RelativeLayout window;
    private EditText payment,date, sum;
    private TextView btn_save;


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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("HistoryTransaction");

        lv = (ListView) v.findViewById(R.id.listHead);

        window =  v.findViewById(R.id.window);

        payment =  v.findViewById(R.id.payment);
        date =  v.findViewById(R.id.date);
        sum =  v.findViewById(R.id.sum);
        EditText editTexts[] = {payment,date,sum};

        btn_save =  v.findViewById(R.id.btn_save);

        ArrayList<HistoryTransaction> arrayOfHistories = new ArrayList<HistoryTransaction>();
        // Create the adapter to convert the array to views
        HistoryAdapter adapter = new HistoryAdapter(getContext(), arrayOfHistories);
        adapter.adapterhandler = new AdapterHandler() {
            @Override
            public void updateText(boolean controller) {
                System.out.println("####"+controller);
                if (controller){
                    window.setVisibility(View.VISIBLE);
                }
            }
        };

        SharedPreferences mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String id = mSettings.getString(APP_PREFERENCES_NAME, "UNIK_ID");
        for (int i = 0;i<3;i++){
            editTexts[i].addTextChangedListener(new TextWatcher(){
                @Override
                public void afterTextChanged(Editable s) {
                    if (!payment.getText().toString().equals("")&&!date.getText().toString().equals("")&&!sum.getText().toString().equals(""))
                    {
                        Log.d("$$$","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        btn_save.setBackgroundResource(R.drawable.backgraund_btn_timer);
                        btn_save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btn_save.setBackgroundResource(R.drawable.backgraund_btn_timer_down);
                                payment.getText().toString();
                                date.getText().toString();
                                sum.getText().toString();
                                System.out.println(payment.getText().toString()+" "+date.getText().toString()+" "+Double.parseDouble(sum.getText().toString()));

                                HistoryTransaction historyTransaction = new HistoryTransaction(
                                        id,
                                        payment.getText().toString(),
                                        sum.getText().toString(),
                                        date.getText().toString());
                                myRef.push().setValue(historyTransaction);

                                window.setVisibility(View.GONE);
                                payment.setText("");
                                date.setText("");
                                sum.setText("");
                            }
                        });

                    }
                    if (payment.getText().toString().equals("")||date.getText().toString().equals("")||sum.getText().toString().equals(""))
                    {
                        btn_save.setBackgroundResource(R.drawable.backgraund_btn_timer_down);
                    }
                    if (sum.getText().toString().contains(",")){
                        Toast toast = Toast.makeText(getContext(),
                                "Недопустимый символ - \",\"", Toast.LENGTH_LONG);
                        toast.show();
                        sum.setText("");
                    }
                    if (date.getText().toString().contains(",")){
                        Toast toast = Toast.makeText(getContext(),
                                "Недопустимый символ - \",\"", Toast.LENGTH_LONG);
                        toast.show();
                        date.setText("");
                    }
                }


                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }


        Query myQuery = myRef.orderByChild("id").equalTo(id);
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    HistoryTransaction historyTransaction = snapshot.getValue(HistoryTransaction.class);
                    adapter.add(historyTransaction);
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

        adapter.add(new HistoryTransaction(null,"Name of Payments", "Sum", "data"));
//        adapter.add(new HistoryTimer("Paymebt Hill Clab","Paymebt Hill Clab", "15$", "01.09.2021"));
//        adapter.add(new HistoryTimer("NON name card","NON name card", "13.75$", "22.02.2021"));
//        adapter.add(new HistoryTimer("Transaction","Transaction", "632.09$", "15.11.2021"));

        lv.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onBackPressed() {
        window.setVisibility(View.GONE);
    }
}