package com.example.ccc.fit;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {


    private List<Fruit>  mFruitList;
    private Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }


    public FruitAdapter(List<Fruit> fruitlist){

         mFruitList=fruitlist;
        }



    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null)
        {
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item1,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
        }



    @Override

    public void onBindViewHolder(ViewHolder holder,int position)
    {
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
        /*
        @Override
        public  int getItemCount()
        {
            return mFruitList.size();
        }
        */
    }



    @Override

    public int getItemCount() {

        return mFruitList.size();

    }




}

/*
public class FruitAdapter extends ArrayAdapter <Fruit>
{
    private int resourceId;
    public FruitAdapter (Context context, int textViewResourceId, List<Fruit> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertview,ViewGroup parent)
    {
        Fruit fruit=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
        TextView fruitName=(TextView)view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getimageid());
        fruitName.setText(fruit.getName());
        return view;
    }
}
*/
