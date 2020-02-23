package com.example.roombastic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getDatabase(context);
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWords();
    }

    void insertWords(Word... words) {
        new WordRepository.InsertAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new WordRepository.DeleteAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words) {
        new WordRepository.UpdateAsyncTask(wordDao).execute(words);
    }

    void clearWords(Void... voids) {
        new WordRepository.ClearAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        private WordDao wordDao;

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        private WordDao wordDao;

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        private WordDao wordDao;

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        private WordDao wordDao;

        @Override
        protected Void doInBackground(Void... Voids) {
            wordDao.deleteWords();
            return null;
        }
    }
}
