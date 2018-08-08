package com.wholecrypto.airdrops;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PublishAirdropActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_airdrop);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.publishAirdropViewPager);

        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        Button publishAirdropBtn = findViewById(R.id.publish_airdrop_button);

        publishAirdropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitAirdropForm = new Intent(PublishAirdropActivity.this, newAirdropForm.class);
                startActivity(submitAirdropForm);
            }
        });

    }


//Tell a view pager about its pages using a fragment pager adapter,this is the inner class for this adapter.
private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount(){
            return 5;
        }

        @Override
    public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new PublishAirdropExposureFragment();
                case 1:
                    return new PublishAirdropTargetFragment();
                case 2:
                    return new PublishAirdropTrustFragment();
                case 3:
                    return new PublishAirdropPriceFragment();
                case 4:
                    return new PublishAirdropSupportFragment();
            }
            return null;
        }




}



}
