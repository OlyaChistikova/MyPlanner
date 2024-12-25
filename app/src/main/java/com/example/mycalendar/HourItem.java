package com.example.mycalendar;

import java.util.ArrayList;
import java.util.List;

// HourItem.java
public class HourItem {
    private String hour;
    private List<TodoItem> tasks;

    public HourItem(String hour) {
        this.hour = hour;
        this.tasks = new ArrayList<>();
    }

    public String getHour() {
        return hour;
    }

    public List<TodoItem> getTasks() {
        return tasks;
    }

    public void addTask(TodoItem task) {
        tasks.add(task);
    }
}