package com.example.roombastic;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    public MyAdapter(boolean isCardView) {
        this.isCardView = isCardView;
    }

    private boolean isCardView = false;
    List<Word> allWords = new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (isCardView){
            itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        }else {
            itemView = layoutInflater.inflate(R.layout.cell_normal,parent,false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Word word = allWords.get(position);
        holder.id.setText(String.valueOf(position+1));
        holder.english.setText(word.getWord());
        holder.chinese.setText(word.getChineseMeaning());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://dict.youdao.com/w/"+holder.english.getText()+"/#keyfrom=dict2.top");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,english,chinese;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            english = itemView.findViewById(R.id.english);
            chinese = itemView.findViewById(R.id.chinese);
        }
    }
}
