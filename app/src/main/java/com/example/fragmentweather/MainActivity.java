package com.example.fragmentweather;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////TODO please explain this
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.btnnav);

        ////TODO please don't use deprecated code
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.home) {
                    loadfrag(new HomeFragment(), true);


                } else if (id == R.id.forecast) {
                    loadfrag(new ForecastFragment(), false);
                } else {
                    loadfrag(new SearchFragment(), false);
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    public void loadfrag(Fragment fragment, Boolean flag) {
        ////TODO please explain what is getSupportFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        ////TODO please explain what is beginTransaction()
        FragmentTransaction ft = fm.beginTransaction();
        ////TODO please explain diff b/w add and replace
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ////TODO please explain this
        ft.commit();
    }

}