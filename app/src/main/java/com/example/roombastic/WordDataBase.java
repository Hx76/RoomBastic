package com.example.roombastic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {
    private static WordDataBase INSTANCE;
    static synchronized WordDataBase getDatabase(Context context){//多个线程同时申请不会冲突，排队
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDataBase.class,"word_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
}
