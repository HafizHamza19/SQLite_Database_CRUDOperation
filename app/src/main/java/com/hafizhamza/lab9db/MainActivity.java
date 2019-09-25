package com.hafizhamza.lab9db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, fathername,address,state,dob,email,search;
    TextView showRecord;
    Spinner city;
    LinearLayout searchbox;
    databaseclass databaseFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (Spinner) findViewById(R.id.id_city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.city));
        city.setAdapter(adapter);
        name = (EditText) findViewById(R.id.id_Name);
        fathername = (EditText) findViewById(R.id.id_FatherName);
        address = (EditText) findViewById(R.id.id_address);
        state = (EditText) findViewById(R.id.id_state);
        dob = (EditText) findViewById(R.id.id_dob);
        email = (EditText) findViewById(R.id.id_email);
        search = (EditText) findViewById(R.id.id_search);
        showRecord = (TextView) findViewById(R.id.id_record_show);
        searchbox=(LinearLayout)findViewById(R.id.id_searchbox);
        databaseFile = new databaseclass(this);
    }
//////////Record Insert ////////////
    public void save(View view) {
        try {
            databaseFile.Registration(name.getText().toString(),fathername.getText().toString(),address.getText().toString()
                    ,state.getText().toString(),city.getSelectedItem().toString(),dob.getText().toString(),email.getText().toString());
            Toast.makeText(this, "You Have Registered", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

        }

    }
    //////////Record Update ////////////
    public void update(View view) {
        databaseFile.Update(Integer.parseInt(search.getText().toString()),name.getText().toString(),fathername.getText().toString(),address.getText().toString()
                ,state.getText().toString(),city.getSelectedItem().toString(),dob.getText().toString(),email.getText().toString());
        Toast.makeText(this, "You Have Updated Successfully", Toast.LENGTH_LONG).show();
    }

    //////////Record Delete ////////////
    public void deleteProduct(View view) {
        databaseFile.Delete(Integer.parseInt(search.getText().toString()));
        Toast.makeText(this,"Record Deleted OF Student Roll No = "+Integer.parseInt(search.getText().toString()),Toast.LENGTH_SHORT).show();
    }

    //////////Record Select ////////////
    public void select(View view) {
        ///////////Get Record Through Id Ony That Column Show/////////
        String text = databaseFile.getSpecificRecord(Integer.parseInt(search.getText().toString())).toString(); //this is the method to query
        showRecord.setText(text);
        /////////////Get All Column//////////
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList=databaseFile.getAllRecord();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        city.setAdapter(adapter);
    }
}
