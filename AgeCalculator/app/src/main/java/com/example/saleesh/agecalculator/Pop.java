package com.example.saleesh.agecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ambily saleesh on 22/09/15.
 */
public class Pop extends Activity {
    TextView popupMessage;

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        popupMessage = (TextView)findViewById(R.id.text_message);
        Button ok_button = (Button)findViewById(R.id.button_ok);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.1));
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Popup", "I m OK btn");
                finish();
            }

        });
    }
}
