package com.example.roomtodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        todoViewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todoList) {
                Log.d(TAG, "onChanged: " + todoList.toString());
                Log.d(TAG, "onChanged: " + todoList.size());
            }
        });
    }

    public void insertSingleTodo(View view) {
        Todo todo = new Todo("make a video on swift ...", false);
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.execute(todo);
    }

    public void getAllTodos(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .getAllTodos();

                Log.d(TAG, "run: " + todoList.toString());
            }
        });
        thread.start();
    }

    public void deleteATodo(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .findTodoById(4);

                Log.d(TAG, "run: " + todo.toString());
                TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .deleteTodo(todo);

                Log.d(TAG, "run: todo has been deleted...");

            }
        }).start();
    }

    public void updateATodo(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .findTodoById(1);

                if (todo != null) {
                    todo.setCompleted(true);

                    TodoRoomDatabase.getInstance(getApplicationContext())
                            .todoDao()
                            .updateTodo(todo);

                    Log.d(TAG, "run: todo has been updated..." );
                }
            }
        }).start();

    }

    public void insertMultipleTodos(View view) {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = new ArrayList<>();
                todoList.add(new Todo("make a video on kotlin", false));
                todoList.add(new Todo("watch black panther", true));
                todoList.add(new Todo("watch marvel movies seris", true));
                todoList.add(new Todo("make a beginner video on pyhton", false));
                
                TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .insertMultipleTodos(todoList);

                Log.d(TAG, "run: todos has been inserted...");
            }
        }).start();
        
    }

    public void findCompletedTodos(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = TodoRoomDatabase.getInstance(getApplicationContext())
                        .todoDao()
                        .getAllCompletedTodos();

                Log.d(TAG, "run: " + todoList.toString());
            }
        }).start();

    }

    class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected Void doInBackground(Todo... todos) {

            TodoRoomDatabase.getInstance(getApplicationContext())
                    .todoDao()
                    .insertTodo(todos[0]);

            return null;
        }
    }
}

