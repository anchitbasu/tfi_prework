package intern.tfi.org.prework;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VolunteerStart extends Activity {

    ImageView backgroundImageView;
    String sendEmailId,sendUid,sendName;
    FirebaseDatabase database;
    DatabaseReference myRefVolOpp,myRefVolOppSignup,myRefUsers;
    RecyclerView mRecyclerView;
    RecyclerAdapter recyclerAdapter;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<VolunteeringOpportunities> volunteeringOpportunitiesArrayList =new ArrayList<>();
    ImageView searchCriteriaButton;
    SharedPreferences sharedPreferences;
    String USERID,NAME,MOBILE,EMAIL,VOLOPPID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_start);
        backgroundImageView=(ImageView) findViewById(R.id.volunteer_start_background_image_view);
        Glide.with(this).load(R.drawable.background_3).into(backgroundImageView);
        database=FirebaseDatabase.getInstance();
        myRefVolOpp=database.getReference("vol_opp");
        myRefVolOppSignup=database.getReference("vol_opp_signup");
        myRefUsers=database.getReference("users");
        searchCriteriaButton=(ImageView) findViewById(R.id.search_fab);
        recyclerAdapter=new RecyclerAdapter(volunteeringOpportunitiesArrayList);
        mRecyclerView=(RecyclerView)findViewById(R.id.volunteer_recycler_view);
        mLinearLayoutManager=new LinearLayoutManager(getApplicationContext());
        sharedPreferences=getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE);


        USERID=sharedPreferences.getString(getString(R.string.sp_userid),"");
        MOBILE=sharedPreferences.getString(getString(R.string.sp_mobile),"");
        EMAIL=sharedPreferences.getString(getString(R.string.sp_email),"");
        NAME=sharedPreferences.getString(getString(R.string.sp_name),"");

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(recyclerAdapter);

        searchCriteriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchCriteriaIntent=new Intent(getApplicationContext(),SearchCriteriaActivity.class);
                startActivity(searchCriteriaIntent);
            }
        });

        final SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE);
        final int searchType=sharedPreferences.getInt("searchType",-1);
        final String searchVal=sharedPreferences.getString("searchVal","");

        myRefVolOpp.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                VOLOPPID=dataSnapshot.getKey().toString();
                String title=dataSnapshot.child("title").getValue().toString();
                String city=dataSnapshot.child("city").getValue().toString();
                String area=dataSnapshot.child("area").getValue().toString();
                String description=dataSnapshot.child("description").getValue().toString();
                String start=dataSnapshot.child("start").getValue().toString();
                String end=dataSnapshot.child("end").getValue().toString();
                String days=dataSnapshot.child("days").getValue().toString();
                String time=dataSnapshot.child("time").getValue().toString();
                String email=dataSnapshot.child("emailid").getValue().toString();
                String type=dataSnapshot.child("type").getValue().toString();

                if(searchType==0){
                    if(title.equalsIgnoreCase(searchVal)){
                        VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                        volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                else if(searchType==1){
                    if(city.equalsIgnoreCase(searchVal)){
                        VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                        volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                else if(searchType==2){
                    if(start.equalsIgnoreCase(searchVal)){
                        VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                        volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                else if(searchType==3){
                    if(time.equalsIgnoreCase(searchVal)){
                        VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                        volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                else if(searchType==4){
                    if(type.equalsIgnoreCase(searchVal)){
                        VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                        volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                else if(searchType==-1){
                    VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                    volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                    recyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        final String title=volunteeringOpportunitiesArrayList.get(position).getTitle();


                        Toast.makeText(VolunteerStart.this, volunteeringOpportunitiesArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        final Dialog dialog=new Dialog(VolunteerStart.this);
                        dialog.setContentView(R.layout.volunteer_opp_signup_dialog_layout);
                        dialog.setTitle("SignUp");

                        myRefVolOpp.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                                    if(dsp.child("title").getValue().equals(title)){
                                        sendEmailId=dsp.child("emailid").getValue().toString();
                                        sendUid=dsp.child("userid").getValue().toString();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        myRefUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                                    if(dsp.getKey().toString().equals(sharedPreferences.getString("senduid",""))){
                                        sendName=dsp.child("name").getValue().toString();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



                        final EditText reasonEditText=(EditText) dialog.findViewById(R.id.vol_opp_signup_why_edit_text);
                        final EditText experienceEditText=(EditText) dialog.findViewById(R.id.vol_opp_signup_prev_experience_edit_text);
                        final EditText commitTimeEditText=(EditText) dialog.findViewById(R.id.vol_opp_signup_commit_time_edit_text);
                        Button volOppSignUpButton=(Button) dialog.findViewById(R.id.vol_opp_signup_button);

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;



                        volOppSignUpButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String reason=reasonEditText.getText().toString();
                                String experience=experienceEditText.getText().toString();
                                String commitTime=commitTimeEditText.getText().toString();

                                if(reason.equals("") || experience.equals("") || commitTime.equals("") || Integer.parseInt(commitTime)>24){
                                    Toast.makeText(VolunteerStart.this, "Invalid Input. Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    myRefVolOppSignup.child(VOLOPPID).child(USERID).setValue(new VolOppSignUpInfo(NAME, EMAIL, MOBILE, reason, experience, commitTime));
                                    Toast.makeText(VolunteerStart.this, "Successfully SignedUp ", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                    SendMail1 sendMail1 = new SendMail1(getApplicationContext(), sendEmailId, "Volunteer Sign Up Information", "Hi " + sendName + "!!      " + NAME + " (" + MOBILE + ") (" + EMAIL + ") has signed for opportunity '" + title + "' created by you.      Reason:" + reason + "              Volunteering Experience:" + experience + "              Time that can be committed per day:" + commitTime);
                                    sendMail1.execute();
                                }
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setAttributes(lp);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }

                })
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent loginIntent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(loginIntent);
    }
}
