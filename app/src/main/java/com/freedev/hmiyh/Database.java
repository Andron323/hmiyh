package com.freedev.hmiyh;

import com.freedev.hmiyh.datas.Graphix;
import com.freedev.hmiyh.datas.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Database {


    public static void setInfoUser(String className, User classInfo){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(className);

        myRef.push().setValue(classInfo);
    }

    public static void setInfoGraphix(String className, Graphix classInfo){
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
