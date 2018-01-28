package com.example.appjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    //declare instance variable widgets
    private EditText inputeditText;
    private TextView percenttextView;
    private TextView discounttextView;
    private TextView totaltextView;

    private String subtotalString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets from the R class
        inputeditText =  findViewById(R.id.inputeditText);
        percenttextView = findViewById(R.id.percenttextView);
        discounttextView = findViewById(R.id.discounttextView);
        totaltextView = findViewById(R.id.totaltextView);

        inputeditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        //add calcAndDisplay
        return false;
    }

    private void calcAndDisplay() {
        //get subtotal
        subtotalString = inputeditText.getText().toString();
        float subtotal;
        if(subtotalString.equals("")) {
            subtotal = 0;
        } else {
            subtotal = Float.parseFloat(subtotalString);
        }

        //get discount percent
        float discountPercent = 0;

        if(subtotal >= 200) {
            discountPercent = .2f;
        } else if (subtotal >= 100) {
            discountPercent = .1f;
        } else {
            discountPercent = 0;
        }

        //calculate discount
        float discountAmount = subtotal * discountPercent;
        float total = subtotal - discountAmount;

        //displaying data on layout
        NumberFormat percent = NumberFormat.getPercentInstance();
        discounttextView.setText(percent.format(discountPercent));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        discounttextView.setText(currency.format(discountAmount));
        totaltextView.setText(currency.format(total));
    }
}
