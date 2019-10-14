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

public class NumbersActivity extends AppCompatActivity {


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
        words.add(new Word("One", "یک", R.drawable.one));
        words.add(new Word("Two", "دو", R.drawable.two));
        words.add(new Word("Three", "سه", R.drawable.three));
        words.add(new Word("Four", "چهار", R.drawable.four));
        words.add(new Word("Five", "پنج", R.drawable.five));
        words.add(new Word("Six", "شش", R.drawable.six));
        words.add(new Word("Seven", "هفت", R.drawable.seven));
        words.add(new Word("Eight", "هشت", R.drawable.eight));
        words.add(new Word("Nine", "نه", R.drawable.nine));
        words.add(new Word("Ten", "ده", R.drawable.ten));

        List<Word> data = words;

        final ArrayList<String> textstospeech = new ArrayList<>();
        textstospeech.add("One");
        textstospeech.add("Two");
        textstospeech.add("Three");
        textstospeech.add("Four");
        textstospeech.add("Five");
        textstospeech.add("Six");
        textstospeech.add("Seven");
        textstospeech.add("Eight");
        textstospeech.add("Nine");
        textstospeech.add("Ten");


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
