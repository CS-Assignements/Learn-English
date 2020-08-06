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
import com.cs.learnenglish.Adapters.Recycler_Adapter;
import com.cs.learnenglish.Interfaces.RecyclerViewItemClickListener;
import com.cs.learnenglish.R;
import com.cs.learnenglish.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FamilyMembers extends AppCompatActivity {


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
        words.add(new Word("Father", "پدر", R.drawable.father));
        words.add(new Word("Mother", "مادر", R.drawable.mother));
        words.add(new Word("Grandpa", "پدر بزرگ", R.drawable.grandpa));
        words.add(new Word("Grandma", "مادر بزرگ", R.drawable.grandma));
        words.add(new Word("Brother", "برادز", R.drawable.brother));
        words.add(new Word("Sister", "خواهر", R.drawable.sister));
        words.add(new Word("Son", "پسر", R.drawable.son));
        words.add(new Word("Daughter", "دخنر", R.drawable.daugther));
        words.add(new Word("Uncle", "کاکا، ماما", R.drawable.uncle));
        words.add(new Word("Aunt", "عمه، خاله", R.drawable.aunt));
        words.add(new Word("Nephew", "خواهر زاده، برادر زاده", R.drawable.nephew));
        words.add(new Word("Wife", "خانم", R.drawable.wh));
        words.add(new Word("Husband", "شوهر", R.drawable.wh));
        words.add(new Word("Father in law", "خسر", R.drawable.fatherinlaw));
        words.add(new Word("Mother in law", "خشو", R.drawable.motherinlaw));
        words.add(new Word("Brother in law", "خسر بره، داماد", R.drawable.brother));
        words.add(new Word("Sister in law", "خشلوچه", R.drawable.sisreinlaw));

        List<Word> data = words;

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Recycler_Adapter adapter = new Recycler_Adapter(data, getApplication());
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
