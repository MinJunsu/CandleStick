package com.example.candelstick;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private MainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView.findViewById(R.id.candle_layout);
        mainView.setSize(500, 500);
        mainView.setCandleSize(20);

    }

    public void setData()
    {
        APIConnection apiConnection = new APIConnection("", "");
        String data = apiConnection.get_data_set();
    }
}