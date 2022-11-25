package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.healthcare.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  private ActivityMainBinding binding;
  private MaterialTimePicker picker;
  private Calendar calendar;
  private AlarmManager alarmManager;
  private PendingIntent pendingIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // action bar
    Toolbar toolbar = binding.toolbar;
    setSupportActionBar(toolbar);
    drawer = binding.drawerLayout;
    NavigationView navigationView = binding.navView;
    toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setCheckedItem(R.id.nav_alarm);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.nav_alarm:
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            break;
          case R.id.nav_news:
            Intent intent1 = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(intent1);
            break;
          case R.id.nav_consult:
            Intent intent2 = new Intent(MainActivity.this, ConsultantActivity.class);
            startActivity(intent2);
            break;
          case R.id.nav_profile:
            Intent intent3 = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent3);
            break;
        }
        return true;
      }
    });

    //set alarm
    createNotificationChannel();
    binding.selectedTimeBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showTimePicker();
      }
    });
    binding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        setAlarm();
      }
    });
    binding.cancelAlarmBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        cancelAlarm();
      }
    });
  }

  private void cancelAlarm() {
    Intent intent = new Intent(this, AlarmReceiver.class);
    pendingIntent = PendingIntent.getBroadcast(this, 0,
            intent, 0);
    if (alarmManager == null) {
      alarmManager = (AlarmManager)
              getSystemService(Context.ALARM_SERVICE);
    }
    alarmManager.cancel(pendingIntent);
    Toast.makeText(this, "Alarm Cancelled",
            Toast.LENGTH_SHORT).show();
  }

  private void setAlarm() {
    try {
      alarmManager = (AlarmManager)
              getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(this, AlarmReceiver.class);
      pendingIntent = PendingIntent.getBroadcast(this, 0,
              intent, 0);
      alarmManager.setExact(AlarmManager.RTC_WAKEUP,
              calendar.getTimeInMillis(), pendingIntent);
      Toast.makeText(this, "Alarm Set",
              Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(this, "Alarm Not Set",
              Toast.LENGTH_SHORT).show();
    }
  }

  private void showTimePicker() {
    picker = new MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build();
    picker.show(getSupportFragmentManager(), "AlarmManager");
    picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (picker.getHour() > 12) {
          binding.selectedTime.setText(
                  String.format("%02d : %02d",
                          picker.getHour(), picker.getMinute())
          );
        } else {
          binding.selectedTime.setText("0"+ picker.getHour()
                  + " : " + "0"+ picker.getMinute());
        }
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,
                picker.getHour());
        calendar.set(Calendar.MINUTE,
                picker.getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
      }
    });
  }

  private void createNotificationChannel() {
    CharSequence name = "2018112";
    String description = "Ada Berita Baru Nih!!!";
    int importance = NotificationManager.IMPORTANCE_HIGH;
    NotificationChannel channel = new
            NotificationChannel("AlarmManager", name, importance);
    channel.setDescription(description);
    NotificationManager notificationManager =
            getSystemService(NotificationManager.class);
    notificationManager.createNotificationChannel(channel);

  }
}