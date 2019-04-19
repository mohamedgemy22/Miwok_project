package com.enggemy22.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
private MediaPlayer  mMediaPlayer;
private AudioManager mAudioManager;

// audio foucos
private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
            // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
            // our app is allowed to continue playing sound but at a lower volume. We'll treat
            // both cases the same way because our app is playing short sound files.

            // Pause playback and reset player to the start of the file. That way, we can
            // play the word from the beginning when we resume playback.
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
            mMediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            // The AUDIOFOCUS_LOSS case means we've lost audio focus and
            // Stop playback and clean up resources
            releaseMediaPlayer();
        }
    }
};

 /*
 this function i call realese function here..!
 by using on completionListner function...!
 */
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
            setContentView(R.layout.activity_numbers);
            // Create and setup the {@link AudioManager} to request audio focus
          //  mAudioManager = (AudioManager) getSystemService(javax.naming.Context.AUDIO_SERVICE);

            final ArrayList<word> words = new ArrayList<word>();
            words.add(new word("one", "lutti", R.drawable.number_one, R.raw.number_one,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("two", "otiiko", R.drawable.number_two, R.raw.number_two,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("three", "tolookosu", R.drawable.number_three, R.raw.number_three,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("four", "oyyisa", R.drawable.number_four, R.raw.number_four,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("five", "massokka", R.drawable.number_five, R.raw.number_five,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("six", "temmokka", R.drawable.number_six, R.raw.number_six,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine,R.drawable.baseline_play_arrow_black_18dp));
            words.add(new word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten,R.drawable.baseline_play_arrow_black_18dp));


            wordAdapter adapter = new wordAdapter(this, words, R.color.number);

            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    releaseMediaPlayer();


                    word word = words.get(position);

                        // We have audio focus now.
                        // Create and setup the {@link MediaPlayer} for the audio resource associated
                        // with the current word
                        mMediaPlayer = MediaPlayer.create(Numbers.this, word.getMediaResourceId());

                        // Start the audio file
                        mMediaPlayer.start();

                        // Setup a listener on the media player, so that we can stop and release the
                        // media player once the sound has finished playing.
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            });
        }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
        private void releaseMediaPlayer() {
            if ( mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
               // mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
}

