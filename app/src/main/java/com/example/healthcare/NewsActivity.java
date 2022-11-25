package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class NewsActivity extends AppCompatActivity {
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  RecyclerView recylerView;
  RecyclerView recylerViewTrending;
  String s1[], s2[];
  int images[] = {
          R.drawable.image1,
          R.drawable.image2,
          R.drawable.image3,
          R.drawable.image4,
          R.drawable.image1,
  };
  int trendingImages[] = {
          R.drawable.trending,
          R.drawable.trending,
          R.drawable.trending,
          R.drawable.trending,
          R.drawable.trending,
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    recylerView = findViewById(R.id.recyclerView);
    s1 = getResources().getStringArray(R.array.tanggal);
    s2 = getResources().getStringArray(R.array.subjudul);
    HealthAdapter appAdapter = new HealthAdapter(this,s1,s2,images);
    recylerView.setAdapter(appAdapter);
    recylerView.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false));

    recylerViewTrending = findViewById(R.id.recyclerViewTrending);
    TrendingAdapter trendingAdapter = new TrendingAdapter(this,s2,trendingImages);
    recylerViewTrending.setAdapter(trendingAdapter);
    recylerViewTrending.setLayoutManager(new LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false));
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
    drawer.addDrawerListener(toggle);
    toggle.setDrawerIndicatorEnabled(true);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setCheckedItem(R.id.nav_news);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.nav_alarm:
            Intent intent = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(intent);
            break;
          case R.id.nav_news:
            Intent intent1 = new Intent(NewsActivity.this, NewsActivity.class);
            startActivity(intent1);
            break;
          case R.id.nav_consult:
            Intent intent2 = new Intent(NewsActivity.this, ConsultantActivity.class);
            startActivity(intent2);
            break;
          case R.id.nav_profile:
            Intent intent3 = new Intent(NewsActivity.this, ProfileActivity.class);
            startActivity(intent3);
            break;
        }
        return true;
      }
    });

  }
}