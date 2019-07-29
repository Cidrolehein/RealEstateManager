package com.openclassrooms.realestatemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity to call classes before the main kotlin activity
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TextView textViewMain;
    private TextView textViewQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        this.textViewMain = findViewById(R.id.activity_main_activity_text_view_main); // change the wrong layout (activity_second to activity_main)
        this.textViewQuantity = findViewById(R.id.activity_main_activity_text_view_quantity);

        this.configureTextViewMain();
        this.configureTextViewQuantity();
        Utils.isInternetFormatedAvailable();
        Utils.isOnline();

    }

    /**
     * Start kotlin main activity here to restart when we call the back button
     */
    @Override
    public void onResume() {
        super.onResume();
        // Start main Kotlin activity with RecyclerView
        startMainActivity();
    }

    /**
     * Start the main kotlin activity with main recyclerView
     */
    private void startMainActivity() {
        Intent intent = new Intent(this, MainRecyclerViewActivity.class);
        startActivity(intent);
    }

    /**
     * Main text of MainActivity
     */
    private void configureTextViewMain() {
        this.textViewMain.setTextSize(15);
        this.textViewMain.setText(getString(R.string.valeur_premier_bien));
    }

    /**
     * Text quantity of MainActivity
     */
    private void configureTextViewQuantity() {
        String quantity = String.valueOf(Utils.convertDollarToEuro(100)); // change int to String
        this.textViewQuantity.setTextSize(20);
        this.textViewQuantity.setText(quantity);
    }
}
