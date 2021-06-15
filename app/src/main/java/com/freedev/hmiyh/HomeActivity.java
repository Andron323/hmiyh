package com.freedev.hmiyh;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freedev.hmiyh.datas.Graphix;
import com.freedev.hmiyh.datas.User;
import com.freedev.hmiyh.fragments.LeftFragment;
import com.freedev.hmiyh.fragments.RightFragment;
import com.freedev.hmiyh.fragments.CenterFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "GOOGLE_INFO";
    private static final String APP_PREFERENCES_NAME ="UNIK_ID" ;
    private static final String APP_PREFERENCES = "setfile";
    private RelativeLayout menu_dots,regWindow,headRelLayHome,settingsWindow;
    private Animation mFadeInAnimation, mFadeOutAnimation;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton SignInButton, OutButton;
    private int RC_SIGN_IN = 1;
    private EditText setWork, setInformation, setWorkDay, setWorkTime,setDohod;
    private TextView btn_save;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private SharedPreferences mSettings;;

    com.google.android.material.tabs.TabLayout tableLayout;
    ViewPager viewPagerHead, viewPagerBottom;



    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setWork = findViewById(R.id.setWork);
        setInformation = findViewById(R.id.setInformation);
        setWorkDay = findViewById(R.id.setWorkDay);
        setWorkTime = findViewById(R.id.setWorkTime);
        setDohod = findViewById(R.id.setDohod);
        btn_save = findViewById(R.id.btn_save);

        regWindow = findViewById(R.id.regWindow);
        settingsWindow = findViewById(R.id.settingsWindow);
        headRelLayHome = findViewById(R.id.headRelLayHome);

        mAuth = FirebaseAuth.getInstance();
        SignInButton = findViewById(R.id.sign_in_button);
        OutButton = findViewById(R.id.out_button);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        ifRegUser();

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Sing in", Toast.LENGTH_SHORT);
                toast.show();
                signIn();
                regWindow.setVisibility(View.GONE);
                headRelLayHome.setClickable(true);

            }
        });

        menu_dots = findViewById(R.id.Rlay);
        menu_dots = findViewById(R.id.Rlay);
        // подключаем файл анимации
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
//        mFadeInAnimation.setAnimationListener(animationFadeInListener);
//        mFadeOutAnimation.setAnimationListener(animationFadeOutListener);
        // при запуске начинаем с анимации исчезновения
        menu_dots.startAnimation(mFadeOutAnimation);


        tableLayout = findViewById(R.id.tab_layout);
        viewPagerHead = findViewById(R.id.view_pager_head);

        prepareViewPage(viewPagerHead);

        tableLayout.setupWithViewPager(viewPagerHead);
        tableLayout.selectTab(tableLayout.getTabAt(1));
        tableLayout.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {

                return false;
            }
        });
    }

    public void showDire() {
        regWindow = findViewById(R.id.regWindow);
        headRelLayHome = findViewById(R.id.headRelLayHome);
        regWindow.setVisibility(View.VISIBLE);
        headRelLayHome.setClickable(false);
    }

    public void ifRegUser() {
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser!=null){
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "NO NULL", Toast.LENGTH_SHORT);
//            toast.show();
//        }else {
//            regWindow.setVisibility(View.VISIBLE);
//            headRelLayHome.setClickable(false);
//        }
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {

            SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            String id = mSettings.getString(APP_PREFERENCES_NAME, "UNIK_ID");
            Log.d("%%%%%%%%%%%%%",id);

//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "NO NULL "+ id, Toast.LENGTH_LONG);
//            toast.show();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "LOADING... ", Toast.LENGTH_LONG);
            toast.show();
        } else {
            regWindow.setVisibility(View.VISIBLE);
            headRelLayHome.setClickable(false);
        }

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(HomeActivity.this);
        Log.d("Is Sing in ??????????", String.valueOf(account));
//        if (account==null&&activityName){
//            regWindow.setVisibility(View.VISIBLE);
//            headRelLayHome.setClickable(false);
//        }
//        if (account!=null&&!activityName){
//            FirebaseAuth.getInstance().signOut();
//            regWindow.setVisibility(View.VISIBLE);
//            headRelLayHome.setClickable(false);
//        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("#############","##########");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("222222222222222","222222222222222222222");
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d("3333333333333","3333333333333333333333");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                Log.d("44444444444444","4444444444444444444444");
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("555555555555555","55555555555555555555");
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("6666666666666","666666666666666666666");
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "isSuccessful", Toast.LENGTH_SHORT);
                    toast.show();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    updateUI(currentUser);
//                    showSettingsWindow();
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Failed", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

//    private void showSettingsWindow() {
//        settingsWindow.setVisibility(View.VISIBLE);
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                setWork.getText().toString();
//                setInformation.getText().toString();
//                setWorkDay.getText().toString();
//                setWorkTime.getText().toString();
//                setDohod.getText().toString();
//
//                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = mSettings.edit();
//                editor.putString("setWork", setWork.getText().toString());
//                editor.putString("setInformation", setInformation.getText().toString());
//                editor.putString("setWorkTime", setWorkTime.getText().toString());
//                editor.putString("setDohod", setDohod.getText().toString());
//                editor.apply();
//
//                settingsWindow.setVisibility(View.GONE);
//            }
//        });
//    }

    private void updateUI(FirebaseUser fUser) {
        Log.d("7777777777777","7777777777777777");

        settingsWindow.setVisibility(View.VISIBLE);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

                if(account !=null){
                    String personName = account.getDisplayName();
                    String personGiveName = account.getGivenName();
                    String personFamilyName = account.getFamilyName();
                    String personEmail = account.getEmail();
                    String personId = account.getId();
                    Uri personPhoto = account.getPhotoUrl();

                    Log.d("INFORMATION:\n",
                            "\npersonGiveName "+personGiveName
                                    +"\npersonEmail "+personEmail
                                    +"\npersonId "+personId
                                    +"\npersonPhoto "+personPhoto);

                    mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(APP_PREFERENCES_NAME, personId);
                    editor.apply();


                    double days = Double.parseDouble(setWorkDay.getText().toString());
                    double time = Double.parseDouble(setWorkTime.getText().toString());
                    double zp = Double.parseDouble(setDohod.getText().toString());

                    double hourCost = (zp/(days*time));

                    ArrayList<String> list = new ArrayList<>();
                    list.add("111111f");
                    list.add("100000f");
                    list.add("1880f");
                    list.add("50000f");
                    list.add("3240f");
                    list.add("123440f");
                    list.add("144444f");

                    String[] graph1 = {"111111f", "100000f", "1880f", "50000f", "3240f", "123440f", "144444f", "100000f", "100f", "200000f", "345432f"};
                    String[] graph2 = {"0f", "183000f", "188000f", "50000f", "324000f", "230000f", "188000f", "15000f", "126000f", "5000f", "33000f"};
                    float[] graph22 = {0f, 100000f, 188f, 50f, 324f, 123440f, 144444f, 100000f, 100f, 500000f, 345432f};
                    String[] legendArr = {"05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31"};

                    @SuppressLint("DefaultLocale") User user = new User(personId,
                            personGiveName,
                            personEmail,
                            String.valueOf(personPhoto),
                            setWork.getText().toString(),
                            setInformation.getText().toString(),
                            String.valueOf(hourCost));
                    Database.setInfoUser("User",user);
                    @SuppressLint("DefaultLocale") Graphix graphix = new Graphix(
                            personId,
                            "0",
                            "0",
                            "0",
                            "goal $/month",
                            "Tab to set",
                            "0",
                            "work intensity",
                            list,
                            list,
                            list);
                    Database.setInfoGraphix("Graphix",graphix);
                    @SuppressLint("DefaultLocale") Graphix graphix2 = new Graphix(
                            personId,
                            "60",
                            "25",
                            "8",
                            "Progress in day",
                            "Tab to set",
                            "0",
                            "",
                            list,
                            list,
                            list);
                    Database.setInfoGraphix("Graphix",graphix2);
//            myRef.push().setValue(user);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                settingsWindow.setVisibility(View.GONE);
            }
        });
    }

    public static String nowData(){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        return dateText;
    }

//    private static String getIdToM(String id){
//        SharedPreferences mSettings =getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
//        if(mSettings.contains(APP_PREFERENCES_NAME)) {
//            mSettings.getString(APP_PREFERENCES_NAME, "null");
//        }
//        return
//    }

    private void prepareViewPage(ViewPager viewPager_head) {
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());
        LeftFragment leftFragment = new LeftFragment();
        RightFragment rightFragment = new RightFragment();
        CenterFragment centerFragment = new CenterFragment();

        adapter.addFragment(leftFragment);
        adapter.addFragment(centerFragment);
        adapter.addFragment(rightFragment);

        viewPager_head.setAdapter(adapter);

    }

    private class HomeAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

        public HomeAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        menu_dots.clearAnimation();
    }

    Animation.AnimationListener animationFadeOutListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}