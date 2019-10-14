package com.cs.learnenglish.Activites;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.cs.learnenglish.Adapters.CustomRVItemTouchListener;
import com.cs.learnenglish.Adapters.Recycler_View_Adapter;
import com.cs.learnenglish.Interfaces.RecyclerViewItemClickListener;
import com.cs.learnenglish.R;
import com.cs.learnenglish.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimalsActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    TextToSpeech textToSpeech;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer1) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Apple", "سیب", R.drawable.a));
        words.add(new Word("Ball", "توپ", R.drawable.b));
        words.add(new Word("Cat", "گربه", R.drawable.c));
        words.add(new Word("Doll", "عروسک", R.drawable.d));
        words.add(new Word("Elephant", "فیل", R.drawable.e));
        words.add(new Word("Fast", "سریع", R.drawable.f));
        words.add(new Word("Gorilla", "گوریل", R.drawable.g));
        words.add(new Word("Hat", "کلاه", R.drawable.h));
        words.add(new Word("Igloo", "خانه یخی", R.drawable.i));
        words.add(new Word("Juice", "آب میوه", R.drawable.j));
        words.add(new Word("Kite", "کاغذ باد", R.drawable.k));
        words.add(new Word("Lion", "شیر", R.drawable.l));
        words.add(new Word("Monkey", "میمون", R.drawable.m));
        words.add(new Word("No", "نخیر", R.drawable.n));
        words.add(new Word("Orange", "نارنجی", R.drawable.o));
        words.add(new Word("Piq", "خوک", R.drawable.p));
        words.add(new Word("Queen", "ملکه", R.drawable.q));
        words.add(new Word("Rabbit", "خرگوش", R.drawable.r));
        words.add(new Word("Soup", "سوپ", R.drawable.s));
        words.add(new Word("Tigger", "ببر", R.drawable.t));
        words.add(new Word("Umbrella", "چتری", R.drawable.u));
        words.add(new Word("Van", "موتر کلان", R.drawable.v));
        words.add(new Word("Window", "پنجره", R.drawable.w));
        words.add(new Word("box", "جعبه", R.drawable.x));
        words.add(new Word("Yellow", "زرد", R.drawable.y));
        words.add(new Word("Zoo", "باغ وحش", R.drawable.z));
        List<Word> data = words;

        final ArrayList<String> textstospeech = new ArrayList<>();
        textstospeech.add("A,Like,Apple");
        textstospeech.add("B,Like,Ball");
        textstospeech.add("C,Like,Cat");
        textstospeech.add("D,Like,Doll");
        textstospeech.add("E,Like,Elephant");
        textstospeech.add("F,Like,Fast");
        textstospeech.add("G,Like,Gorilla");
        textstospeech.add("H,Like,Hat");
        textstospeech.add("I,Like,Igloo");
        textstospeech.add("J,Like,Juice");
        textstospeech.add("K,Like,Kite");
        textstospeech.add("L,Like,Lion");
        textstospeech.add("M,Like,Monkey");
        textstospeech.add("N,Like,No");
        textstospeech.add("O,Like,Orange");
        textstospeech.add("P,Like,Pig");
        textstospeech.add("Q,Like,Queen");
        textstospeech.add("R,Like,Rabbit");
        textstospeech.add("S,Like,Soup");
        textstospeech.add("T,Like,Tiger");
        textstospeech.add("U,Like,Umbrella");
        textstospeech.add("V,Like,Van");
        textstospeech.add("W,Like,Window");
        textstospeech.add("X,Like,box");
        textstospeech.add("Y,Like,yellow");
        textstospeech.add("Z,Like,zoo");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Word word = words.get(position);
                textToSpeech.speak(textstospeech.get(position), TextToSpeech.QUEUE_FLUSH, null);

                releaseMediaPlayer();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
