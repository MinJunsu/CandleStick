package com.example.candelstick;

import java.util.Date;

public class DataVO
{
    private Date dateTime;
    private String name;
    private float openPrice, highPrice, lowPrice, closePrice;

    public DataVO(Date dateTime, String name, float openPrice, float highPrice, float lowPrice, float closePrice)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
    }

    public Date getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getOpenPrice()
    {
        return openPrice;
    }

    public void setOpenPrice(float openPrice)
    {
        this.openPrice = openPrice;
    }

    public float getHighPrice()
    {
        return highPrice;
    }

    public void setHighPrice(float highPrice)
    {
        this.highPrice = highPrice;
    }

    public float getLowPrice()
    {
        return lowPrice;
    }

    public void setLowPrice(float lowPrice)
    {
        this.lowPrice = lowPrice;
    }

    public float getClosePrice()
    {
        return closePrice;
    }

    public void setClosePrice(float closePrice)
    {
        this.closePrice = closePrice;
    }
}
