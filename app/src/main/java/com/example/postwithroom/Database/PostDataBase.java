package com.example.postwithroom.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.postwithroom.Converters;
import com.example.postwithroom.Pojo.Posts;

@Database(entities = Posts.class,version = 1)
@TypeConverters(Converters.class)
public abstract class PostDataBase extends RoomDatabase {
    private static PostDataBase instance ;
    public abstract PostDao postDao ();
    public synchronized static  PostDataBase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostDataBase.class,"Post_DataBase")
                        .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
