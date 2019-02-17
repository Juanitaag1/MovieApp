//This abstract class is a class that will extends RoomDatabase
//is required in order to generate the SQLite database and associated tables(Entities)
//Room will generate a concrete implemetation of the database
//Will create a singleton of the MovieCollectionDatabase
//Has abstract methods to expose the Daos
// Abstact methods returns a MovieDao and a DirectorDao
package com.example.poppi.moviecollectionapp.db;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.poppi.moviecollectionapp.Models.Director;
import com.example.poppi.moviecollectionapp.Models.Movie;

@Database(entities = {Movie.class,Director.class}, version = 1)//the entities to be added to the db
public abstract class MovieCollectionDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Movie_Collection";
    private static MovieCollectionDatabase INSTANCE = null;

    //Room will generate a concrete implemetation of the database
    //Since this is an abstract class this instance will be generated from room
    //creates a singleton of the MovieCollectionDatabase
    //returns an instance of MoviewCollectionDatabase
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
