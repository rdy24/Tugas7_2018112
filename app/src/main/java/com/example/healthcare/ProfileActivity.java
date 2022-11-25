package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
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
    navigationView.setCheckedItem(R.id.nav_consult);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.nav_alarm:
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            break;
          case R.id.nav_news:
            Intent intent1 = new Intent(ProfileActivity.this, NewsActivity.class);
            startActivity(intent1);
            break;
          case R.id.nav_consult:
            Intent intent2 = new Intent(ProfileActivity.this, ConsultantActivity.class);
            startActivity(intent2);
            break;
          case R.id.nav_profile:
            Intent intent3 = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent3);
            break;
        }
        return true;
      }
    });
  }
}