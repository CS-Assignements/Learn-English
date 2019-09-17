package com.cs.learnenglish.Activites;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.cs.learnenglish.Adapters.Recycler_View_Adapter;
import com.cs.learnenglish.R;
import com.cs.learnenglish.Word;

import java.util.ArrayList;
import java.util.List;

public class AlphabetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "یک", R.drawable.one, R.raw.one));
        words.add(new Word("Two", "دو", R.drawable.two, R.raw.two));
        words.add(new Word("Three", "سه", R.drawable.three, R.raw.three));
        words.add(new Word("Four", "چهار", R.drawable.four, R.raw.four));
        words.add(new Word("Five", "پنج", R.drawable.five, R.raw.five));
        words.add(new Word("Six", "شش", R.drawable.six, R.raw.six));
        words.add(new Word("Seven", "هفت", R.drawable.seven, R.raw.seven));
        words.add(new Word("Eight", "هشت", R.drawable.eight, R.raw.eight));
        words.add(new Word("Nine", "نه", R.drawable.nine, R.raw.nine));
        words.add(new Word("Ten", "ده", R.drawable.ten, R.raw.ten));

        List<Word> data = words;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
