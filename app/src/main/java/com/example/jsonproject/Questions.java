package com.example.jsonproject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Questions {
    @SerializedName("question")
    String mQuestion;
    @SerializedName("options")
    ArrayList<String> mOptions = new ArrayList<String>();
    @SerializedName("answer")
    String mAnswer;

    public Questions(String question, ArrayList<String> options, String answer) {
        mQuestion = question;
        mOptions = options;
        mAnswer = answer;
    }
}
