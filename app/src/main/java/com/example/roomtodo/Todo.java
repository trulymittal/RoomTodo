package com.example.roomtodo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_uid")
    private int uid;

    @ColumnInfo(name = "todo_text")
    private String text;

    @ColumnInfo(name = "todo_completed")
    private boolean completed;

    public Todo(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "\nTodo{" +
                "uid=" + uid +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                '}';
    }
}
