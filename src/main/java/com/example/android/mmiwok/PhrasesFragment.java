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

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
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
    private AudioManager maudioManager;
    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView=inflater.inflate(R.layout.word_list,container,false);

        maudioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //    String words[] ={"one","two","three","four","five","six","seven","eight","nine","ten"};
        //    int i;
        //    for(i=0;i<10;i++)
        //    Log.v("NumbersActivity","Word at index "+i+" " +words[i]);

        final ArrayList<word> words = new ArrayList<word>();


        //  words.add("one");
        words.add(new word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?","tinne oyaase'ne",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?","michekses?",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I'm feeling good","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?","eenes'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes,I'm coming","hee'eenem",R.raw.phrase_yes_im_coming));
        words.add(new word("I'm coming","eenem",R.raw.phrase_im_coming));
        words.add(new word("Let's go","yoowutis",R.raw.phrase_lets_go));
        words.add(new word("Come here","paapa",R.raw.phrase_come_here));


        wordAdapter itemsAdapter = new wordAdapter(getActivity(),words,R.color.category_phrases);
        ListView listView=(ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                word word=words.get(position);

                int result = maudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
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
        }
    }

}
