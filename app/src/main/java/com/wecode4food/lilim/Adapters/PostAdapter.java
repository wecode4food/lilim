package com.wecode4food.lilim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.wecode4food.lilim.Models.Post;
import com.wecode4food.lilim.Models.Reto;
import com.wecode4food.lilim.R;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.myViewHolder> {
    private Context mContext;
    private List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.post_item, viewGroup,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int position) {

        myViewHolder.titulo.setText(mData.get(position).getTitulo());
        myViewHolder.owner.setText(mData.get(position).getOwner());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postActivity = new Intent(mContext, com.wecode4food.lilim.Activities.postActivity.class);
                postActivity.putExtra("key",mData.get(position).getKey());
                mContext.startActivity(postActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, owner;


        public myViewHolder(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            owner=itemView.findViewById(R.id.owner);
        }
    }
}
