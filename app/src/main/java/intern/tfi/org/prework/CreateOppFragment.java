package intern.tfi.org.prework;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateOppFragment extends Fragment {


    FirebaseDatabase database;
    DatabaseReference myRefVolOpp;
    Spinner volunteerTypeSpinner,volunteerDaySpinner;
    EditText titleEditText,cityEditText,areaEditText,emailEditText,startEditText,endEditText,timeEditText,descriptionEditText;
    ArrayAdapter<CharSequence> adapterType,adapterDays;
    Button createOppButton;
    String title,city,area,email,start,end,time,description,days,type,userid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_opp, container, false);

        final SharedPreferences sharedpreferences=getActivity().getSharedPreferences(getString(R.string.shared_preference_name),Context.MODE_PRIVATE);
        database=FirebaseDatabase.getInstance();
        myRefVolOpp=database.getReference("vol_opp");
        volunteerTypeSpinner=(Spinner)view.findViewById(R.id.volunteer_type_spinner);
        adapterType=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.volunteer_type,android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volunteerTypeSpinner.setAdapter(adapterType);
        volunteerDaySpinner=(Spinner)view.findViewById(R.id.opp_days_spinner);
        adapterDays=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.volunteer_days,android.R.layout.simple_spinner_item);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volunteerDaySpinner.setAdapter(adapterDays);


        createOppButton=(Button)view.findViewById(R.id.opp_create_button);
        titleEditText=(EditText)view.findViewById(R.id.opp_title_edit_text);
        cityEditText=(EditText)view.findViewById(R.id.opp_city_edit_text);
        areaEditText=(EditText)view.findViewById(R.id.opp_area_edit_text);
        emailEditText=(EditText)view.findViewById(R.id.opp_email_edit_text);
        startEditText=(EditText)view.findViewById(R.id.opp_start_date_edit_text);
        endEditText=(EditText)view.findViewById(R.id.opp_end_date_edit_text);
        timeEditText=(EditText)view.findViewById(R.id.opp_time_edit_text);
        descriptionEditText=(EditText)view.findViewById(R.id.opp_description_edit_text);

        createOppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=titleEditText.getText().toString();
                city=cityEditText.getText().toString();
                area=areaEditText.getText().toString();
                email=emailEditText.getText().toString();
                start=startEditText.getText().toString();
                end=endEditText.getText().toString();
                time=timeEditText.getText().toString();
                description=descriptionEditText.getText().toString();
                days=volunteerDaySpinner.getSelectedItem().toString();
                type=volunteerTypeSpinner.getSelectedItem().toString();

                userid=sharedpreferences.getString(getString(R.string.sp_userid),"");

                if(title.equals("") || city.equals("") || area.equals("") || email.equals("") || start.equals("") || end.equals("") || time.equals("") || description.equals("") || days.equals("") || type.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(time)>12){
                    Toast.makeText(getActivity().getApplicationContext(), "Please Enter A Valid Time", Toast.LENGTH_SHORT).show();
                    timeEditText.setText("");
                }
                else {
                    String id = myRefVolOpp.push().getKey();
                    myRefVolOpp.child(id).setValue(new VolunteerOpportunityInfo(title, city, area, email, type, start, end, time, days, description, userid));

                    titleEditText.setText("");
                    cityEditText.setText("");
                    areaEditText.setText("");
                    emailEditText.setText("");
                    startEditText.setText("");
                    endEditText.setText("");
                    timeEditText.setText("");
                    descriptionEditText.setText("");
                }
            }
        });

        return view;
    }
}
