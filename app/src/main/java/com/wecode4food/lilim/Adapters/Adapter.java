package com.wecode4food.lilim.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.wecode4food.lilim.Models.Reto;
import com.wecode4food.lilim.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    private Context mContext;
    private List<Reto> mData;

    public Adapter(Context mContext, List<Reto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, viewGroup,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {
        Glide.with(mContext)
                .load(mData.get(position).getSrchelp())
                .into(myViewHolder.background_img);
        //myViewHolder.background_img.setImageURI(Uri.parse(mData.get(position).getSrchelp()));
        myViewHolder.tv_tittle.setText(mData.get(position).getTitulo());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView background_img;
        TextView tv_tittle;


        public myViewHolder(View itemView){
            super(itemView);
            background_img = itemView.findViewById(R.id.card_background);
            tv_tittle=itemView.findViewById(R.id.card_tittle);
        }
    }
}
