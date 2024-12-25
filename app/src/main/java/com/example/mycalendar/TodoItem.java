package com.example.mycalendar;

public class TodoItem {
    private int id;
    private String date;
    private String hour_start;
    private String hour_finish;
    private String name;
    private String description;

    // Конструктор
    public TodoItem(int id, String date, String hour_start, String hour_finish, String name, String description) {
        this.id = id;
        this.date = date;
        this.hour_start = hour_start;
        this.hour_finish = hour_finish;
        this.name = name;
        this.description = description;
    }

    // Геттеры
    public int getId() { return id; }
    public String getDate() { return date; }
    public String getHourStart() { return hour_start; }
    public String getHourFinish() { return hour_finish; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Вы можете добавить сеттеры при необходимости
}