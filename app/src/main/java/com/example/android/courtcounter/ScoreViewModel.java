package com.example.android.courtcounter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    // Use Live data for the Team A score
    private MutableLiveData<Integer> ScoreTeamA; //ScoreTeamA is now LiveData, so observable

    //Constructor
    public ScoreViewModel(){
        ScoreTeamA = new MutableLiveData<>();
        ScoreTeamA.setValue(0);
    }
     public LiveData<Integer> getScoreTeamA() { return ScoreTeamA; }

     // You can also add business logic in the ViewModel
    public void add(int increment) {
        ScoreTeamA.setValue(ScoreTeamA.getValue()+increment);
    }
}
