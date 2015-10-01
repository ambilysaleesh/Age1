package com.sid.saleesh.newage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity  {

    TextView textView_current;
    TextView textView_dob;
    TextView textView_result;
    TextView textView_next;

    Button btn_calculate;
    ImageButton image_button;
    static  final int DIALOG_ID = 0;

    int start_day;
    int start_month;
    int start_year;

    int res_year;
    int res_month;
    int res_day;

    int prev_month;
    int prev_days;

    final Calendar c = Calendar.getInstance();
    int current_year = c.get(Calendar.YEAR);
    int current_month = c.get(Calendar.MONTH);
    int current_day = c.get(Calendar.DAY_OF_MONTH);



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_current = (TextView)findViewById(R.id.textView_current_id);
        textView_dob= (TextView)findViewById(R.id.textView_dob_id);
        textView_result = (TextView)findViewById(R.id.textView_result_id);
        textView_next = (TextView)findViewById(R.id.textView_next_id);

        textView_current.setText(new StringBuilder().append(current_day).append(" - ").append(current_month +1).append(" - ").append(current_year));

        showMydate();

        btn_calculate = (Button)findViewById(R.id.button_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isValidInput() == false) {
                    //Log.e("Popup", "I m popup");
                    startPopUpWindow();
                    return;
                }

                findMyAge();
                textView_result.setText(Integer.toString(res_year) + " years " + Integer.toString(res_month) + " months " + Integer.toString(res_day) + " days old ");


                textView_next.setText(Integer.toString(start_day) + "/" + Integer.toString(start_month) + "/" + Integer.toString(current_year + 1));


            }
        });


    }

    public void showMydate(){
        image_button = (ImageButton)findViewById(R.id.imageButton);

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);



            }
        });

    }

    public void startPopUpWindow(){
        startActivity(new Intent(MainActivity.this, Pop.class));
    }

    /* checks if date of birth seleted is Valid or not */
    public  boolean isValidInput(){
        boolean isValid = true;

        if(start_year ==0){
            isValid = false;

        }

        if(start_year > current_year) {
            isValid = false;
        }
        else if(start_year == current_year){
            if(start_month > current_month) isValid = false;

            if(start_month == current_month){
                if(start_day > current_day) isValid = false;
            }
        }

        return isValid;
    } /* endfn isValidInput */


    public boolean isLeapYear(int year){
        boolean is_leap = false;

        if(year%4 == 0){
            if(year%100==0){
                if(year%400==0){
                    is_leap = true;
                }
            }
            else{
                is_leap = true;
            }
        }
        else{
            is_leap = false;
        }

        return is_leap;
    }

    public void testLeapYear(){

        int test_array[] = {2000, 2001, 2002, 2004, 2008, 2009, 2096, 2100, 2200, 2300, 2396, 2400, 2401};
        int i;

        for(i=0; i<13; i++){
            boolean res = isLeapYear(test_array[i]);
            Log.e(Integer.toString(test_array[i]), Boolean.toString(res));
        }
    }




    /* Main logic of calculating the age in this function */
    public void findMyAge() {
        /*
        Log.e("Day", Integer.toString(start_day));
        Log.e("Month", Integer.toString(start_month));
        Log.e("Year", Integer.toString(start_year));
        */

        res_year  = current_year - start_year;
        res_month = 0;
        res_day = 0;

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

            if(res_month < 0) {
                res_year = res_year - 1;
                res_month = res_month + 12;
            }
        }

        if(current_day >= start_day) {
            res_day = current_day - start_day;
        }
        else {
            prev_month = current_month - 1;

            if(prev_month == 0){
                prev_month = 12;
            }
            prev_days = get_days_in_month(prev_month, current_year);
            res_day = current_day + prev_days - start_day;
        }

    } /* endfn calculateAge */



    public int get_days_in_month(int month, int year){
        int days = 30;

        if(month == 2) {
            days = 28;
            if(isLeapYear(year)) days = 29;
        }
        else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
            days = 31;
        }
        else {
            days = 30;
        }
        return (days);
    } /* endfn get_days_in_month() */





    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, current_year, current_month, current_day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            start_day = selectedDay;
            start_month = selectedMonth ;
            start_year = selectedYear;

            Log.e("start_day", Integer.toString(start_day));
            Log.e("start_month", Integer.toString(start_month));
            Log.e("start_year", Integer.toString(start_year));


            textView_dob.setText(new StringBuilder().append(start_day).append(" - ").append(start_month +1).append(" - ").append(start_year));


        }
    };




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

} /* end class MainActivity */
