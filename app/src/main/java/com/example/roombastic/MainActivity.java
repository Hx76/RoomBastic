package com.example.roombastic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordDataBase wordDataBase;
    WordDao dao;
    Button insert, update, clear, delete;
    WordViewModel wordViewModel;
    MyAdapter adapter1,adapter2;
    RecyclerView recyclerView;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        recyclerView = findViewById(R.id.recyclerView);
        adapter1 = new MyAdapter(false);
        adapter2 = new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    recyclerView.setAdapter(adapter2);
                }else {
                    recyclerView.setAdapter(adapter1);
                }
            }
        });

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
//        wordDataBase = Room.databaseBuilder(this, WordDataBase.class, "word_database").build();
//        dao = wordDataBase.getWordDao();
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter1.setAllWords(words);
                adapter2.setAllWords(words);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
//                StringBuilder text = new StringBuilder();
//                for (int i = 0; i < words.size(); i++) {
//                    Word word = words.get(i);
//                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
//                }
//                tv.setText(text);
            }
        });

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("Hello", "你好");
                Word word2 = new Word("world", "世界");
                wordViewModel.insertWords(word1, word2);

            }
        });
        clear = findViewById(R.id.clear);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.clearWords();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("HI", "你好");
                word.setId(5);
                wordViewModel.updateWords(word);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("11", "22");
                word.setId(6);
                wordViewModel.deleteWords(word);

            }
        });

    }

}
