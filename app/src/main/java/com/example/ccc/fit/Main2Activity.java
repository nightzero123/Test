package com.example.ccc.fit;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.app.PendingIntent.getActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager pager;
    public List<View> viewList = new ArrayList<View>();
    private ImageAdapter viewAdapter;
    private LayoutInflater inflater;
    private List<Fruit> fruitlist=new ArrayList<>();
    Fragment fragmentup1;
    Fragment fragmentup2;
    Fragment fragmentup3;
    Fragment fragmentup4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = findViewById(R.id.rbChat);
        Button button1 = findViewById(R.id.rbAddress);
        Button button2 = findViewById(R.id.rbFind);
        Button button3= findViewById(R.id.rbMe);
        button.setOnClickListener((View.OnClickListener) this);
        button1.setOnClickListener((View.OnClickListener) this);
        button2.setOnClickListener((View.OnClickListener) this);
        button3.setOnClickListener((View.OnClickListener) this);
        fragmentup1=new fragementup();
        fragmentup2=new another_fragementup();
        fragmentup3=new another_fragementup1();
        fragmentup4=new another_fragementup2();
        replaceFragment(fragmentup1);
        //replaceFragment(fragementup.newInstance());
        //pager=getFragmentManager().findFragmentById(R.id.linearLayout2).getView().findViewById(R.id.viewPager);
        pager = (ViewPager)findViewById(R.id.viewPager1);
        inflater = LayoutInflater.from(this);
        View view1 =  inflater.inflate(R.layout.layout1,null);
        View view2 =  inflater.inflate(R.layout.layout2, null);
        viewList.add(view1);
        viewList.add(view2);
        viewAdapter = new ImageAdapter(getSupportFragmentManager(),viewList);
        pager.setAdapter(viewAdapter);
        //FragmentManager fragmentM=getSupportFragmentManager();
        //RecyclerView tv=fragmentM.findFragmentByTag("123").getView().findViewById(R.id.recycler_view);
        //getFragmentManager().findFragmentById(R.id.fragmentup1).getView().findViewById(R.id.recycler_view);
        //RecyclerView tv=getSupportFragmentManager().findFragmentById(R.id.fragmentup1).getView().findViewById(R.id.recycler_view);
        //Fragment fragment = manager.findFragmentById(R.id.up_layout);
        initFruit();
        Fragment anfragment=(Fragment)getSupportFragmentManager().findFragmentById(R.id.up_layout);
        Fragment anfragment=getSupportFragmentManager().findFragmentById(R.id.up_layout);
        RecyclerView tv =(RecyclerView)fragmentup3.getView().findViewById(R.id.recycler_view);
        RecyclerView tv =anfragment.getView().findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        tv.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitlist);
        tv.setAdapter(adapter);

    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.rbChat:
                replaceFragment(new fragementup());
                //startActivity(new Intent(getActivity(),Activity.class));
                break;
            case R.id.rbAddress:
                replaceFragment(new another_fragementup());
                break;
            case R.id.rbFind:
                replaceFragment(new another_fragementup1());
                /*
                Fragment anfragment=(Fragment)getSupportFragmentManager().findFragmentById(R.id.up_layout);
                //RecyclerView tv =(RecyclerView)fragmentup3.getView().findViewById(R.id.recycler_view);
                RecyclerView tv =anfragment.getView().findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager=new LinearLayoutManager(this);
                tv.setLayoutManager(layoutManager);
                FruitAdapter adapter=new FruitAdapter(fruitlist);
                tv.setAdapter(adapter);
                /*
                initFruit();
                RecyclerView tv =(RecyclerView)fragmentup3.getView().findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager=new LinearLayoutManager(this);
                tv.setLayoutManager(layoutManager);
                FruitAdapter adapter=new FruitAdapter(fruitlist);
                tv.setAdapter(adapter);
                */
                break;
            case R.id.rbMe:
                replaceFragment(new another_fragementup2());
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.up_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void initFruit()
    {
        Fruit apple=new Fruit("apple",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple1=new Fruit("apple1",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple2=new Fruit("apple2",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple3=new Fruit("apple3",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple4=new Fruit("apple4",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple5=new Fruit("apple5",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple6=new Fruit("apple6",R.drawable.a1);
        fruitlist.add(apple);
        Fruit apple7=new Fruit("apple7",R.drawable.a1);
        fruitlist.add(apple);

    }
}
