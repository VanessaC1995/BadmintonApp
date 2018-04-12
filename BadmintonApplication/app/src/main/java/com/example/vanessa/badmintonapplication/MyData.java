package com.example.vanessa.badmintonapplication;

/**
 * Created by Vanessa.
 */

public class MyData
{
    private String name;
    private byte[] image;

    public MyData(String name, byte[] image)
    {
        this.name = name;
        this.image = image;
    }

    public String GetName()
    {
        return name;
    }

    public byte[] GetImage()
    {
        return image;
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public void SetImage (byte[] image)
    {
        this.image = image;
    }
}
