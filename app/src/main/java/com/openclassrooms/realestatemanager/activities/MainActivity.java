package com.openclassrooms.realestatemanager.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMain;
    private TextView textViewQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textViewMain = findViewById(R.id.activity_main_activity_text_view_main); // change the wrong layout (activity_second to activity_main)
        this.textViewQuantity = findViewById(R.id.activity_main_activity_text_view_quantity);

        this.configureTextViewMain();
        this.configureTextViewQuantity();
        Utils.isInternetFormatedAvailable();
    }

    private void configureTextViewMain(){
        this.textViewMain.setTextSize(15);
        this.textViewMain.setText(getString(R.string.valeur_premier_bien));
    }

    private void configureTextViewQuantity(){
        String quantity = String.valueOf(Utils.convertDollarToEuro(100)); // change int to String
        this.textViewQuantity.setTextSize(20);
        this.textViewQuantity.setText(quantity);
    }
}
