package com.example.royalorbitlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PizzaMenu extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();


   // DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://royal-orbit-last-default-rtdb.firebaseio.com/");

    private ImageButton imageButton30;
    private ImageButton imageButton29;
    EditText editTextNumber5,editTextNumber6,editTextNumber7,editTextNumber8;
    private ImageButton imageButton20;
    private ImageButton imageButton17;
    private ImageButton imageButton27;
    private ImageButton imageButton28;
    private ImageButton cartbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pizza_menu);

        DatabaseReference myRef = database.getReference("food orders");

        editTextNumber5 = findViewById(R.id.editTextNumber5);
        editTextNumber6 = findViewById(R.id.editTextNumber6);
        editTextNumber7 = findViewById(R.id.editTextNumber7);
        editTextNumber8 = findViewById(R.id.editTextNumber8);

       cartbtn = (ImageButton)findViewById(R.id.cartbtn);
        imageButton28 = (ImageButton)findViewById(R.id.imageButton28);
        imageButton20 = (ImageButton)findViewById(R.id.imageButton20);
        imageButton17 = (ImageButton)findViewById(R.id.imageButton17);
        imageButton27 = (ImageButton)findViewById(R.id.imageButton27);

cartbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        opencart();
    }
});

        imageButton28.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String hawQuantityTxt = editTextNumber8.getText().toString();

                final int hawQuantity = Integer.parseInt(hawQuantityTxt);
                final int totHawPrice = hawQuantity * 3500;
//                    final String total =
                String tothaw = String.valueOf(totHawPrice);

                myRef.child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        myRef.child("pizza").child("hawPizza").child("quantity").setValue(hawQuantity);
                        myRef.child("pizza").child("hawPizza").child("totalPrice").setValue(totHawPrice);

                        Toast.makeText(PizzaMenu.this, "Add to cart successful", Toast.LENGTH_SHORT).show();

                        String Quantith = hawQuantityTxt;
                        String toth = tothaw;

                        Intent intent = new Intent(getApplicationContext(),CartPizza.class);

                        intent.putExtra("quantityh",Quantith);
                        intent.putExtra("totalPriceh",toth);

                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }));


        imageButton27.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bbqQuantityTxt = editTextNumber7.getText().toString();

                final int bbqQuantity = Integer.parseInt(bbqQuantityTxt);
                final int totBbqPrice = bbqQuantity * 2800;

                String totbbq = String.valueOf(totBbqPrice);

                myRef.child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        myRef.child("pizza").child("bbq").child("quantity").setValue(bbqQuantity);
                        myRef.child("pizza").child("bbq").child("totalPrice").setValue(totBbqPrice);

                        Toast.makeText(PizzaMenu.this, "Add to cart successful", Toast.LENGTH_SHORT).show();

                        String Quantityb = bbqQuantityTxt;
                        String totb = totbbq;


                        Intent intent = new Intent(getApplicationContext(),CartPizza.class);

                        intent.putExtra("quantityb",Quantityb);
                        intent.putExtra("totalPriceb",totb);

                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }));




        imageButton17.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cheeseQuantityTxt = editTextNumber6.getText().toString();

                final int cheeseQuantity = Integer.parseInt(cheeseQuantityTxt);
                final int totCheesePrice = cheeseQuantity * 3000;


                String totCheese = String.valueOf(totCheesePrice);

                myRef.child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        myRef.child("pizza").child("cheese").child("quantity").setValue(cheeseQuantity);
                        myRef.child("pizza").child("cheese").child("totalPrice").setValue(totCheesePrice);

                        Toast.makeText(PizzaMenu.this, "Add to cart successful", Toast.LENGTH_SHORT).show();


                        String Quantityc = cheeseQuantityTxt;
                        String totc = totCheese;

                        Intent intent = new Intent(getApplicationContext(),CartPizza.class);

                        intent.putExtra("quantityc",Quantityc);
                        intent.putExtra("totalPricec",totc);

                        startActivity(intent);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }));




        imageButton20.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final String pepQuantityTxt = editTextNumber5.getText().toString();

        final int pepQuantity = Integer.parseInt(pepQuantityTxt);
        final int totPepPrice = pepQuantity * 2500;

        String totpep = String.valueOf(totPepPrice);

        myRef.child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myRef.child("pizza").child("pepPizza").child("quantity").setValue(pepQuantity);
                myRef.child("pizza").child("pepPizza").child("totalPrice").setValue(totPepPrice);

                Toast.makeText(PizzaMenu.this, "Add to cart successful", Toast.LENGTH_SHORT).show();

                String Quantityp = pepQuantityTxt;
                String totp = totpep;
                String totpp = totpep;

                Intent intent = new Intent(getApplicationContext(),CartPizza.class);

                intent.putExtra("quantityp",Quantityp);
                intent.putExtra("totalPricep",totp);
                intent.putExtra("totalPricepp",totpep);

                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
});




























//
//final int pepporani = 0;
//final int cheese = 0;
//final int bbq=0;
//final int hawana = 0;
//
//        final double pepPrice = 0.0;
//        final double cheesePrice = 0.0;
//        final double bbqPrice=0.0;
//        final double hawanaPrice = 0.0;



//        imageButton28.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(editTextNumber8.getText().toString())){
//                    editTextNumber8.setError("Quantity is Compulsory");
//                    return;
//                }
//                else{
////                    openCartFood();
//
//
//                    final String type1 = "Hawana";
//                    final int hawana = Integer.parseInt(editTextNumber8.getText().toString());
//                    double PepPRice = 3500.0 * hawana;
//
//
//
//                }
//            }
//        });


//        imageButton27 = (ImageButton) findViewById(R.id.imageButton27);
//        imageButton27.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(editTextNumber7.getText().toString())){
//                    editTextNumber7.setError("Quantity is Compulsory");
//                    return;
//                }
//                else{
////                    openCartFood();
//
//
//                    final String type3 = "BBQ";
//                    final int BBQ = Integer.parseInt(editTextNumber7.getText().toString());
//                    double PepPRice = 2800.0 * BBQ;
//                }
//            }
//        });

//        imageButton17 = (ImageButton) findViewById(R.id.imageButton17);
//        imageButton17.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(editTextNumber6.getText().toString())){
//                    editTextNumber6.setError("Quantity is Compulsory");
//                    return;
//                }
//                else{
////                    openCartFood();
//
//                    final String type2 = "cheese";
//                    final int cheese = Integer.parseInt(editTextNumber6.getText().toString());
//                    double cheesePrice = 3000.0 * cheese;
//                }
//            }
//        });



//        imageButton20 = (ImageButton) findViewById(R.id.imageButton20);
//        imageButton20.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(editTextNumber5.getText().toString())){
//                    editTextNumber5.setError("Quantity is Compulsory");
//                    return;
//                }
//                else{
////                    openCartFood();
//
//                    final String type1 = "Pepperoni";
//                    final int pepporani = Integer.parseInt(editTextNumber5.getText().toString());
//                    double PepPrice = 2500.0 * pepporani;
//                }
//            }
//
//        });

//        imageButton29 = (ImageButton) findViewById(R.id.imageButton29);
//        imageButton29.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFoodmainmenu();
//            }
//        });


//        imageButton30 = (ImageButton) findViewById(R.id.imageButton30);
//        imageButton30.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCartFood();
//            }
//        });









    }
    public void openFoodmainmenu(){
        Intent intent = new Intent(this,Foodmainmenu.class);
        startActivity(intent);
    }
    public void opencart(){
        Intent intent = new Intent(this,CartPizza.class);
        startActivity(intent);
    }
}