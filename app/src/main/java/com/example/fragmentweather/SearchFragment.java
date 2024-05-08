package com.example.fragmentweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;



public class SearchFragment extends Fragment {

private AutoCompleteTextView searchView;
private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        searchView=view.findViewById(R.id.searchView2);
        button=view.findViewById(R.id.button2);

        String[] cities = {"london", "delhi", "sydney", "bareilly", "lucknow"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, cities);
        searchView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchView.getText().toString();

                HomeFragment homeFragment = new HomeFragment();
                ForecastFragment forecastFragment=new ForecastFragment();

                Bundle args = new Bundle();
                args.putString("cityName", cityName);
                homeFragment.setArguments(args);
                forecastFragment.setArguments(args);


                getFragmentManager().beginTransaction()
                        .replace(R.id.container, homeFragment)
                        .commit();


          //      ForecastFragment forecastFragment=new ForecastFragment();
          //      Bundle arg = new Bundle();
           //     arg.putString("cityName", cityName);
             //   forecastFragment.setArguments(arg);

               // getFragmentManager().beginTransaction()
                 //       .replace(R.id.container,forecastFragment)
                   //     .commit();
            }
        });

        return view;
    }
}