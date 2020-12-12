package com.example.candelstick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainView extends View {
    private int xSize, ySize;
    private float Max, Min;
    private ArrayList<DataVO> dataSet;
    private Paint myPaint;
    private int candleSize, slimCandleSize;
    private float posX, posY;
    private String candleText;


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
        DrawCandleStick(canvas);
        DrawCandleStickPrice(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        posX = event.getX();
        posY = event.getY();
        Log.d("position", "x : " + posX + ", y : " + posY);
        return super.onTouchEvent(event);
    }

    public void DrawCandleStickLine(Canvas canvas)
    {
        myPaint.setColor(Color.GRAY);
        for(int i = 0; i < 100; i++)
        {
            if(i % 2 == 0)
            {
                canvas.drawLine(0 + ((int) (xSize * 1.1) / 100) * i, posY, 0 + ((int) (xSize * 1.1) / 100) * (i + 1), posY, myPaint);
            }
        }
        for(int i = 0; i < 30; i++)
        {
            if(i % 2 == 0)
            {
                canvas.drawLine(posX, 0 + ((int) (ySize * 1.1) / 30) * i, posX, 0 + ((int) (ySize * 1.1) / 30) * (i + 1), myPaint);
            }
        }
    }

    public void DrawCandleStickPrice(Canvas canvas)
    {
        for(int i = 0; i < dataSet.size(); i++)
        {
            float left = (xSize / candleSize) * i;
            float right = (xSize / candleSize) * (i + 1);
            if((left <= posX) && (posX <= right))
            {
                candleText = "T: " + dataSet.get(i).getDateTime().substring(0, 9) + " O: " + dataSet.get(i).getOpenPrice() + " H: " + dataSet.get(i).getHighPrice() + " L: " + dataSet.get(i).getLowPrice() + " C: " + dataSet.get(i).getClosePrice();
                break;
            }
        }
        myPaint.setTextSize(xSize / 40);
        myPaint.setColor(Color.BLACK);
        canvas.drawText(candleText, 10, 25, myPaint);
        DrawCandleStickLine(canvas);
        repaint();
    }

    public void DrawCandleStick(Canvas canvas)
    {
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
