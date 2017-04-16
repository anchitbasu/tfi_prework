package intern.tfi.org.prework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.internal.zzs.TAG;

public class LoginActivity extends Activity {


    FirebaseDatabase database;
    DatabaseReference myRefUsers;
    Button loginButton;
    ImageView loginBackgroundImage,loginTextImage;
    EditText emailEditText, passwordEditText;
    String email,password;
    TextView signUpTextLink,staffLoginTextLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBackgroundImage=(ImageView) findViewById(R.id.background_image_view);
        loginTextImage=(ImageView)findViewById(R.id.text_image_view);
        Glide.with(this).load(R.drawable.login_background).into(loginBackgroundImage);
        Glide.with(this).load(R.drawable.teachforindia_white).into(loginTextImage);
        database=FirebaseDatabase.getInstance();
        myRefUsers=database.getReference("users");

        signUpTextLink=(TextView) findViewById(R.id.signup_text_link);
        staffLoginTextLink=(TextView)findViewById(R.id.staff_login_text_link);
        loginButton=(Button) findViewById(R.id.login_button);
        emailEditText=(EditText) findViewById(R.id.login_username_editText);
        passwordEditText=(EditText) findViewById(R.id.login_password_editText);

        email=emailEditText.getText().toString();
        password=passwordEditText.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        staffLoginTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffLoginRedirect();
            }
        });

        signUpTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });







    }



    private void signUp(){
        Intent signupIntent=new Intent(getApplicationContext(),UserSignUpActivity.class);
        startActivity(signupIntent);
    }

    private void staffLoginRedirect(){
        Intent staffLoginIntent=new Intent(getApplicationContext(),StaffLoginActivity.class);
        startActivity(staffLoginIntent);
    }

    private void login(){
        final String email=emailEditText.getText().toString();
        final String password=passwordEditText.getText().toString();
        myRefUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int flag=0;
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    if(dsp.child("email").getValue().equals(email) && dsp.child("password").getValue().equals(password)){

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences=getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString(getString(R.string.sp_userid),dsp.getKey().toString());
                        editor.putString(getString(R.string.sp_email),email);
                        editor.putString(getString(R.string.sp_mobile),dsp.child("mobile").getValue().toString());
                        editor.putInt("searchType",-1);
                        editor.putString(getString(R.string.sp_name),dsp.child("name").getValue().toString());
                        editor.commit();
                        Intent volunteerStartIntent=new Intent(getApplicationContext(),VolunteerStart.class);
                        startActivity(volunteerStartIntent);
                        flag=1;
                    }


                }
                if(flag==0) {
                    Toast.makeText(LoginActivity.this, "Login Failed. Try Again.", Toast.LENGTH_SHORT).show();
                    emailEditText.setText("");
                    passwordEditText.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
