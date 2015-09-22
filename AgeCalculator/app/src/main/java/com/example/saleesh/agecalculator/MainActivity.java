package com.example.saleesh.agecalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity  {

    Spinner spinday ;
    Spinner spinmonth;
    Spinner spinyear;
    Button btn_submit;
    TextView text_today;
    TextView text_result;
    TextView popupmessage;
    //TextView text_year;
   // TextView text_month;
    //TextView text_day;

    LinearLayout layoutOfPopup;
    PopupWindow popupMessage;
    TextView popupText;

    int res_year ;
    int res_month;
    int res_day;

    final Calendar c = Calendar.getInstance();
    int current_year = c.get(Calendar.YEAR);
    int current_month = c.get(Calendar.MONTH);
    int current_day = c.get(Calendar.DAY_OF_MONTH);

    int start_day;
    int start_month;
    int start_year;

    int yy;
    int prev_month;
    int prev_days;
    int month;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinday = (Spinner)findViewById(R.id.spinnerdate);
        spinmonth = (Spinner)findViewById(R.id.spinnermonth);
        spinyear = (Spinner)findViewById(R.id.spinneryear);
        btn_submit = (Button)findViewById(R.id.button);
        text_result = (TextView)findViewById(R.id.textResult);
        //ok_button = (Button)findViewById(R.id.button_ok);
        //text_year   =  (TextView)findViewById(R.id.textView_year);
        //text_month   =  (TextView)findViewById(R.id.textView_month);
        //text_day   =  (TextView)findViewById(R.id.textView_day);


        //current_month starts from 0
        current_month = current_month + 1;

        text_today = (TextView)findViewById(R.id.textCurrent_day);
        text_today.setText("Today is :" + new StringBuilder().append(current_day).append(" ").append("-").append(current_month).append("-")
                .append(current_year));

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
               // Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " day selected ", Toast.LENGTH_LONG).show();
                String str_start_day = parent.getItemAtPosition(position).toString();
                //text_result.setText(str_start_day );

                start_day = Integer.parseInt(str_start_day);


            }

            public void onNothingSelected(AdapterView<?> parent) {
                start_day = 5;
            }
        });


        spinmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " month selected ", Toast.LENGTH_LONG).show();
                String str_start_month = parent.getItemAtPosition(position).toString();
                //text_result.setText(str_start_month );

                start_month = Integer.parseInt(str_start_month);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " year selected ", Toast.LENGTH_LONG).show();
                String str_start_year = parent.getItemAtPosition(position).toString();
                //text_result.setText(str_start_year);
                start_year = Integer.parseInt(str_start_year );



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.e("exp", "My button--------------------------------------------------------------------------------------");

                //1. Get current Year, month, day here

                if( start_year > 0){
                    Log.e("Popup", "I m popup");
                    startPopUpWindow();
                    return;
                }


                calculateAge();
            }
        });


    }

    public void startPopUpWindow(){
        startActivity(new Intent(MainActivity.this,Pop.class));
    }












    //private void showToastErrorMessage(){
    //show toast
       // Toast.makeText(getBaseContext(), "heeya", Toast.LENGTH_LONG).show();
    //}




    public void calculateAge() {

        //res_Year = (current_year - start_year);
        //text_year.append(start_year);
       //text_year.setText(start_year);


        //current_day = 15;
        //current_month = 9;
        //current_year = 2015;


        //Log.e("hi", Integer.toString(current_day));
        //Log.e("hi", Integer.toString(current_month ));
        //Log.e("hi", Integer.toString(current_year ));
        Log.e("Day", Integer.toString(start_day));
        Log.e("Month", Integer.toString(start_month));
        Log.e("Year", Integer.toString(start_year));

        //start_day = 7;
        //start_month = 9;
        //start_year = 1982;

        //if( start_year > current_year ){




        res_year  = current_year - start_year;
        //res_month = 0;
        //res_day = 0;

        if(current_month >= start_month) {
            res_month = current_month - start_month;
        }
        else{

        res_month = current_month - start_month;
        res_month = res_month + 12;
        res_year = res_year - 1;
        }
        if(current_day < start_day) {
            res_month = res_month - 1;
        }



        if(current_day >= start_day) {
            res_day = current_day - start_day;
        }
        else {
            prev_month = current_month - 1;

          if(prev_month == 0){
             prev_month = 12;
          }
             prev_days = get_days(prev_month);
             res_day = current_day + prev_days - start_day;

        }
        Log.e("hi","88888888888888888888888888888888888888888888888888888888888888888888888");
        //res_year = 100;
        text_result.setText("Years: " + Integer.toString(res_year) + " Months: " + Integer.toString(res_month) + " Days: " + Integer.toString(res_day));
    }




    public int get_days(int month){
        int days = 30;

        if(month == 2)
        days = 28;
        else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
        days = 31;
        else
        days = 30;
        return (days);

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
