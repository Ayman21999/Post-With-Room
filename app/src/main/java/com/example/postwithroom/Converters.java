package com.example.postwithroom;

import androidx.room.TypeConverter;

import com.example.postwithroom.Pojo.User;
import com.google.gson.Gson;

public class Converters {
    @TypeConverter
    public String fromUserToString(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public User fromGsonToUser(String user) {
        return new Gson().fromJson(user,User.class);

    }
}
