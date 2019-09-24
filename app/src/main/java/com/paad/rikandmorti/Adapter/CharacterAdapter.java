package com.paad.rikandmorti.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paad.rikandmorti.API.Result;
import com.paad.rikandmorti.DetailActivity;
import com.paad.rikandmorti.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    List<Result> list;
    private Context context;
    private int index;


    public CharacterAdapter(Context context, List<Result> list) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new CharacterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder characterViewHolder, int i) {
        characterViewHolder.bind(list.get(i));
        index = i;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;



        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
        }

        public void bind(final Result character) {
            String name = character.getName();
            tvName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", character);
                   startActivity(context, intent, null);
                }
            });

        }

    }


}
