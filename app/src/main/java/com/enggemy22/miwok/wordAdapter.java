package com.enggemy22.miwok;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<word> {
private int mcolor;
public wordAdapter(Activity context , ArrayList<word> words)
{
    super(context ,0 , words);
}
public wordAdapter(Activity context, ArrayList<word>words,int colorRecourse)
    {
        super(context, 0 , words);
        mcolor= colorRecourse;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View listView= convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        word currentWord=getItem(position);

        TextView numberText1= (TextView) listView.findViewById(R.id.miwok_text_view);
        numberText1.setText(currentWord.getMaiwokNumber());


        TextView numberText2= (TextView) listView.findViewById(R.id.default_text_View);
        numberText2.setText(currentWord.getEnglishNumber());


        ImageView imageView =(ImageView) listView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());

            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);


        ImageView mm=(ImageView) listView.findViewById(R.id.play);
        mm.setImageResource(currentWord.getimage_audio());


        View text_container = listView.findViewById(R.id.text_container);
        int colr = ContextCompat.getColor(getContext(),mcolor);
        text_container.setBackgroundColor(colr);


return listView;
    }
}
