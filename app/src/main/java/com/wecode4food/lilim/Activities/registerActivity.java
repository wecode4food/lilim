package com.wecode4food.lilim.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.wecode4food.lilim.Models.User;
import com.wecode4food.lilim.R;

public class registerActivity extends AppCompatActivity {
    ConstraintLayout cl;
    EditText name,
             mail,
             id,
             password,
             cPassword;
    private User user;
    private FirebaseAuth mAuth;
    DatabaseReference usuarios;
    Intent homeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        cl = findViewById(R.id.cl);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.cPassword);
        mAuth = FirebaseAuth.getInstance();
        usuarios = FirebaseDatabase.getInstance().getReference("usuarios");
        homeActivity = new Intent(this, com.wecode4food.lilim.Activities.homeActivity.class);

    }

    public void OCRegister(View view){
        register(mail.getText().toString().trim(), name.getText().toString().trim(), id.getText().toString().trim(), password.getText().toString().trim(),
                cPassword.getText().toString().trim());
    }

    private void register(String email, String name, String id, final String password, String cPassword){

        hideKeyboard();

        if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty() && (password.length()==6) &&  password.equals(cPassword)){
            user = new User(name, email, id);
            Log.i("Flag","Enter Condition");
            Log.i("cc ",id);
            usuarios.orderByChild("cc").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.exists()){
                        Log.i("Snapshot",dataSnapshot.toString());
                        mAuth.createUserWithEmailAndPassword(user.getMail(),password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    showMessage("Registro realizado Correctamente");
                                    usuarios.child(mAuth.getUid()).setValue(user);
                                    startActivity(homeActivity);
                                    finish();
                                }else {
                                    showMessage(task.getException()+"");
                                    Log.i("Error in mAuth ",task.getException()+"");
                                }

                            }
                        });

                    }else {
                        showMessage("Esta cedula ya se encuentra registrada");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }else{
            showMessage("Revise los campos e intente de nuevo");
            Log.i("error ","\n\nError al comparar condicionales\n\n");
            Log.i("email empty ",email.isEmpty()+"");
            Log.i("name empty ",name.isEmpty()+"");
            Log.i("password empty ",password.isEmpty()+"");
            Log.i("password_confirm empty ",cPassword.isEmpty()+"");
            Log.i("diferents passwords ",password.equals(cPassword)+"");
            Log.i("password value ",password.toString());
            Log.i("password confirm value ",cPassword.toString());

        }
    }

    //Miscellaneous Functions

    private void showMessage(String text) {
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Snackbar.make(cl,text,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
