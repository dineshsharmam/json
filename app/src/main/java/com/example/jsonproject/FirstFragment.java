package com.example.jsonproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FirstFragment extends Fragment {

    RecyclerView recyclerView;
    ViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        initJson();


    }

    private void initJson() {
        Call<quiz> call = RetrofitClient.getInstance().getMyApi().getquiz();
        call.enqueue(new Callback<quiz>() {
            @Override
            public void onResponse(Call<quiz> call, Response<quiz> response) {
                quiz myquiz = response.body();
                setRecyclerView(myquiz);

            }

            @Override
            public void onFailure(Call<quiz> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.d("print", t.getMessage());
            }


        });
    }

    private void setRecyclerView(quiz myquiz) {
        adapter = new ViewAdapter(getActivity().getApplicationContext(), myquiz);
        recyclerView.setAdapter(adapter);
    }
}
