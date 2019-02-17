package com.example.poppi.moviecollectionapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.poppi.moviecollectionapp.Models.Director;
import com.example.poppi.moviecollectionapp.Models.Movie;

@Database(entities = {Movie.class,Director.class}, version = 1)
public abstract class MovieCollectionDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Movie_Collection";
    private static MovieCollectionDatabase INSTANCE = null;

    //creates a singleton of the MovieCollectionDatabase
    public static MovieCollectionDatabase getInstance(Context context){
        if(INSTANCE == null){//create the database
            synchronized (MovieCollectionDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieCollectionDatabase.class,
                        DATABASE_NAME)
                       // .addMigrations(MIGRATION_1_2)
                        .build();
            }
        }
        return INSTANCE;
    }

    //abstract methods to expose the Daos for latter use
    //After rebuilding project, will see these generated methods in the generated
    //MovieCollectionDatabase_impl class - now can insert records
    //returns a MovieDao
    public abstract MovieDao movieDao();
    //returns a DirectorDao
    public abstract DirectorDao directorDao();

}
