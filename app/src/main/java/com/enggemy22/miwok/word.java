package com.enggemy22.miwok;

import android.media.MediaPlayer;

public class word  {
    private String englishNumber;
    private String maiwokNumber;
    private int imagep;
    private int MediaPlayer;
    private int image_audio;
    private static final  int NO_IMAGE_PROVIDED = -1;


    public word(String word1, String word2)
    {
        englishNumber = word1;
        maiwokNumber = word2;
    }

    public word(String word1, String word2,int audio)
    {
        englishNumber = word1;
        maiwokNumber = word2;
        MediaPlayer=audio;

    }

    public word(String word1, String word2,int image,int player, int image_audio_play)
    {
        englishNumber = word1;
        maiwokNumber = word2;
        imagep = image;
        MediaPlayer=player;
        image_audio = image_audio_play;
    }
    public word(String word1, String word2 ,int image, int player)
    {
        englishNumber = word1;
        maiwokNumber = word2;
        imagep = image;
        MediaPlayer=player;
      //  image_audio = image_audio_play;
    }




    public String getEnglishNumber()
    {
        return  englishNumber;
    }
    public  String getMaiwokNumber()
    {
        return  maiwokNumber;
    }
    public  int getImageResourceId()
    {
        return  imagep;
    }
    public boolean hasImage() {
        return imagep != NO_IMAGE_PROVIDED;
    }
    public  int getMediaResourceId(){return MediaPlayer;}
    public int getimage_audio(){return image_audio;}

}
