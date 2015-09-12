package com.example.saleesh.agecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

    Spinner spinday ;
    Spinner spinmonth;
    Spinner spinyear;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinday = (Spinner)findViewById(R.id.spinnerdate);
        spinmonth = (Spinner)findViewById(R.id.spinnermonth);
        spinyear = (Spinner)findViewById(R.id.spinneryear);

        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter.createFromResource(this, R.array.date, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_year = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);

        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinday.setAdapter(adapter_day);

        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinmonth.setAdapter(adapter_month);

        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinyear.setAdapter(adapter_year);

        spinday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " day selected ", Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " month selected ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " year selected ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
