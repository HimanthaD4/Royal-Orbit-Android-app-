package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartPizza extends AppCompatActivity {
private Button Update,Delete;

    TextView pepQLabel,pepPLabel,hawQLabel, hawPLabel,chesQLabel,chesPLabel,bbqQLabel,bbqPLabel,tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart_pizza);

        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.delete);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfoodMenu();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfoodMenu2();
            }
        });


      pepQLabel = findViewById(R.id.pepQLabel);
      pepPLabel = findViewById(R.id.pepPLabel);
tot = findViewById(R.id.tot);

      hawQLabel =findViewById(R.id.hawQLabel);
      hawPLabel = findViewById(R.id.pepPLabel);
        chesQLabel = findViewById(R.id.chesPLabel);
        chesPLabel = findViewById(R.id.chesPLabel);
        bbqQLabel =findViewById(R.id.bbqPLabel);
        bbqPLabel = findViewById(R.id.bbqPLabel);



      showData();



    }

    private void showData() {
        Intent intent = getIntent();

        String qH = intent.getStringExtra("quantityh");
        String pH = intent.getStringExtra("totalPriceh");

        String qC = intent.getStringExtra("quantityc");
        String pC = intent.getStringExtra("totalPricec");

        String qB = intent.getStringExtra("quantityb");
        String pB = intent.getStringExtra("totalPriceb");

        String qP = intent.getStringExtra("quantityp");
        String pP = intent.getStringExtra("totalPricep");
        String pPp = intent.getStringExtra("totalPricepp");


        hawQLabel.setText(qH);
        hawPLabel.setText(pH);

        pepQLabel.setText(qP);
        pepPLabel.setText(pP);
        tot.setText(pPp);

        bbqQLabel.setText(qB);
        bbqPLabel.setText(pB);

        chesQLabel.setText(qC);
        chesPLabel.setText(pC);

//        tot.setText(qH);

    }
    public void openfoodMenu( ){

        Intent intent = new Intent(this, PizzaMenu.class);
        startActivity(intent);
    }

    public void openfoodMenu2( ){

        Intent intent = new Intent(this, Foodmainmenu.class);
        Toast.makeText(this, "Successful delete", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}