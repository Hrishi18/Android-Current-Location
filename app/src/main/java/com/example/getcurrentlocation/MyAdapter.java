package com.example.getcurrentlocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<distanceModel> objectList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<distanceModel>objectList){
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {

        return objectList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        distanceModel current = objectList.get(position);
        holder.setData(current, position);


    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView distance;
        private ImageView imgThumb;
        private int position;
        private distanceModel currentObject;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            distance      = (TextView)  itemView.findViewById(R.id.distance);
            imgThumb    = (ImageView) itemView.findViewById(R.id.img_view);
        }

        public void setData(distanceModel currentObject, int position) {
            this.distance.setText(currentObject.getDistance());
            this.imgThumb.setImageResource(currentObject.getImageId());
            this.position = position;
            this.currentObject = currentObject;
        }



    }
}
