package com.wecode4food.lilim.Activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.wecode4food.lilim.Adapters.ChallengeAdapter;
import com.wecode4food.lilim.Adapters.PostAdapter;
import com.wecode4food.lilim.Models.Post;
import com.wecode4food.lilim.Models.Reto;
import com.wecode4food.lilim.R;

import java.util.ArrayList;

public class challengeActivity extends AppCompatActivity {
    ConstraintLayout cl;
    ImageView imagesrc;
    TextView titulo,
            descripcion;
    private DatabaseReference retos;
    private DatabaseReference posts;
    private RecyclerView recyclerView;
    ArrayList<Post> postsList;
    String key;
    Reto reto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        key = getIntent().getStringExtra("key");
        Log.i("Intent result",key);
        init();
    }

    private void init(){
        imagesrc = findViewById(R.id.imagesrc);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        cl = findViewById(R.id.cl);
        retos = FirebaseDatabase.getInstance().getReference("reto");
        posts = FirebaseDatabase.getInstance().getReference("post");

        postsList = new ArrayList<>();

        populateAlpha();
        populateRecyclerView();
    }

    private void populateAlpha(){
        retos.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Log.i("Exito","Existe el registro en la db");
                    Reto r = dataSnapshot.getValue(Reto.class);
                   filldata(r);
                }else {
                    Log.i("error","Nueva consulta fallida");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void populateRecyclerView(){
        posts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot d: dataSnapshot.getChildren()){
                        postsList.add(d.getValue(Post.class));
                        recyclerViewInit();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void recyclerViewInit(){
        PostAdapter postAdapterRV =new PostAdapter(this,postsList);
        recyclerView.setAdapter(postAdapterRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //miselaneous functions

    private void showMessage(String text) {
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Snackbar.make(cl,text,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }
    private void filldata(Reto reto){
        titulo.setText(reto.getTitulo());
        descripcion.setText(reto.getDescripcion());
        Glide.with(getBaseContext())
                .load(reto.getSrchelp())
                .into(imagesrc);
    }
}
