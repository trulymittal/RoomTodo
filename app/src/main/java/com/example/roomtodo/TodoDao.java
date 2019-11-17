package com.example.roomtodo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insertTodo(Todo todo);

    @Query("SELECT * FROM todo_table")
    List<Todo> getAllTodos();

    @Query("SELECT * FROM todo_table WHERE todo_uid LIKE :uid")
    Todo findTodoById(int uid);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Insert
    void insertMultipleTodos(List<Todo> todoList);

    @Query("SELECT * FROM todo_table WHERE todo_completed LIKE 1")
    List<Todo> getAllCompletedTodos();


    @Query("SELECT * FROM todo_table")
    LiveData<List<Todo>> findAllTodos();

}
