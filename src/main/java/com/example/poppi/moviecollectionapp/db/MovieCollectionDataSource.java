//A wrapper class as an abstraction layer for the database and cursor
//open() to connect to the database
//close() to close the database
//createMovie to persist records to the databse
//getAllmoview to retrieve all of the records in the database
package com.example.poppi.moviecollectionapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;

import com.example.poppi.moviecollectionapp.Models.Director;

public class MovieCollectionDataSource {

    //creates a unique TAG for the class
    private static final String TAG = MovieCollectionDataSource.class.getSimpleName();

    private final DirectorDao directorDao;
    private final MovieDao movieDao;

    // private SQLiteDatabase database;
    // sqliteOpenHelper class we would create if not using room
    //it would extend SQLiteOpenHelper class
    //  private  DatabaseSQLiteOpenHelper dbHelper;


    public MovieCollectionDataSource(Context context){

        //get an instance of the database
        MovieCollectionDatabase database = MovieCollectionDatabase.getInstance(context);
        //get access to DAOs the methods were implemented in the MovieCollectionDatabase class
        directorDao = database.directorDao();
        movieDao = database.movieDao();
// this.dbHelper = new DatabaseSQLiteOpenHelper(context);//if not used room

    }

    //inserts a new record
    //before had to use the contentValues
    //now after add movies to database in the mainActiviy
    public void createMovie(com.example.poppi.moviecollectionapp.Models.Movie movie){
        Director d = movie.getmDirector();
           if(movie.getCollection().equalsIgnoreCase("no")) {
              long  rowId = movieDao.createMovie(movie);

               int director = movie.getDirector();
               if (d != null) {
                   movie.setDirector(director);
               }
               //if the director is not in the db create it
               directorDao.createDirector(d);
               Log.d(TAG,"movie created the id: " + rowId);
           }

    }
       /** public void open(){

           this.database = dbHelper.getWritableDatabase();
           Log.d(TAG,"Database is open");
       }
       //to close the database if not using room
       public void close(){
        dbHelper.close();
       }*/


}
