package com.example.roombastic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    WordRepository repository;

    public LiveData<List<Word>> getAllWordsLive() {
        return repository.getAllWordsLive();
    }

    private LiveData<List<Word>> allWordsLive;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
    }

    void insertWords(Word... words) {
        repository.insertWords(words);
    }

    void deleteWords(Word... words) {
        repository.deleteWords(words);
    }

    void updateWords(Word... words) {
        repository.updateWords(words);
    }

    void clearWords(Void... voids) {
        repository.clearWords();
    }


}
