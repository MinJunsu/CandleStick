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
    private int xMaxSize, yMaxSize;
    private float Max, Min;
    private ArrayList<DataVO> dataSet;
    private Paint myPaint;
    private int candleSize, slimCandleSize;
    private float posX, posY;
    private String candleText;


    public MainView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        myPaint = new Paint();
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
        DrawBound(canvas);
        DrawCandleStick(canvas);
        DrawCandleStickPrice(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        posX = event.getX();
        posY = event.getY();
        return super.onTouchEvent(event);
    }

    public void DrawCandleStickLine(Canvas canvas)
    {
        myPaint.setColor(Color.GRAY);
        for(int i = 0; i < 100; i++)
        {
            if(i % 2 == 0)
            {
                canvas.drawLine(0 + ((int) (xMaxSize * 1.1) / 100) * i, posY, 0 + ((int) (xMaxSize * 1.1) / 100) * (i + 1), posY, myPaint);
            }
        }
        for(int i = 0; i < 30; i++)
        {
            if(i % 2 == 0)
            {
                canvas.drawLine(posX, 0 + ((int) (yMaxSize * 1.1) / 30) * i, posX, 0 + ((int) (yMaxSize * 1.1) / 30) * (i + 1), myPaint);
            }
        }
        DrawSpecificPrice(canvas);
    }

    public void DrawCandleStickPrice(Canvas canvas)
    {
        for(int i = 0; i < dataSet.size(); i++)
        {
            float left = (xMaxSize / candleSize) * i;
            float right = (xMaxSize / candleSize) * (i + 1);
            if((left <= posX) && (posX <= right))
            {
                candleText = "T: " + dataSet.get(i).getDateTime().substring(0, 9) + " O: " + dataSet.get(i).getOpenPrice() + " H: " + dataSet.get(i).getHighPrice() + " L: " + dataSet.get(i).getLowPrice() + " C: " + dataSet.get(i).getClosePrice();
                break;
            }
        }
        myPaint.setTextSize(xSize / 40);
        myPaint.setColor(Color.BLACK);
        canvas.drawText(candleText, 15, 30, myPaint);
        DrawCandleStickLine(canvas);
        repaint();
    }

    public void DrawBound(Canvas canvas)
    {
        myPaint.setColor(Color.GRAY);
        canvas.drawRect(-5, 0, 10, ySize, myPaint);
        canvas.drawRect(xMaxSize + 5, 0, xSize, ySize, myPaint);
        canvas.drawRect(0, 0, xSize, 10, myPaint);
        canvas.drawRect(0, ySize - 10, xSize, ySize, myPaint);
    }

    public void DrawSpecificPrice(Canvas canvas)
    {
        myPaint.setColor(Color.BLACK);
        float tmp = (Min) + (1 - ((posY - 10) / yMaxSize)) * (Max - Min);
        float price = (float) (Math.round(tmp) + ((tmp - ((int) tmp)) > 0.5 ? 0.5 : 0.0));
        canvas.drawText(String.valueOf(price), xMaxSize + 5, posY + 10, myPaint);
    }

    public void DrawCandleStick(Canvas canvas)
    {
        if(dataSet != null)
        {
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
                    top = yMaxSize - ((open - Min) / (Max - Min) * yMaxSize);
                    bottom = yMaxSize - ((close - Min) / (Max - Min) * yMaxSize);
                    stickTop = yMaxSize - ((high - Min) / (Max - Min) * yMaxSize);
                    stickBottom = yMaxSize - ((low - Min) / (Max - Min) * yMaxSize);

                }
                else if (open == close)
                {
                    myPaint.setColor(Color.GREEN);
                    top = yMaxSize - ((close - Min) / (Max - Min) * yMaxSize);
                    bottom = yMaxSize - ((open - Min) / (Max - Min) * yMaxSize);
                    stickTop = yMaxSize - ((high - Min) / (Max - Min) * yMaxSize);
                    stickBottom = yMaxSize - ((low - Min) / (Max - Min) * yMaxSize);
                }
                else
                {
                    myPaint.setColor(Color.GREEN);
                    top = yMaxSize - ((close - Min) / (Max - Min) * yMaxSize);
                    bottom = yMaxSize - ((open - Min) / (Max - Min) * yMaxSize);
                    stickTop = yMaxSize - ((high - Min) / (Max - Min) * yMaxSize);
                    stickBottom = yMaxSize - ((low - Min) / (Max - Min) * yMaxSize);
                }

                canvas.drawRect(((float) ((xMaxSize / candleSize) * (i + 0.5)) - (xMaxSize / (candleSize * 10))) + 10, stickTop + 10, ((float) ((xMaxSize / candleSize) * (i + 0.5)) + (xMaxSize / (candleSize * 10))) + 10, stickBottom + 10, myPaint);
                canvas.drawRect(((xMaxSize / candleSize) * i) + 10, top + 10, ((xMaxSize / candleSize) * (i + 1)) + 10, bottom + 10, myPaint);
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
        this.xMaxSize = (int) (xSize * 0.9);
        this.yMaxSize = (int) (ySize * 0.9);
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
