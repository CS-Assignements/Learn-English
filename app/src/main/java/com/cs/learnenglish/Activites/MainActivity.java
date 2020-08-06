package com.cs.learnenglish.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.cs.learnenglish.DialogueActivity;
import com.cs.learnenglish.R;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout=findViewById(R.id.mainGrid);

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
                            intent = new Intent(MainActivity.this, AlphabetActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, NumbersActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, FamilyMembers.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, ColorsActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(MainActivity.this, AnimalsActivity.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(MainActivity.this, DialogueActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }
    }




}