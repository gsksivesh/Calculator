package com.example.gsksivesh.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Button> allButtonsText = new ArrayList<>();
        allButtonsText.add((Button)findViewById(R.id.no_0));
        allButtonsText.add((Button)findViewById(R.id.no_1));
        allButtonsText.add((Button)findViewById(R.id.no_2));
        allButtonsText.add((Button)findViewById(R.id.no_3));
        allButtonsText.add((Button)findViewById(R.id.no_4));
        allButtonsText.add((Button)findViewById(R.id.no_5));
        allButtonsText.add((Button)findViewById(R.id.no_6));
        allButtonsText.add((Button)findViewById(R.id.no_7));
        allButtonsText.add((Button)findViewById(R.id.no_8));
        allButtonsText.add((Button)findViewById(R.id.no_9));
        allButtonsText.add((Button)findViewById(R.id.dot));
        final TextView textView = (TextView)findViewById(R.id.text_answer);
        for(Button button: allButtonsText){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String clickedButton = ((Button) view).getText().toString();
                    String previousText = textView.getText().toString();
                    textView.setText(previousText+clickedButton);
                }
            });
        }
        final Button resetButton = (Button)findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });
    }
}
