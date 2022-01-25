/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.courtcounter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBindings;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;
import android.widget.TextView;
import com.example.android.courtcounter.databinding.ActivityMainBinding;

/**
 * This activity keeps track of the basketball score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {

    // Tracks the score for Teams
    int scoreTeamB = 0;
    private ScoreViewModel mScoreViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Gets the View Model Reference and add databinding
        mScoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ScoreTeamA is LiveData, so we can observe it; assign a callback method for when ScoreTeamA's value is changed
        mScoreViewModel.getScoreTeamA().observe(this, ScoreTeamA -> {
            //Use the binding (instead of findViewById) to update score in the View
            binding.teamAScore.setText(String.valueOf(mScoreViewModel.getScoreTeamA().getValue()));
        });

    }
// region updates Team A using ViewModel with LiveData to store data
    /**
     * Update the score for Team A using the View Model method
     */
    public void addOneForTeamA(View v) {
        mScoreViewModel.add(1);
        //no need to update the View
    }/**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        mScoreViewModel.add(2);
    }
    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamA(View v) {
        mScoreViewModel.add(3);
    }

// endregion updates Team A using ViewModel to store data

// region updates Team B using Activity to store data
    /**
     * Update the score for Team B by 1
     */
    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }
    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }
    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
// endregion updates Team B using Activity to store data

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {

        //Using Activity class
        scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
    }
}
