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

public class ColorsActivity extends AppCompatActivity {

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
        words.add(new Word("Black", "سیاه", R.drawable.black));
        words.add(new Word("White", "سفید", R.drawable.white));
        words.add(new Word("Red", "سرخ", R.drawable.red));
        words.add(new Word("Blue", "آبی", R.drawable.blue));
        words.add(new Word("Yellow", "زرد", R.drawable.yellow));
        words.add(new Word("Brown", "قهوه ای", R.drawable.brown));
        words.add(new Word("Green", "سبز", R.drawable.green));
        words.add(new Word("Grey", "خاکستری", R.drawable.grey));
        words.add(new Word("Pink", "گلابی", R.drawable.pink));
        words.add(new Word("Purple", "بنفش", R.drawable.purple));
        words.add(new Word("Orange", "نارنجی", R.drawable.orange));

        List<Word> data = words;

        Word word = new Word();
        final ArrayList<String> textstospeech = new ArrayList<>();
        textstospeech.add("Black");
        textstospeech.add("White");
        textstospeech.add("Red");
        textstospeech.add("Blue");
        textstospeech.add("Yellow");
        textstospeech.add("Brown");
        textstospeech.add("Green");
        textstospeech.add("Grey");
        textstospeech.add("Pink");
        textstospeech.add("Purple");
        textstospeech.add("Orange");


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
