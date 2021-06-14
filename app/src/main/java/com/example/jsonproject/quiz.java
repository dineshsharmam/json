package com.example.jsonproject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class quiz {
    @SerializedName("questions")
    ArrayList<Questions> mQuestions=new ArrayList<Questions>();

    public quiz(ArrayList<Questions> questions){
        mQuestions=questions;
    }

}
