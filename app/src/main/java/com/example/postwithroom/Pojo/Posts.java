package com.example.postwithroom.Pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Post_table")
public class Posts {
    private String title;
    private String body;
    @PrimaryKey(autoGenerate = true)
    private int id;
   public   User user;


    public Posts(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }
}
