package intern.tfi.org.prework;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewOppFellowFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRefVolOpp;
    RecyclerView mRecyclerView;
    RecyclerAdapter recyclerAdapter;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<VolunteeringOpportunities> volunteeringOpportunitiesArrayList =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_opp_fellow, container, false);
        database=FirebaseDatabase.getInstance();
        myRefVolOpp=database.getReference("vol_opp");
        recyclerAdapter=new RecyclerAdapter(volunteeringOpportunitiesArrayList);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        mLinearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(recyclerAdapter);


        myRefVolOpp.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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
                VolunteeringOpportunities volunteeringOpportunities=new VolunteeringOpportunities(title,city,area,description,start,end,days,time,email,type);
                volunteeringOpportunitiesArrayList.add(volunteeringOpportunities);
                recyclerAdapter.notifyDataSetChanged();
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

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView.removeAllViewsInLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.removeAllViewsInLayout();
    }
}
