package com.enggemy22.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn  = (Button)findViewById(R.id.numbers);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_number = new Intent(MainActivity.this,Numbers.class);
                startActivity(intent_number);
            }
        });


        Button btn2  = (Button)findViewById(R.id.family_members);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public  void onClick(View v)
            {
                Intent intent_family =new Intent(MainActivity.this, FamilyMembers.class);
                startActivity(intent_family);

            }

        });

    }

    public void Color(View view) {
        Intent intent_color= new Intent(this,Color.class);
        startActivity(intent_color);
    }

    public void phrases(View view) {
        Intent intent_pharses = new Intent(this,pharases.class);
        startActivity(intent_pharses);
    }
}
