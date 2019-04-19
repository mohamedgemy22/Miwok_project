package com.enggemy22.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {
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
        setContentView(R.layout.activity_family_members);

        final ArrayList <word> words = new ArrayList<word>();
        words.add(new word("father","әp",R.drawable.family_father,R.raw.family_father ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("son","angsi",R.drawable.family_son,R.raw.family_son ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("yuonger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("grand mather","ama",R.drawable.family_grandmother,R.raw.family_grandmother ,R.drawable.baseline_play_arrow_black_18dp));
        words.add(new word("drand father","paapa",R.drawable.family_grandfather,R.raw.family_grandfather ,R.drawable.baseline_play_arrow_black_18dp));

        wordAdapter adddpter = new wordAdapter(this,words,R.color.family);
        ListView lis = (ListView) findViewById(R.id.family_list);
        lis.setAdapter(adddpter);



        lis.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                word word = words.get(position);

                    media = MediaPlayer.create(FamilyMembers.this, word.getMediaResourceId());
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
   private void releaseMediaPlayer(){
        if(media !=null)
        {
            media.release();
            media=null;
            //mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
