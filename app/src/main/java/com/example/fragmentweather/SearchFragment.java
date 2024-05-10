package com.example.fragmentweather;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

////TODO please indent your code
public class SearchFragment extends Fragment {

    private AutoCompleteTextView searchView;
    private Button button;
    private WeatherViewModel weatherViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ////TODO please explain why you have used: requireActivity()
        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        searchView = view.findViewById(R.id.searchView2);
        button = view.findViewById(R.id.button2);

        ////TODO please explain what is: new String[0]
        String[] cities = weatherViewModel.getRecentSearches().toArray(new String[0]);

        ////TODO please explain why you have used ArrayAdapter?
        ////TODO this is list of cities not city
        ArrayAdapter<String> city = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, cities);
        searchView.setAdapter(city);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchView.getText().toString();
                if (cityName != null ) {
                    ////TODO please explain why you have called both setSearchQuery & addSearchQuery
                    weatherViewModel.setSearchQuery(cityName);
                    weatherViewModel.addSearchQuery(cityName);
                    ////TODO why are you calling home frament here? you could have passed the data to the first tab
                    //// TODO do it using the ViewModel
                    loadfrag(new HomeFragment());

                }
            }
        });
         searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String cityName = (String) parent.getItemAtPosition(position);
          if (cityName != null ) {
            weatherViewModel.setSearchQuery(cityName);
              weatherViewModel.addSearchQuery(cityName);
            loadfrag(new HomeFragment());
             }
         }
         });
         return view;
    }

    private void loadfrag(HomeFragment homeFragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,homeFragment);
        //// TODO please explain what is addToBackStack
        ft.addToBackStack(null);

        ft.commit();
    }
}