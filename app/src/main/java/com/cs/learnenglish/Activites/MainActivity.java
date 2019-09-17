package com.cs.learnenglish.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.cs.learnenglish.R;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout=(GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(gridLayout);

    }

    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI){
                        case 0:
                            Intent intent = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            /*Intent intent1 = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent1);*/
                            Toast.makeText(MainActivity.this, "Numbers", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            /*Intent intent2 = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent2);*/
                            Toast.makeText(MainActivity.this, "Family Members", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            /*Intent intent3 = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent3);*/
                            Toast.makeText(MainActivity.this, "Colors", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            /*Intent intent4 = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent4);*/
                            Toast.makeText(MainActivity.this, "Animals", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            /*Intent intent5 = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent5);*/
                            Toast.makeText(MainActivity.this, "Phrases", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });
        }
    }




}