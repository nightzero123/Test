package com.example.ccc.fit;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout
{
    public TitleLayout(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleback=(Button)findViewById(R.id.title_back);
        titleback.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((Activity)getContext()).finish();
            }
        });
    }
}
