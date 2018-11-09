package com.example.zxa01.add3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Card RecyclerView
    private RecyclerView mRecyclerView;
    private ArrayList<Card> mCardList;
    private CardAdapter mAdapter;

    // preference
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 0. Theme change
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(pref.getBoolean("theme_switch",true) ? android.R.style.Holo_Light_ButtonBar : android.R.style.Theme_DeviceDefault);

        setContentView(R.layout.activity_main);

        // Hide top bar - requestWindowFeature(Window.FEATURE_OPTIONS_PANEL);
        // Full Screen - this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 1. Top bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(R.string.app_subname);
        setSupportActionBar(toolbar);

        // 2. Side bar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 3. CardView
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardList = new ArrayList<>(); // Initialize
        mAdapter = new CardAdapter(this, mCardList);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                Snackbar.make(toolbar, "Click About us", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_lang:
                // change language
                UpdateResources updateResources = new UpdateResources();
                updateResources.update(MainActivity.this);
                // reopen
                finish();
                startActivity(getIntent());
                break;
            case R.id.action_reload:
                if(mCardList.isEmpty()){
                    initializeData();
                } else{
                    mCardList.clear();
                    mAdapter.notifyDataSetChanged();
                }
                Snackbar.make(toolbar, "Click Reload", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeData() {
        String[] titleList = getResources().getStringArray(R.array.array_title);
        String[] infoList = getResources().getStringArray(R.array.array_info);
        TypedArray arrayImageResources =getResources().obtainTypedArray(R.array.array_images);
        mCardList.clear();
        // 刪除方式 - arrayImageResources.recycle();
        for(int i=0;i<arrayImageResources.length();i++){
            mCardList.add(new Card(titleList[i],infoList[i],arrayImageResources.getResourceId(i,0)));
        }
        mAdapter.notifyDataSetChanged();
    }
}
