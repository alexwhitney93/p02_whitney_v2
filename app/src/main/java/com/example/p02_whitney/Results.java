package com.example.p02_whitney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class Results extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        int result= getIntent().getExtras().getInt("Result");
        Button button= findViewById(R.id.resultsButton);
        TextView tv = findViewById(R.id.resultsTV);
        if(result == 0)
        {
            button.setText("Go to Main Menu");
            tv.setText("CONGRATULATIONS! YOU WON!");
        }
        else
        {
            tv.setText("OOPS! YOU LOST!");
            button.setText("Try Again");
        }
    }
    public void retry(View v)
    {
        Intent i= new Intent(Results.this,MainActivity.class);
        startActivity(i);
    }
}
