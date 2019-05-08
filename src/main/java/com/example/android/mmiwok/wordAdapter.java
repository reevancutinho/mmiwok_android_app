package com.example.android.mmiwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.mmiwok.R.raw.number_one;

/**
 * Created by rex on 20-Jul-18.
 */

public class wordAdapter extends ArrayAdapter<word>{

   private int mColorResourceid;

    public wordAdapter(Activity context, ArrayList<word>arrpter,int ColorResourceid){
        super(context,0,arrpter);
        mColorResourceid=ColorResourceid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
       //return super.getView(position, convertView, parent);

        word currentwordAdapter=getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentwordAdapter.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentwordAdapter.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

        if(currentwordAdapter.hasImage()) {
            imageView.setImageResource(currentwordAdapter.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceid);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }
}
