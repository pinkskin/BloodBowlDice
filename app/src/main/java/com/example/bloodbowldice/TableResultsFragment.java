package com.example.bloodbowldice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TableResultsFragment extends DialogFragment {
    private String rollClicked = "";

    public BloodBowlAppMethods bbMethods;
    public String weatherRollStr, kickRollStr, kickOffRollStr, bounceBallStr, injuryRollStr,
            casualtyRollStr;
    public int homeFanFactor, homeCheerleaders, homeAssistantCoaches, awayFanFactor, awayCheerleaders,
            awayAssistantCoaches;
    public Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        homeFanFactor = Integer.parseInt(sharedPreferences.getString("homeFanFactor",
                "0"));
        homeCheerleaders = Integer.parseInt(sharedPreferences.getString("homeCheerleaders",
                "0"));
        homeAssistantCoaches = Integer.parseInt(sharedPreferences.getString("homeAssistantCoaches",
                "0"));
        awayFanFactor = Integer.parseInt(sharedPreferences.getString("awayFanFactor",
                "0"));
        awayCheerleaders = Integer.parseInt(sharedPreferences.getString("awayCheerleaders",
                "0"));
        awayAssistantCoaches = Integer.parseInt(sharedPreferences.getString("awayAssistantCoaches",
                "0"));

        // Constructor
        bbMethods = new BloodBowlAppMethods(homeFanFactor, awayFanFactor, homeCheerleaders,
                awayCheerleaders, homeAssistantCoaches, awayAssistantCoaches);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = requireActivity().getLayoutInflater()
                                        .inflate(R.layout.fragment_table_results, null);
        builder.setView(view);
        dialog = builder.create();
        TextView tableResultsTextView = (TextView) view.findViewById(R.id.tableResultsTextView);

        // code below is for displaying the right roll results in the textview
        rollClicked = getArguments().getString("rollClickedBundle");
        if (rollClicked.equals("weather")) {
            tableResultsTextView.setText(bbMethods.weatherRoll());
        } else if (rollClicked.equals("kick")) {
            tableResultsTextView.setText(bbMethods.kickRoll());
        } else if (rollClicked.equals("kickEvent")) {
            tableResultsTextView.setText(bbMethods.kickOffRoll());
        }else if (rollClicked.equals("injury")) {
            tableResultsTextView.setText(bbMethods.injuryRoll());
        } else {
            tableResultsTextView.setText(bbMethods.casualtyRoll());
        }

        ConstraintLayout tableResultLayout = (ConstraintLayout) view.
                findViewById(R.id.tableResultLayout);
        tableResultLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
