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
import android.widget.Toast;

import com.cs.learnenglish.Adapters.CustomRVItemTouchListener;
import com.cs.learnenglish.Adapters.Recycler_Adapter_NoImage;
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


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Lion", "شیر"));
        words.add(new Word("Dog", "سگ"));
        words.add(new Word("Cat", "گربه"));
        words.add(new Word("Cow", "گاو"));
        words.add(new Word("Elephant", "فیل"));
        words.add(new Word("Zebra", "گوره خر"));
        words.add(new Word("Gorilla", "گوریل"));
        words.add(new Word("Monkey", "میمون"));
        words.add(new Word("Giraffe", "زرافه"));
        words.add(new Word("Horse", "اسپ"));
        words.add(new Word("Donkey", "خر"));

        List<Word> data = words;

        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        Recycler_Adapter_NoImage adapter = new Recycler_Adapter_NoImage(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                try{
                    textToSpeech.speak(words.get(position).getmEnglishTranslation(), TextToSpeech.QUEUE_FLUSH, null);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(AnimalsActivity.this, "No sound", Toast.LENGTH_SHORT).show();
                }
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
