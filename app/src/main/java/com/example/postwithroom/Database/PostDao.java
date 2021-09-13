package com.example.postwithroom.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.postwithroom.Pojo.Posts;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface PostDao {

        @Insert
        Completable insertPost(Posts posts);
        @Query("SELECT * FROM post_table")
        Single<List<Posts>> getPosts();



}
