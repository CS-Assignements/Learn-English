package com.cs.learnenglish;

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
import com.cs.learnenglish.Adapters.Recycler_Adapter_NoImage;
import com.cs.learnenglish.Interfaces.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DialogueActivity extends AppCompatActivity {


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


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("What is your name?", "نام شما چیست؟"));
        words.add(new Word("My name is Simaye", "نام من سیمای است"));
        words.add(new Word("What is your last name?", "تخلص شما چیست؟"));
        words.add(new Word("My last name is Noor", "تخلص من نور است"));
        words.add(new Word("What is your father's name?", "نام پدر شما چیست؟"));
        words.add(new Word("My father's name is Ahmad", "نام پدر من احمد است"));
        words.add(new Word("How old are you?", "شما چند ساله هستید؟"));
        words.add(new Word("I am 3 years old", "من سه ساله هستم"));
        words.add(new Word("What is your job?", "وظیفه شما چیست؟"));
        words.add(new Word("I am a student", "من یک شااگرد هستم"));
        words.add(new Word("What do you like?", "شما چی دوست دارید؟"));
        words.add(new Word("I like reading books", "من مطالعه کردن کتاب را دوست دارم"));
        List<Word> data = words;


        RecyclerView recyclerView =  findViewById(R.id.recyclerview);
        Recycler_Adapter_NoImage adapter = new Recycler_Adapter_NoImage(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Word word = words.get(position);
                textToSpeech.speak(words.get(position).getmEnglishTranslation(), TextToSpeech.QUEUE_FLUSH, null);

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