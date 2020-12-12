package com.example.candelstick;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private MainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.candle_layout);
        mainView.setCandleSize(50);
        APIConnection api = new APIConnection("", "");
        ArrayList<DataVO> dataVOS = api.make_candle();
        mainView.setData(dataVOS);
    }

    public void setData()
    {
        APIConnection apiConnection = new APIConnection("", "");
        String data = apiConnection.get_data_set();
    }
}