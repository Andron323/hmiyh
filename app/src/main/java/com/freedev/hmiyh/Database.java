package com.freedev.hmiyh;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Database {


    public static void setInfo(String className, User classInfo){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(className);

        myRef.push().setValue(classInfo);
    }

    public static User getInfo(String className){

        return null;
    }

//    private static User returnInfo(User user) {
//        assert user != null;
//        Log.d("@@@@@@@@@@@@", String.valueOf(user.getPersonEmail()));
//        return user;
//    }

}
