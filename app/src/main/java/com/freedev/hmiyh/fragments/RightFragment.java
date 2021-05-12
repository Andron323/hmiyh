package com.freedev.hmiyh.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.freedev.hmiyh.HistoryTimer;
import com.freedev.hmiyh.HomeActivity;
import com.freedev.hmiyh.R;
import com.freedev.hmiyh.User;
import com.freedev.hmiyh.adapters.AdapterReitin;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btn_out;
    private FirebaseAuth mAuth;

    private TextView data,data2,data3,nickname;
    private CircleImageView avatar;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private static final String APP_PREFERENCES_NAME ="UNIK_ID" ;
    private static final String APP_PREFERENCES = "setfile";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RightFragment newInstance(String param1, String param2) {
        RightFragment fragment = new RightFragment();
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

        View v = inflater.inflate(R.layout.fragment_right, container, false);

        data = (TextView) v.findViewById(R.id.data);
        data2 = (TextView) v.findViewById(R.id.data2);
        data3 = (TextView) v.findViewById(R.id.data3);
        nickname = (TextView) v.findViewById(R.id.nickname);
        avatar = (CircleImageView) v.findViewById(R.id.avatar_user);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        SharedPreferences mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String id = mSettings.getString(APP_PREFERENCES_NAME, "UNIK_ID");
        Log.d("%%%%%%%%%%%%%",id);
        Query myQuery = myRef.orderByChild("personId").equalTo(id);
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    User user = snapshot.getValue(User.class);
                    Log.d("$$$$$$$$$$$$$$$",user.personPhoto);
                    Picasso.with(getContext())
                            .load(user.personPhoto)
                            .placeholder(R.drawable.avatar)
                            .error(R.drawable.avatar)
                            .into(avatar);
                    nickname.setText(user.getPersonName());
                    data.setText(user.getPersonName());
                    data2.setText(user.getPersonWork());
                    data3.setText(user.getPersonInfo());
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

//        mAuth = FirebaseAuth.getInstance();
        btn_out = (Button) v.findViewById(R.id.btn_out);
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(),
                        "Кот ушел!", Toast.LENGTH_SHORT);
                toast.show();

//                GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                        .addApi(Auth.GOOGLE_SIGN_IN_API)
//                        .build();

                // Firebase sign out
//                mAuth.signOut();
                // Google sign out
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                ((HomeActivity) Objects.requireNonNull(getActivity())).ifRegUser(false);
            }
        });

        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter_spinet = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, countries);
        // Определяем разметку для использования при выборе элемента
//        adapter_spinet.setDropDownViewResource(R.layout.item_spinner);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter_spinet);
        spinner2.setAdapter(adapter_spinet);

        ListView lv = (ListView) v.findViewById(R.id.listReiting);

        ArrayList<User> arrayOfHistories = new ArrayList<User>();
        // Create the adapter to convert the array to views
        AdapterReitin adapter = new AdapterReitin(getContext(), arrayOfHistories);

        Query myQueryR = myRef;
        myQueryR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    User user = snapshot.getValue(User.class);
                    adapter.add(user);
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
//        adapter.add(new History("Worker543", "100.81$","Developer JAVA"));
//        adapter.add(new History("Hana Marta", "80$","Developer PHP"));
//        adapter.add(new History("Suchika", "79.75$","Frilanser"));
//        adapter.add(new History("Strelock5", "78.09$","Driver"));
//        adapter.add(new History("Andron323", "77.09$","Developer JAVA"));
//        adapter.add(new History("Frilanser", "40$","Pfotograf"));
//        adapter.add(new History("Sergio Asomer", "34$","Muvi maker"));
//        adapter.add(new History("IT123", "22$","Fantaner"));
//        adapter.add(new History("Mars", "16.9$","Makretolog"));
//        adapter.add(new History("Moet", "10.09$","Reklamer"));
//        adapter.add(new History("SpeedyWorker", "6$","Developer WWW"));
//        adapter.add(new History("12343253317872454", "5.50$","Copirayter"));

        lv.setAdapter(adapter);
        return v;
    }

    private String[] countries = { "Developer", "Empty", "Колумбия", "Чили", "Уругвай"};
}