package intern.tfi.org.prework;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.android.gms.internal.zzs.TAG;

public class UserSignUpActivity extends Activity {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRefUsers;
    ImageView backgroundImageView, backgroundTextImageView;
    EditText emailEditText, passwordEditText, confPassEditText, nameEditText,mobileEditText;
    private FirebaseAuth mAuth;
    //String email, password,mobile,name;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        backgroundImageView=(ImageView) findViewById(R.id.signup_background_image_view);
        backgroundTextImageView=(ImageView) findViewById(R.id.signup_tfi_text_image_view);
        Glide.with(getApplicationContext()).load(R.drawable.login_background).into(backgroundImageView);
        Glide.with(getApplicationContext()).load(R.drawable.teachforindia_white).into(backgroundTextImageView);
        signUpButton=(Button) findViewById(R.id.signup_button);
        emailEditText=(EditText) findViewById(R.id.signup_email_editText);
        passwordEditText=(EditText) findViewById(R.id.signup_password_editText);
        confPassEditText=(EditText) findViewById(R.id.signup_conf_password_editText);
        nameEditText=(EditText)findViewById(R.id.signup_name_editText);
        mobileEditText=(EditText)findViewById(R.id.signup_mobile_editText);

        database=FirebaseDatabase.getInstance();
        myRefUsers=database.getReference("users");
        mAuth=FirebaseAuth.getInstance();


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }
        });

    }
    public void signUp(){
        if(passwordEditText.getText().toString().equals(confPassEditText.getText().toString()) && !nameEditText.getText().toString().equals("") && !emailEditText.getText().toString().equals("") && !mobileEditText.getText().toString().equals("") && !passwordEditText.getText().toString().equals("") && mobileEditText.getText().toString().length()==10 ){
            String uid=myRefUsers.push().getKey();
            myRefUsers.child(uid).setValue(new UserSignUpInfo(nameEditText.getText().toString(),emailEditText.getText().toString(),mobileEditText.getText().toString(),passwordEditText.getText().toString(),"user"));
            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
            Intent loginIntent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
