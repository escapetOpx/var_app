package com.var_app.var_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by escapetOpx on 4/21/2017.
 */

public class SellBuyActivity extends Activity {
    private ImageButton sellButton, buyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellbuy);

        sellButton = (ImageButton) findViewById(R.id.sellButton);
        buyButton = (ImageButton) findViewById(R.id.buyButton);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellBuyActivity.this, SellActivity.class);
                startActivity(intent);

            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellBuyActivity.this, BuyActivity.class);
                startActivity(intent);

            }
        });


    }
}

