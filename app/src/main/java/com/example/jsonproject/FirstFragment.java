package com.example.jsonproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private TextView mQuestionTv;
    private TextView mOption1;
    private TextView mOption2;
    private TextView mOption3;
    private TextView mOption4;
    private TextView mAnswerTv;
    private Button mShowBtn;
    private Button mNextBtn;

    quiz myquiz;
    private int pos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initJson();
        pos = 0;
        setUi();

    }

    private void setUi() {
        if(pos == myquiz.mQuestions.size())
            pos=0;
        mQuestionTv.setText(myquiz.mQuestions.get(pos).mQuestion);
        mOption1.setText(myquiz.mQuestions.get(pos).mOptions.get(0));
        mOption2.setText(myquiz.mQuestions.get(pos).mOptions.get(1));
        mOption3.setText(myquiz.mQuestions.get(pos).mOptions.get(2));
        mOption4.setText(myquiz.mQuestions.get(pos).mOptions.get(3));
        mAnswerTv.setText(myquiz.mQuestions.get(pos).mAnswer);
        mAnswerTv.setVisibility(View.INVISIBLE);
    }

    private void initJson() {
        Gson gson = new Gson();

        String jsonString = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onCreate: " + e);
        }

        myquiz = gson.fromJson(jsonString, quiz.class);

    }

    private void initUi() {
        mQuestionTv = getView().findViewById(R.id.question_tv);
        mOption1 = getView().findViewById(R.id.option1_tv);
        mOption2 = getView().findViewById(R.id.option2_tv);
        mOption3 = getView().findViewById(R.id.option3_tv);
        mOption4 = getView().findViewById(R.id.option4_tv);
        mAnswerTv = getView().findViewById(R.id.answer_tv);
        mShowBtn = getView().findViewById(R.id.show_btn);
        mNextBtn = getView().findViewById(R.id.next_btn);

        mShowBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_btn:
                mAnswerTv.setVisibility(View.VISIBLE);
                break;
            case R.id.next_btn:
                handleNextBtn();
        }
    }

    private void handleNextBtn() {
        pos++;
        setUi();
        mAnswerTv.setVisibility(View.INVISIBLE);

    }
}
