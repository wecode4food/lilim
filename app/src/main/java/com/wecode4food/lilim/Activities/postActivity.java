package com.wecode4food.lilim.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wecode4food.lilim.Models.Post;
import com.wecode4food.lilim.Models.Reto;
import com.wecode4food.lilim.R;

import java.util.ArrayList;

public class postActivity extends AppCompatActivity {

    ImageView imagesrc;
    TextView titulo,
            descripcion;
    private DatabaseReference posts;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        key = getIntent().getStringExtra("key");
        Log.i("Intent result",key);
        init();
        populateAlpha();
    }
    public void init(){
        imagesrc = findViewById(R.id.imagesrc);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        posts = FirebaseDatabase.getInstance().getReference("post");
    }

    private void populateAlpha(){
        posts.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Log.i("Exito","Existe el registro en la db");
                    Post p = dataSnapshot.getValue(Post.class);
                    filldata(p);
                }else {
                    Log.i("error","Nueva consulta fallida");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void filldata(Post post){
        titulo.setText(post.getTitulo());
        descripcion.setText(post.getDescripcion());
        Glide.with(getBaseContext())
                .load(post.getSrc())
                .into(imagesrc);
    }
}
