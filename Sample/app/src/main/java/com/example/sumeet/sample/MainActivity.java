package com.example.sumeet.sample;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout aboutUs;
    AboutUsFragment aboutUsFragment;
    ContactFragment contactFragment;
    FrameLayout contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentMain = findViewById(R.id.content_main);
                contentMain.setVisibility(View.GONE);
                if (contactFragment == null) {
                    contactFragment = new ContactFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, contactFragment).commit();
            }
        });

//        LinearLayout lvContent = findViewById(R.id.content_main);
//
//        lvContent.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
//            @Override
//            public void onSwipeRight(){
//                System.out.print("Sadasdasdasdasdasd");
//                openInsurance(null);
//            }
//
//            @Override
//            public void onSwipeLeft(){
//
//                System.out.print("sadawrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//                openLoan(null);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        LinearLayout contentMain = findViewById(R.id.content_main);
        aboutUs = findViewById(R.id.about_us);
        contact = findViewById(R.id.contact);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (contentMain.getVisibility() == View.GONE) {
            if (contact == null) {
                contentMain.setVisibility(View.VISIBLE);
                aboutUs.setVisibility(View.GONE);
                getFragmentManager().popBackStack();
            } else if (aboutUs != null) {
                aboutUs.setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            } else {
                aboutUsFragment = new AboutUsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, aboutUsFragment).commit();
            }

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        aboutUs = findViewById(R.id.about_us);

        if (id == R.id.contact_us) {
            if (aboutUs == null) {
                aboutUsFragment = new AboutUsFragment();
            } else {
                aboutUs.setVisibility(View.VISIBLE);
            }

            LinearLayout contentMain = findViewById(R.id.content_main);
            contentMain.setVisibility(View.GONE);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, aboutUsFragment).commit();
        } else if (id == R.id.home) {
            LinearLayout contentMain = findViewById(R.id.content_main);
            if (contentMain.getVisibility() == View.GONE) {
                contentMain.setVisibility(View.VISIBLE);
                if (aboutUs != null) {
                    aboutUs.setVisibility(View.GONE);
                }
                getFragmentManager().popBackStack();
            }
        }
///
/// else if (id == R.id.nav_gallery)
//        {
//
//        }
//        else if (id == R.id.nav_slideshow) {

//        } else if (id == R.id.nav_manage) {

//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ContactFragment getContactFragment() {
        if (contactFragment == null) {
            contactFragment = new ContactFragment();
        }
        return contactFragment;
    }

    public void openLoan(View v) {
        TextView tvLoan = (TextView) findViewById(R.id.tvloan);
        TextView tvInsurance = (TextView) findViewById(R.id.tvinsurance);

        //assign the textview forecolor
        tvLoan.setTextColor(Color.RED);
        tvInsurance.setTextColor(Color.BLACK);

        LinearLayout loan = findViewById(R.id.loans);
        LinearLayout insurance = findViewById(R.id.insurances);
        loan.setVisibility(View.VISIBLE);
        insurance.setVisibility(View.GONE);
    }

    public void openInsurance(View v) {
        TextView tvLoan = (TextView) findViewById(R.id.tvloan);
        TextView tvInsurance = (TextView) findViewById(R.id.tvinsurance);

        //assign the textview forecolor
        tvLoan.setTextColor(Color.BLACK);
        tvInsurance.setTextColor(Color.RED);

        LinearLayout loan = findViewById(R.id.loans);
        LinearLayout insurance = findViewById(R.id.insurances);
        loan.setVisibility(View.GONE);
        insurance.setVisibility(View.VISIBLE);
    }

}
