package com.wholecrypto.airdrops;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private List<Fragment> activeFragments = new ArrayList<>();
    private DrawerLayout drawer;
    static String currentUserPhone;


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

            Fragment defaultFragment = new ActiveListFragment();
            FragmentTransaction mainFragmentTransaction = fragmentManager.beginTransaction();
            mainFragmentTransaction.add(R.id.main_activity_viewpager, defaultFragment);
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

            case R.id.nav_airdrops:
                listItemFragment = new ActiveListFragment();
            break;

            case R.id.nav_list_airdrop:
                listItemIntent = new Intent(this,SalesMainPageActivity.class);
                break;

            case R.id.nav_faq:
                listItemIntent = new Intent(this,FaqActivity.class);
                break;

/*
            case R.id.nav_portfolio:
                Toast.makeText(this,R.string.version_unavailable,Toast.LENGTH_SHORT).show();
                return true;

            case R.id.nav_block_ads:
                Toast.makeText(this,R.string.version_unavailable,Toast.LENGTH_SHORT).show();
                return true;

            case R.id.nav_notifications:
                Toast.makeText(this,R.string.version_unavailable,Toast.LENGTH_SHORT).show();
                return true;
*/
            case R.id.nav_terms_of_service:
                listItemIntent = new Intent(this,TermsOfServiceActivity.class);
                break;

            case R.id.nav_logout:
                //Log out the user
                AuthUI.getInstance().signOut(this);

            default:
                listItemFragment = new ActiveListFragment();
        }

        if (listItemFragment != null){
            //add selected fragment to the active fragments list
            if(!activeFragments.contains(listItemFragment)){
                activeFragments.add(listItemFragment);
            }
            navChosenFragment.replace(R.id.main_activity_viewpager,listItemFragment);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
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
        getSupportActionBar().setDisplayShowTitleEnabled(false); //Disables the app title

        MainActivity.SectionsPagerAdapter pagerAdapter = new MainActivity.SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.main_activity_viewpager);

        pager.setAdapter(pagerAdapter);

        //TabLayout tabLayout = findViewById(R.id.tab_dots);
        //tabLayout.setupWithViewPager(pager, true);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer){

            //Disable bug when clicking on hamburger icon back and forth has rotating effect. comment out to see effect.
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


    //Tell a view pager about its pages using a fragment pager adapter,this is the inner class for this adapter.
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount(){
            return 1;
        }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new ActiveListFragment();
                /*case 1:
                    return new UpcomingListFragment();
                    */
            }
            return null;
        }




    }


    private void setupAuthentication(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // user is already signed in
                    currentUserPhone = user.getPhoneNumber();
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
                                            new AuthUI.IdpConfig.PhoneBuilder().build())
                                    )
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

}

