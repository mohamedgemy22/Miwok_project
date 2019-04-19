package com.enggemy22.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Color extends AppCompatActivity {
private MediaPlayer media;
    private AudioManager mAudioManager ;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        media.pause();
                        media.seekTo(0);
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        media.start();
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                    }
                }
            };
private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        final ArrayList <word> words= new ArrayList<word>();

        words.add(new word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("green","chokokki",R.drawable.color_green,R.raw.color_green,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("black","kululli",R.drawable.color_black,R.raw.color_black,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("white","kelelli",R.drawable.color_white,R.raw.color_white,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow,R.drawable.baseline_play_arrow_black_18dp));


        wordAdapter addapter = new wordAdapter(this,words, R.color.color);

        ListView list = (ListView) findViewById(R.id.color_list);

        list.setAdapter(addapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                word word = words.get(position);

                    media = MediaPlayer.create(Color.this, word.getMediaResourceId());
                    media.start();
                    media.setOnCompletionListener(mCompletionListener);
            }
    });
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
        private void releaseMediaPlayer()
        {
            if(media !=null)
            {
                media.release();
                media=null;
               // mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
}
