package com.example.android.mmiwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager maudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //RESUME PLAYBACK
                mMediaPlayer.start();
            }else if(focusChange ==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.word_list,container,false);

        maudioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //    String words[] ={"one","two","three","four","five","six","seven","eight","nine","ten"};
        //    int i;
        //    for(i=0;i<10;i++)
        //    Log.v("NumbersActivity","Word at index "+i+" " +words[i]);

        final ArrayList<word> words = new ArrayList<word>();

        //  words.add("one");
        words.add(new word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new word("brown", "tokakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new word("dusty yellow", "topiise", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new word("mustard yellow", "chiwiite", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        // words.add(new word("nine","wo'e"));
        //words.add(new word("ten","na'aacha"));


        wordAdapter itemsAdapter = new wordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                word word = words.get(position);
                //  Log.v("NumbersActivity", "Current word:" + word);

                int result = maudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //maudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());

                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mcompletionListener);
                }
            }
        });
        return rootView;
    }



    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            maudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }

}
