package com.example.candelstick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainView extends View
{
    private int xSize, ySize;
    private float Max, Min;
    private ArrayList<DataVO> dataSet;
    private Paint myPaint;
    private int candleSize, slimCandleSize;



    public MainView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        super.onWindowFocusChanged(hasWindowFocus);
        setSize(getWidth(), getHeight());
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(dataSet != null)
        {
            myPaint = new Paint();
            setMaxMin();

            for(int i = 0; i < dataSet.size(); i++)
            {
                float open = dataSet.get(i).getOpenPrice();
                float close = dataSet.get(i).getClosePrice();
                float high = dataSet.get(i).getHighPrice();
                float low = dataSet.get(i).getLowPrice();
                float top, bottom, stickTop, stickBottom;

                if(open > close)
                {
                    myPaint.setColor(Color.RED);
                    top = ySize - ((open - Min) / (Max - Min) * ySize);
                    bottom = ySize - ((close - Min) / (Max - Min) * ySize);
                    stickTop = ySize - ((high - Min) / (Max - Min) * ySize);
                    stickBottom = ySize - ((low - Min) / (Max - Min) * ySize);

                }
                else if (open == close)
                {
                    myPaint.setColor(Color.GREEN);
                    top = ySize - ((close - Min) / (Max - Min) * ySize);
                    bottom = ySize - ((open - Min) / (Max - Min) * ySize);
                    stickTop = ySize - ((high - Min) / (Max - Min) * ySize);
                    stickBottom = ySize - ((low - Min) / (Max - Min) * ySize);
                }
                else
                {
                    myPaint.setColor(Color.GREEN);
                    top = ySize - ((close - Min) / (Max - Min) * ySize);
                    bottom = ySize - ((open - Min) / (Max - Min) * ySize);
                    stickTop = ySize - ((high - Min) / (Max - Min) * ySize);
                    stickBottom = ySize - ((low - Min) / (Max - Min) * ySize);
                }

                canvas.drawRect((float) ((xSize / candleSize) * (i + 0.5)) - (xSize / (candleSize * 10)), stickTop, (float) ((xSize / candleSize) * (i + 0.5)) + (xSize / (candleSize * 10)), stickBottom, myPaint);
                canvas.drawRect((xSize / candleSize) * i, top, (xSize / candleSize) * (i + 1), bottom, myPaint);
            }
        }
    }

    public void setDistance()
    {

    }

    public void repaint()
    {
        invalidate();
    }

    private void setMaxMin()
    {
        Max = dataSet.get(0).getHighPrice();
        Min = dataSet.get(0).getLowPrice();

        for(DataVO data : dataSet)
        {
            if(data.getHighPrice() > Max)
            {
                Max = data.getHighPrice();
            }

            if(data.getLowPrice() < Min)
            {
                Min = data.getLowPrice();
            }
        }
    }

    public void setSize(int X, int Y)
    {
        this.xSize = X;
        this.ySize = Y;
    }

    public void setCandleSize(int candleSize)
    {
        this.candleSize = candleSize;
        this.slimCandleSize = (candleSize / 25);
    }

    public void setData(ArrayList<DataVO> data)
    {
        this.dataSet = data;
    }
}
