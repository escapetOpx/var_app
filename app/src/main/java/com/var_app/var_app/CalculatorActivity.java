package com.var_app.var_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {
    private Spinner typeSpinner;
    EditText weightEdittext;
    Button calButton , nextButton;
    TextView valueTextview;
    TextView calbacktomenu;
//    String name, size, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        weightEdittext = (EditText) findViewById(R.id.wet);
        calButton = (Button) findViewById(R.id.calbutton);
        valueTextview = (TextView) findViewById(R.id.valuee);
        calbacktomenu = (TextView) findViewById(R.id.backtomenu);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_menu, contentFrameLayout);

        calbacktomenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(CalculatorActivity.this,
                        MenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        ArrayList<String> typeList = new ArrayList<>();
        final ArrayList<Integer> priceList = new ArrayList<>();

        typeList.add("กระดาษขาวดำ");
        typeList.add("กระดาษย่่อย");
        typeList.add("หนังสือพิมพ์");
        typeList.add("PET ใส");
        typeList.add("PET สี");
        typeList.add("อลูมิเนียมกระป๋อง");
        priceList.add(13);
        priceList.add(3);
        priceList.add(8);
        priceList.add(13);
        priceList.add(1);
        priceList.add(38);

        typeSpinner = (Spinner) findViewById(R.id.tspinner);
        typeSpinner.setAdapter(new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                typeList
        ));

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("position : ", String.valueOf(position));
                Log.i("price", String.valueOf(priceList.get(position)));
                calButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!weightEdittext.getText().toString().equals("")) {
                            int weight = Integer.parseInt(weightEdittext.getText().toString());
                            int i = weight * priceList.get(position);
                            Log.i("SUM", String.valueOf(i));
                            valueTextview.setText(String.valueOf(i));
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Please enter the weight!", Toast.LENGTH_LONG)
                                    .show();
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}
