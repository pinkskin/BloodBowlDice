package com.example.bloodbowldice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: 11/06/2020
// make the status bar disappear at all times
// MAKE THE MAIN SCREEN CHANGE WALLPAPER AS THE WEATHER CHANGES
// Make a reset button to return the weather to normal
// make a reset button for the settings page

public class MainActivity extends AppCompatActivity {
    Button oneBlockButton, twoBlockButton, threeBlockButton, resetButton,
            settingsButton, weatherButton;
    ImageView imageView1, imageView2, imageView3;
    TextView homeFanFactorTextView, homeCheerleadersTextView, homeAssistantCoachesTextView,
            awayFanFactorTextView, awayCheerleadersTextView, awayAssistantCoachesTextView;
    Intent intent;
    SharedPreferences sharedPreferences;
    Bundle bundle;
    TableResultsFragment tableResultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        *//*ActionBar actionBar = getActionBar();
        actionBar.hide();*/

        setContentView(R.layout.activity_main);

        // HERE IS WHERE WE EXPERIMENT
        // span yowww for custom string styles
        TextView spanTestTextView = (TextView) findViewById(R.id.spanTestTextView);
        SpannableStringBuilder spannableStringBuilder =
                new SpannableStringBuilder("Iamwhoiamhaha");
        spannableStringBuilder.setSpan(
                new TextAppearanceSpan(null, 1, 28, null, null),
                1,
                3,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanTestTextView.setText(spannableStringBuilder);

        // send the button clicked String to TableResultsFragment
        // coninuation of this is in every roll method; starts with the bundle word
        bundle = new Bundle();

        loadSettings();
        weatherButton();
        kickButton();
        kickEventButton();
        injuryButton();
        casualtyButton();
        oneBlockRoll();
        twoBlockRoll();
        threeBlockRoll();
        setSettingsButton();
    }

    private void loadSettings() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        homeFanFactorTextView = (TextView) findViewById(R.id.homeFanFactorTextView);
        homeCheerleadersTextView = (TextView) findViewById(R.id.homeCheerleadersTextView);
        homeAssistantCoachesTextView = (TextView) findViewById(R.id.homeAssistantCoachesTextView);
        awayFanFactorTextView = (TextView) findViewById(R.id.awayFanFactorTextView);
        awayCheerleadersTextView = (TextView) findViewById(R.id.awayCheerleadersTextView);
        awayAssistantCoachesTextView = (TextView) findViewById(R.id.awayAssistantCoachesTextView);

        homeFanFactorTextView.setText(sharedPreferences.getString("homeFanFactor",
                "0"));
        homeCheerleadersTextView.setText(sharedPreferences.getString("homeCheerleaders",
                "0"));
        homeAssistantCoachesTextView.setText(sharedPreferences
                .getString("homeAssistantCoaches", "0"));
        awayFanFactorTextView.setText(sharedPreferences.getString("awayFanFactor",
                "0"));
        awayCheerleadersTextView.setText(sharedPreferences.getString("awayCheerleaders",
                "0"));
        awayAssistantCoachesTextView.setText(sharedPreferences
                .getString("awayAssistantCoaches", "0"));
    }

    private void weatherButton() {
        Button weatherButton = (Button) findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableResultsFragment tableResultsFragment = new TableResultsFragment();
                tableResultsFragment.show(getSupportFragmentManager(), null);

                bundle.putString("rollClickedBundle", "weather");
                tableResultsFragment.setArguments(bundle);
            }
        });

    }

    private void kickButton() {
        Button kickButton = (Button) findViewById(R.id.kickButton);
        kickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableResultsFragment tableResultsFragment = new TableResultsFragment();
                tableResultsFragment.show(getSupportFragmentManager(), null);

                bundle.putString("rollClickedBundle", "kick");
                tableResultsFragment.setArguments(bundle);
            }
        });

    }
    private void kickEventButton() {
        Button kickEventButton = (Button) findViewById(R.id.kickEventButton);
        kickEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableResultsFragment tableResultsFragment = new TableResultsFragment();
                tableResultsFragment.show(getSupportFragmentManager(), null);

                bundle.putString("rollClickedBundle", "kickEvent");
                tableResultsFragment.setArguments(bundle);
            }
        });
    }
    private void injuryButton() {
        Button injuryButton = (Button) findViewById(R.id.injuryButton);
        injuryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableResultsFragment tableResultsFragment = new TableResultsFragment();
                tableResultsFragment.show(getSupportFragmentManager(), null);

                bundle.putString("rollClickedBundle", "injury");
                tableResultsFragment.setArguments(bundle);
            }
        });
    }
    private void casualtyButton() {
        Button casualtyButton = (Button) findViewById(R.id.casualtyButton);
        casualtyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableResultsFragment tableResultsFragment = new TableResultsFragment();
                tableResultsFragment.show(getSupportFragmentManager(), null);

                bundle.putString("rollClickedBundle", "casualty");
                tableResultsFragment.setArguments(bundle);
            }
        });
    }

    private void setSettingsButton() {
        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void oneBlockRoll() {
        Button oneBlockButton = (Button) findViewById(R.id.oneBlockButton);
        oneBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneBlockFragment oneBlockDialog = new OneBlockFragment();
                oneBlockDialog.show(getSupportFragmentManager(), null);

            }
        });
    }

    private void twoBlockRoll() {
        Button twoBlockButton = (Button) findViewById(R.id.twoBlockButton);
        twoBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoBlockFragment twoBlockDialog = new TwoBlockFragment();
                twoBlockDialog.show(getSupportFragmentManager(), null);
            }
        });
    }

    private void threeBlockRoll() {
        Button threeBlockButton = (Button) findViewById(R.id.threeBlockButton);
        threeBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // works independent of other pages
               /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.three_block_result, null));
                builder.create().show();*/

                ThreeBlockFragment threeBlockDialog = new ThreeBlockFragment();
                threeBlockDialog.show(getSupportFragmentManager(), null);
            }
        });
    }

}
