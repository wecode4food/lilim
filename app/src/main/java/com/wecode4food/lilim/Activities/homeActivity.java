package com.wecode4food.lilim.Activities;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wecode4food.lilim.Adapters.Adapter;
import com.wecode4food.lilim.Models.Reto;
import com.wecode4food.lilim.R;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference retos;
    private RecyclerView recyclerView;
    ArrayList<Reto> retosList;

    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        populateFromDB();
    }

    private void init(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        retos = FirebaseDatabase.getInstance().getReference("reto");
        retosList = new ArrayList<>();
        cl = findViewById(R.id.cl);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void populateFromDB(){
        retos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    retosList.add(ds.getValue(Reto.class));
                    Log.i("Dato No:"+retosList.size(),""+retosList.get(retosList.size()-1));
                }
                recyclerViewInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Database Error ","Error al leer la base de datos");
            }
        });
    }

    private void recyclerViewInit(){
        Adapter adapterRV =new Adapter(this,retosList);
        recyclerView.setAdapter(adapterRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Miscellaneous Functions

    private void showMessage(String text) {
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Snackbar.make(cl,text,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }
}
