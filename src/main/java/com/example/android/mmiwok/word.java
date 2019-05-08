package com.example.android.mmiwok;

/**
 * Created by rex on 20-Jul-18.
 */

public class word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;
    private int mAudioResourceId;


    public word(String DefaultTranslation, String MiwokTranslation,int AudioResourceId) {
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceId=AudioResourceId;
    }

    public word(String DefaultTranslation,String MiwokTranslation,int ImageResourceid,int AudioResourceId){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceId=ImageResourceid;
        mAudioResourceId=AudioResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId(){
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}