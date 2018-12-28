package com.example.ccc.fit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class RecyclerViewtest extends AppCompatActivity {

    private List<Fruit> fruitlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initFruit();
        RecyclerView recyclerView4 =(RecyclerView)findViewById(R.id.recycle_view4);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView4.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitlist);
        recyclerView4.setAdapter(adapter);
    }
    public void initFruit()
    {
        Fruit apple=new Fruit("Sam",R.drawable.s5);
        fruitlist.add(apple);
        Fruit apple1=new Fruit("Jack",R.drawable.s6);
        fruitlist.add(apple1);
        Fruit apple2=new Fruit("Rechid",R.drawable.s7);
        fruitlist.add(apple2);
        Fruit apple3=new Fruit("Frank",R.drawable.s8);
        fruitlist.add(apple3);
        Fruit apple4=new Fruit("Ben",R.drawable.s9);
        fruitlist.add(apple4);
        Fruit apple5=new Fruit("Belicy",R.drawable.s10);
        fruitlist.add(apple5);
        Fruit apple6=new Fruit("John",R.drawable.s6);
        fruitlist.add(apple6);
        Fruit apple7=new Fruit("NightKing",R.drawable.s6);
        fruitlist.add(apple7);
        Fruit apple8=new Fruit("Cercy",R.drawable.s6);
        fruitlist.add(apple8);
        Fruit apple9=new Fruit("Tilia",R.drawable.s6);
        fruitlist.add(apple9);
        Fruit apple10=new Fruit("Robert",R.drawable.s6);
        fruitlist.add(apple10);
        Fruit apple11=new Fruit("Frank",R.drawable.s6);
        fruitlist.add(apple11);
        Fruit apple12=new Fruit("Drango",R.drawable.s6);
        fruitlist.add(apple12);
        Fruit apple13=new Fruit("Hdoor",R.drawable.s6);
        fruitlist.add(apple13);
        Fruit apple14=new Fruit("Vale",R.drawable.s6);
        fruitlist.add(apple14);
        Fruit apple15=new Fruit("NightKing",R.drawable.s6);
        fruitlist.add(apple15);

    }
}
