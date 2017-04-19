package com.var_app.var_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanerBarcode extends AppCompatActivity {

    private TextView result;
    private Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaner_barcode);

        scan = (Button)findViewById(R.id.scan);
        result = (TextView)findViewById(R.id.reslut);

        scan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TODO Auto-generated method stub
                try{
                    Intent intentOption = new Intent("com.google.zxing.client.android.SCAN");
                    intentOption.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intentOption,0);
                } catch(Exception e){
                    //TODO handle exception
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                String contents = getIntent().getStringExtra("SCAN_RESULT");
                result.setText(contents);
            }
        }
    }
}
