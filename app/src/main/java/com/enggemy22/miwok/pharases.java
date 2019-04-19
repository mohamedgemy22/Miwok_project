package com.enggemy22.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class pharases extends AppCompatActivity {
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
        setContentView(R.layout.activity_pharases);


        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("My name is..", "oyaaset..", R.raw.phrase_my_name_is ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("I’m feeling good", "kuchi achit", R.raw.phrase_im_feeling_good ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("Yes, I’m coming", "hәә’ әәnәm", R.raw.phrase_yes_im_coming ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("I’m coming", "әәnәm", R.raw.phrase_im_coming ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("Let’s go", "yoowutis", R.raw.phrase_lets_go ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("Come here", "әnni'nem", R.raw.phrase_come_here ,R.drawable.baseline_play_arrow_black_18dp));

        wordAdapter addddd = new wordAdapter(this, words, R.color.pharse);
        ListView list = (ListView) findViewById(R.id.mohamed);
        list.setAdapter(addddd);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                word word = words.get(position);

                    media = MediaPlayer.create(pharases.this, word.getMediaResourceId());
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
   private  void releaseMediaPlayer(){
        if(media !=null)
        {
            media.release();
            media=null;
           // mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

