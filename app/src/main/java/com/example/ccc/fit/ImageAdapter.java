package com.example.ccc.fit;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter
{
    private List<View> viewList;
    private FragmentManager fm;
    public ImageAdapter(FragmentManager fm,List<View> viewList)
    {
        this.fm=fm;
        this.viewList =  viewList;
    }

    //数据源的数目
    public int getCount()
    {
        return 2;
    }


    //view是否由对象产生，官方写arg0==arg1即可
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0==arg1;
    }


    //销毁一个页卡(即ViewPager的一个item)
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(viewList.get(position));
    }

    //对应页卡添加上数据
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(viewList.get(position));//千万别忘记添加到container
        return viewList.get(position);
    }





}
