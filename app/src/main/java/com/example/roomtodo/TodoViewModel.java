package com.example.roomtodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    TodoRoomDatabase todoRoomDatabase;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoRoomDatabase = TodoRoomDatabase.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Todo>> getAllTodos(){
        return todoRoomDatabase.todoDao().findAllTodos();
    }

}
