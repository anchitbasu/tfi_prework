package intern.tfi.org.prework;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends Activity {

    FirebaseDatabase database;
    DatabaseReference myRefUsers;
    Button createUserButton,logoutButton;
    EditText userNameEditText,userEmailEditText,userMobileEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        createUserButton=(Button) findViewById(R.id.admin_create_user_button);
        userNameEditText=(EditText) findViewById(R.id.admin_create_user_name_edit_text);
        userEmailEditText=(EditText)findViewById(R.id.admin_create_user_email_edit_text);
        userMobileEditText=(EditText)findViewById(R.id.admin_create_user_mobile_edit_text);
        logoutButton=(Button)findViewById(R.id.admin_logout_button);
        database=FirebaseDatabase.getInstance();
        myRefUsers=database.getReference("users");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staffLoginIntent=new Intent(getApplicationContext(),StaffLoginActivity.class);
                startActivity(staffLoginIntent);
            }
        });

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName=userNameEditText.getText().toString();
                final String userEmail=userEmailEditText.getText().toString();
                final String userMobile=userMobileEditText.getText().toString();
                myRefUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dsp: dataSnapshot.getChildren()){
                            if(dsp.child("email").getValue().toString().equals(userEmail) || dsp.child("mobile").getValue().toString().equals(userMobile)){
                                Toast.makeText(AdminActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String password = myRefUsers.push().getKey();
                                myRefUsers.child(password).setValue(new StaffSignUpInfo(userName,userEmail,userMobile,password,"staff"));

                                final Dialog dialog=new Dialog(AdminActivity.this);
                                dialog.setContentView(R.layout.staff_details_dialog_layout);
                                dialog.setTitle("User Info");

                                TextView dialogUserNameTextView=(TextView)dialog.findViewById(R.id.staff_name_dialog_text_view);
                                TextView dialogUserEmailTextView=(TextView)dialog.findViewById(R.id.staff_email_dialog_text_view);
                                TextView dialogUserMobileTextView=(TextView)dialog.findViewById(R.id.staff_mobile_dialog_text_view);
                                TextView dialogUserPasswordTextView=(TextView)dialog.findViewById(R.id.staff_password_dialog_text_view);
                                Button dialogDismissButton=(Button)dialog.findViewById(R.id.dialog_dismiss_button);

                                dialogUserNameTextView.setText("Name: "+userName);
                                dialogUserEmailTextView.setText("Email Id: "+userEmail);
                                dialogUserMobileTextView.setText("Mobile Number: "+userMobile);
                                dialogUserPasswordTextView.setText("Password: "+password);

                                dialogDismissButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }

        });
    }
}
