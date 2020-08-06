package com.cs.learnenglish.Activites;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cs.learnenglish.Adapters.CustomRVItemTouchListener;
import com.cs.learnenglish.Adapters.Recycler_View_Adapter;
import com.cs.learnenglish.Interfaces.RecyclerViewItemClickListener;
import com.cs.learnenglish.R;
import com.cs.learnenglish.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlphabetActivity extends AppCompatActivity {


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
        words.add(new Word("A", "آ ، ا", R.drawable.a));
        words.add(new Word("B", "ب", R.drawable.b));
        words.add(new Word("C", "ک ، س", R.drawable.c));
        words.add(new Word("D", "د", R.drawable.d));
        words.add(new Word("E", "إ", R.drawable.e));
        words.add(new Word("F", "ف", R.drawable.f));
        words.add(new Word("G", "گ ، ج", R.drawable.g));
        words.add(new Word("H", "ح ، ه", R.drawable.h));
        words.add(new Word("I", "آی ، إ ، ی", R.drawable.i));
        words.add(new Word("J", "ج", R.drawable.j));
        words.add(new Word("K", "ک", R.drawable.k));
        words.add(new Word("L", "ل", R.drawable.l));
        words.add(new Word("M", "م", R.drawable.m));
        words.add(new Word("N", "ن", R.drawable.n));
        words.add(new Word("O", "او ، آ", R.drawable.o));
        words.add(new Word("P", "پ", R.drawable.p));
        words.add(new Word("Q", "ک ، کؤ", R.drawable.q));
        words.add(new Word("R", "ر", R.drawable.r));
        words.add(new Word("S", "س", R.drawable.s));
        words.add(new Word("T", "ت", R.drawable.t));
        words.add(new Word("U", "یو ، أ", R.drawable.u));
        words.add(new Word("V", "ؤ", R.drawable.v));
        words.add(new Word("W", "و", R.drawable.w));
        words.add(new Word("x", "اکس", R.drawable.x));
        words.add(new Word("Y", "ی ، ي", R.drawable.y));
        words.add(new Word("Z", "ز", R.drawable.z));
        List<Word> data = words;


        RecyclerView recyclerView =  findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
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