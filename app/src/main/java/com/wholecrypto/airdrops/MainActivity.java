package com.wholecrypto.airdrops;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static final int RC_SIGN_IN = 1;
    private boolean userSignedIn = false;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private List<Fragment> activeFragments = new ArrayList<>();
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupAuthentication();

        MobileAds.initialize(this, "ca-app-pub-3677276785158147~4334258306");

    }


    protected void onResume(){
        super.onResume();
        Log.e(TAG,"onResume");

        Log.e(TAG,"Attaching AuthStateListener");
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    protected void onPostResume(){
        super.onPostResume();
        Log.e(TAG,"onPostResume");

            Fragment defaultFragment = new AirdropsListFragment();
            FragmentTransaction mainFragmentTransaction = fragmentManager.beginTransaction();
            mainFragmentTransaction.add(R.id.content_frame, defaultFragment);
            Log.e(TAG, "commit");
            mainFragmentTransaction.commit();

            if(!activeFragments.contains(defaultFragment)){
                activeFragments.add(defaultFragment);
            }


    }

    protected void onPause(){
        super.onPause();
        Log.e(TAG,"onPause");
        if(mAuthStateListener != null){
            Log.e(TAG,"REMOVING AuthStateListener");
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }

//removing all active fragments, to avoid duplication
        if(!activeFragments.isEmpty()){
            Log.e(TAG,"Removing all Active Fragments");
            FragmentTransaction removeAllFragments = fragmentManager.beginTransaction();
            for(Fragment activeFragment:activeFragments){
                removeAllFragments.remove(activeFragment);
            }
            removeAllFragments.commit();
        }

        }

    protected void onStop(){
        super.onStop();
        Log.e(TAG,"onStop");
    }

    protected void onDestroy(){
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        Fragment listItemFragment = null;
        Intent listItemIntent = null;
        FragmentTransaction navChosenFragment = fragmentManager.beginTransaction();

        switch(id) {

            case R.id.nav_list_airdrops:
                listItemFragment = new AirdropsListFragment();
            break;

            case R.id.nav_publish_airdrop:
                listItemIntent = new Intent(this,PublishAirdropActivity.class);
                break;

            default:
                listItemFragment = new AirdropsListFragment();
        }

        if (listItemFragment != null){
            //add selected fragment to the active fragments list
            if(!activeFragments.contains(listItemFragment)){
                activeFragments.add(listItemFragment);
            }
            navChosenFragment.replace(R.id.content_frame,listItemFragment);
            navChosenFragment.commit();
        }
        else {
            startActivity(listItemIntent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_menu_faq:
                Intent intent = new Intent(this,FaqActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_menu_logout:
                AuthUI.getInstance().signOut(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                userSignedIn = true;
                Log.e(TAG,"onActivityResult: RESULT_OK");
            }
            else if (resultCode == RESULT_CANCELED){
                //Prevent endless loop bug from going to MainActivity and SignInActivity back and forth
                finish();
            }

        }
    }

    private void setupUI(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer){
            //Disable bug when clicking on hamburger icon. comment out to see effect.
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0); // this disables the arrow @ completed state
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0); // this disables the animation
            }

        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView mNavigationView;
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void setupAuthentication(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // user is already signed in
                }
                else {
                    //user is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setTosUrl("https://superapp.example.com/terms-of-service.html")
                                    .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.PhoneBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

}

