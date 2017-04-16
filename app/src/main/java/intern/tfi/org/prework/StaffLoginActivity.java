package intern.tfi.org.prework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StaffLoginActivity extends Activity {

    FirebaseDatabase database;
    DatabaseReference myRefUsers;
    ImageView backgroundImageView,backgroundTextImageView;
    EditText staffEmailEditText,staffPasswordEditText;
    String staffEmail,staffPassword;
    Button staffLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        backgroundImageView=(ImageView) findViewById(R.id.staff_login_background_image_view);
        backgroundTextImageView=(ImageView)findViewById(R.id.staff_login_text_image_view);
        Glide.with(this).load(R.drawable.background_2).into(backgroundImageView);
        Glide.with(this).load(R.drawable.teachforindia_white).into(backgroundTextImageView);
        staffEmailEditText=(EditText)findViewById(R.id.staff_login_email_editText);
        staffPasswordEditText=(EditText)findViewById(R.id.staff_login_password_editText);
        staffLoginButton=(Button) findViewById(R.id.staff_login_button);
        database=FirebaseDatabase.getInstance();
        myRefUsers=database.getReference("users");



        staffLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffEmail=staffEmailEditText.getText().toString();
                staffPassword=staffPasswordEditText.getText().toString();
                if(staffEmail.equals("admin") && staffPassword.equals("admin$1234")){
                    Intent adminIntent=new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(adminIntent);
                }
                else{
                    myRefUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                        int flag=0;
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot dsp: dataSnapshot.getChildren()){
                                if(dsp.child("email").getValue().toString().equals(staffEmail) && dsp.child("password").getValue().toString().equals(staffPassword)){
                                    Toast.makeText(StaffLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    SharedPreferences sharedpreferences= getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sharedpreferences.edit();
                                    editor.putString(getString(R.string.sp_userid),staffPassword);
                                    editor.putString(getString(R.string.sp_email),staffEmail);
                                    editor.commit();

                                    Intent fellowStartIntent=new Intent(getApplicationContext(),FellowStartActivity.class);
                                    startActivity(fellowStartIntent);
                                    flag=1;
                                }
                            }
                            if(flag==0) {
                                Toast.makeText(StaffLoginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                                staffEmailEditText.setText("");
                                staffPasswordEditText.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent userLoginIntent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(userLoginIntent);
    }
}
