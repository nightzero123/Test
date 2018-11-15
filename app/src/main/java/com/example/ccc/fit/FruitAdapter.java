package com.example.ccc.fit;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context context;
    private List<Fruit>  mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view)
        {
            super(view);
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }


    public FruitAdapter(List<Fruit> fruitlist){

         mFruitList=fruitlist;
        }



    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        return new ViewHolder(view);
        }



    @Override

    public void onBindViewHolder(ViewHolder holder,int position)
    {
        Fruit fruit=mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        /*
            @Override
            public void onClick(View v) {
            Log.e("这里是点击每一行item的响应事件",""+position+item);}});
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
