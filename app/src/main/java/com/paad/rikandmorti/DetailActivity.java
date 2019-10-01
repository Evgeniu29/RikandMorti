package com.paad.rikandmorti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paad.rikandmorti.API.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends Activity implements Serializable {

    TextView  id;

    TextView status;

    TextView species;

    TextView type;

    TextView gender;

    TextView locationName;

    TextView originName;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail);

        RecyclerView rvCharacters;

        id = (TextView) findViewById(R.id.id);
        status = (TextView) findViewById(R.id.status);
        species = (TextView) findViewById(R.id.species);
        type = (TextView) findViewById(R.id.type);
        gender = (TextView) findViewById(R.id.gender);
        locationName = (TextView) findViewById(R.id.locationName);
        originName = (TextView) findViewById(R.id.originName);

        image = (ImageView) findViewById(R.id.ivImage);

        Intent intent = getIntent();

        Result extra = intent.getParcelableExtra("position");


        id.setText(extra.getId().toString());

        status.setText(extra.getStatus());

        species.setText(extra.getSpecies());

        type.setText(extra.getType());

        gender.setText(extra.getGender());

        locationName.setText(Singltone.getInstance().characterList.get(extra.getId()).getLocation().getName());

        originName.setText(Singltone.getInstance().characterList.get(extra.getId()).getOrigin().getName());

        Glide.with(this).load(extra.getImage())
                .into(image);


    }
}
