package intern.tfi.org.prework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchCriteriaActivity extends Activity {


    Spinner searchTypeSpinner;
    Button searchButton;
    EditText searchValueEditText;

    ArrayAdapter<CharSequence> spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_criteria);
        searchTypeSpinner=(Spinner)findViewById(R.id.search_spinner);
        searchButton=(Button)findViewById(R.id.search_criteria_button);
        searchValueEditText=(EditText)findViewById(R.id.search_value_edit_text);
        SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        spinnerAdapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.search_type_array,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(spinnerAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int searchType=searchTypeSpinner.getSelectedItemPosition();
                if(!searchValueEditText.getText().toString().equals("") && searchType!=5){
                    editor.putInt("searchType",searchType);
                    editor.putString("searchVal",searchValueEditText.getText().toString());
                    editor.commit();
                    Intent volunteerStartIntent=new Intent(getApplicationContext(),VolunteerStart.class);
                    startActivity(volunteerStartIntent);
                }
                else if(searchType==5){
                    editor.putInt("searchType",-1);
                    editor.commit();
                    Intent volunteerStartIntent=new Intent(getApplicationContext(),VolunteerStart.class);
                    startActivity(volunteerStartIntent);
                }
                else {
                    Toast.makeText(SearchCriteriaActivity.this, "Please Enter Value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
