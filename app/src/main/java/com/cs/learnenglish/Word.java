package com.cs.learnenglish;

/**
 * Created by Qais Rasuli on 9/15/2019.
 */

public class Word {

    private String mEnglishTranslation;
    private String mFarsiTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceId;


    public Word(String English, String Farsi, int ImageResourceId) {
        mEnglishTranslation = English;
        mFarsiTranslation = Farsi;
        mImageResourceId = ImageResourceId;
    }
    public Word(){

    }

    public Word(String English, String Farsi) {
        mEnglishTranslation = English;
        mFarsiTranslation = Farsi;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getmEnglishTranslation() {
        return mEnglishTranslation;
    }

    public String getmFarsiTranslation() {
        return mFarsiTranslation;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}
