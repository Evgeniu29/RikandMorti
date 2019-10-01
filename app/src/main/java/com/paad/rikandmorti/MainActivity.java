package com.paad.rikandmorti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paad.rikandmorti.API.Feed;
import com.paad.rikandmorti.API.Result;
import com.paad.rikandmorti.Adapter.CharacterAdapter;
import com.paad.rikandmorti.Retrofit.RetrofitClient;
import com.paad.rikandmorti.Retrofit.RetrofitService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String baseUrl = "https://rickandmortyapi.com/";

    RecyclerView rvCharacters;

    Button imageB;

    Singltone singltone;

    CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageB = (Button) findViewById(R.id.button);

        rvCharacters = findViewById(R.id.rv);

        final LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvCharacters.setLayoutManager(llm);

        adapter = new CharacterAdapter(this, singltone.getInstance().characterList);
        rvCharacters.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(rvCharacters.getContext(), llm.getOrientation());
        rvCharacters.addItemDecoration(itemDecoration);

        RetrofitService retrofitService = RetrofitClient.getClient(baseUrl).create(RetrofitService.class);

        Call<Feed> listCall = retrofitService.getData();

        listCall.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    Feed feed = response.body();
                    singltone.getInstance().characterList.removeAll(singltone.getInstance().characterList);
                    singltone.getInstance().characterList.addAll(feed.getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        imageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<Result> result = Singltone.getInstance().characterList;

                Collections.sort(result, new Comparator<Result>() {
                    public int compare(Result obj1, Result obj2) {
                        return obj1.getName().compareTo(obj2.getName());
                    }


                });

                adapter = new CharacterAdapter(getApplicationContext(), result);
                rvCharacters.setAdapter(null);
                rvCharacters.setLayoutManager(null);
                rvCharacters.setAdapter(adapter);
                rvCharacters.setLayoutManager(llm);
                adapter.notifyDataSetChanged();

            }
        });


    }
}