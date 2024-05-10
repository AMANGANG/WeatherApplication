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
        /*
        ////TODO please explain why you have used: requireActivity()
        It returns the FragmentActivity this fragment is currently associated
        Because if we use "this" it scope is  limited to
        fragment when we change fragment it gets destroyed but
        with required activity it scope change to activity rather than fragment
         */
        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        searchView = view.findViewById(R.id.searchView2);
        button = view.findViewById(R.id.button2);
        /*
        ////TODO please explain what is: new String[0]
         Its  a way to basically specify the type of array to create (String[]),
         but it's an empty array because we don't know the size of the array beforehand.
         */
        String[] cities = weatherViewModel.getRecentSearches().toArray(new String[0]);
        /*
        ////TODO please explain why you have used ArrayAdapter?
        ArrayAdapter  works well when we have data in arrays or lists that we want
        to display in a ListView . In this case i wanted that when a user starts typing in the searchView,
        it should suggest city names that match what the user has typed so far.

         */
        ////TODO this is list of cities not city
        ArrayAdapter<String> Cities = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, cities);
        searchView.setAdapter(Cities);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = searchView.getText().toString();
                if (cityName != null ) {
                    /*
                    ////TODO please explain why you have called both setSearchQuery & addSearchQuery
                    I have setSearchQuery because i want to send the cityName to other fragments and
                    addSearchQuery because i want to store that cityName and store in a list
                    so i can display it later

                     */
                    weatherViewModel.setSearchQuery(cityName);
                    weatherViewModel.addSearchQuery(cityName);
                    /*
                    ////TODO why are you calling home frament here? you could have passed the data to the first tab
                    So i wanted that when user clicked the button or click on list cityName it get redirected to
                    Home Fragment where it can see the weather changes.
                     */
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
        /*
        //// TODO please explain what is addToBackStack
         So when you replace a fragment and call addToBackStack(null), the current fragment
         will be stopped and will be added to the back stack. If the user navigates back,
         the previous fragment state will be restored.


         */
        ft.addToBackStack(null);

        ft.commit();
    }
}