package com.wecode4food.lilim.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wecode4food.lilim.R;

public class loginActivity extends AppCompatActivity {
    EditText email,
            password;
    //Firebase auth modules init
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    //intent initialization
    private Intent homeActivity;
    private Intent registerActivity;
    //layout initialization
    private ConstraintLayout cl;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        init();
        if (user != null) {
            //the User is already logged
            startActivity(homeActivity);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void init(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //mAuth = FirebaseAuth.getInstance();
        homeActivity = new Intent(this, homeActivity.class);
        registerActivity = new Intent(this,registerActivity.class);
        cl = findViewById(R.id.cl);
    }

    public void login(View view) {
        hideKeyboard();
        if(!email.getText().toString().trim().equals("") && !password.getText().equals("")){
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(homeActivity);
                        finish();
                    } else {
                        showMessage(task.getException().getMessage());
                    }
                }
            });
        }else{
            showMessage("Por favor llena ambos campos antes de continuar");
        }
    }
    public  void guestLogin(View view) {
        showMessage("Entrando como invitado");
        startActivity(homeActivity);
        finish();
    }
    public void register(View view){
        startActivity(registerActivity);
        finish();
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
