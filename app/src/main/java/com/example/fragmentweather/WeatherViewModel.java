package com.example.fragmentweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel extends ViewModel {

    ////TODO please explain mutablelivedata
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private final List<String> recentSearches = new ArrayList<>();

    //// TODO why are you returning LiveData and not MutableLiveData
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

//    TODO why have you used a list? Why not an arraylist?
    public List<String> getRecentSearches() {
        return recentSearches;
    }


}

