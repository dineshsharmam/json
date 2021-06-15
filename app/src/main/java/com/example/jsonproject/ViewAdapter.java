package com.example.jsonproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ItemViewHolder> {

    private Context appContext;
    quiz myquiz;


    public ViewAdapter(Context context) {
        appContext = context;
        initJson(appContext);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(appContext).inflate(R.layout.recycler_view, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ItemViewHolder holder, int position) {
        holder.mQuestionTv.setText(myquiz.mQuestions.get(position).mQuestion);
        holder.mOption1.setText(myquiz.mQuestions.get(position).mOptions.get(0));
        holder.mOption2.setText(myquiz.mQuestions.get(position).mOptions.get(1));
        holder.mOption3.setText(myquiz.mQuestions.get(position).mOptions.get(2));
        holder.mOption4.setText(myquiz.mQuestions.get(position).mOptions.get(3));
        holder.mAnswerTv.setText("Answer: " + myquiz.mQuestions.get(position).mAnswer);
    }


    @Override
    public int getItemCount() {
        return myquiz.mQuestions.size();
    }

    private void initJson(Context context) {
        Gson gson = new Gson();

        String jsonString = null;
        try {
            InputStream inputStream = context.getAssets().open("questions.json");
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

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mQuestionTv;
        TextView mOption1;
        TextView mOption2;
        TextView mOption3;
        TextView mOption4;
        TextView mAnswerTv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuestionTv = itemView.findViewById(R.id.question_tv);
            mOption1 = itemView.findViewById(R.id.option1_tv);
            mOption2 = itemView.findViewById(R.id.option2_tv);
            mOption3 = itemView.findViewById(R.id.option3_tv);
            mOption4 = itemView.findViewById(R.id.option4_tv);
            mAnswerTv = itemView.findViewById(R.id.answer_tv);

        }
    }
}
