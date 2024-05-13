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
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        ////TODO please explain this
        EdgeToEdge basically allows  app to use the whole screen of the phone
        setContentView(R.layout.activity_main);
        This line basically tells our app what to show on the screen when this part of the app is running.
        */


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btnNav);

        ////TODO please don't use deprecated code
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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
        /*
        //TODO please explain what is getSupportFragmentManager()
         So getSupportFragmentManager() basically just  returns an instance of FragmentManager and
         fragmentManager  manages Fragment instances and
         provides methods to interact with them.
        */
        FragmentManager fm = getSupportFragmentManager();
        /*
        ////TODO please explain what is beginTransaction()
        It creates and returns a new instance of FragmentTransaction.
        FragmentTransaction helps to perform actions on fragments,
        like adding, removing.
        */

        FragmentTransaction ft = fm.beginTransaction();
        /*
        ////TODO please explain diff b/w add and replace
         Add->  It add the fragment and if fragment is already present it the new fragment
        on top of it
         Replace-> It replaces the new fragment with existing fragment but if there are no
         fragment it works same like add.
         */
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ////TODO please explain this
        // It basically used to execute all the changes made in the  transaction now.
        ft.commit();
    }

}