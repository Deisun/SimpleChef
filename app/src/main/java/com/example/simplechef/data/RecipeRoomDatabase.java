package com.example.simplechef.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Recipe.class, version=1)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
    private static volatile RecipeRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static RecipeRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), RecipeRoomDatabase.class, "recipe_database").build();
                }
            }
        }
        return instance;
    }


/*
    public static synchronized RecipeRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeRoomDatabase.class, "recipe_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecipeDao recipeDao;

        private PopulateDbAsyncTask(RecipeRoomDatabase db) {
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recipeDao.insert(new Recipe("Recipe 1", "This is the first recipe"));
            return null;
        }
    }
*/
}
