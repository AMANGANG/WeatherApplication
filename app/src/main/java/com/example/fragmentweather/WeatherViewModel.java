package com.example.fragmentweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel extends ViewModel {
    /*
    ////TODO please explain mutablelivedata
    So basically it is a class that holds data meaning  that whenever the data present
    in the MutableLiveData changes, any observers are notified of that  change.

     */
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private final List<String> recentSearches = new ArrayList<>();

    /*
     //// TODO why are you returning LiveData and not MutableLiveData
     LiveData only has methods for observing changes, not for modifying the data
     unlike that in MutableLiveData.

     */
    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }

    public void addSearchQuery(String query) {
        recentSearches.add(query);
        setSearchQuery(query);
    }

    /*
   // TODO why have you used a list? Why not an arraylist?
   List allow you to create more flexible code meaning that
   the variable can hold any object that implements the List,


     */
    public List<String> getRecentSearches() {
        return recentSearches;
    }


}

