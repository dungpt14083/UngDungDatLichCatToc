package com.example.ungdungdatlichcattoc.Data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ungdungdatlichcattoc.Model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

}

