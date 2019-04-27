package com.wecode4food.lilim;

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

public class loginActivity extends AppCompatActivity {
    EditText email,
            password;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this, homeActivity.class);
        cl = findViewById(R.id.cl);
    }

    public void signIn(View view) {
        hideKeyboard();
        if(!email.getText().toString().trim().equals("") && !password.getText().equals("")){
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(HomeActivity);
                    } else {
                        showMessage(task.getException().getMessage());
                    }
                }
            });
        }else{
            showMessage("Por favor llena ambos campos antes de continuar");
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
