package com.example.mycalendar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.CalendarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HourAdapter hourAdapter; // Адаптер для RecyclerView
    private List<TodoItem> tasks; // Список задач

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Загрузка задач из JSON
        tasks = loadTasksFromJson();

        // Заполнение RecyclerView по умолчанию
        updateTaskList();

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            updateTaskList(year, month, dayOfMonth);
        });
    }

    private List<TodoItem> loadTasksFromJson() {
        Gson gson = new Gson();
        List<TodoItem> tasks = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(getAssets().open("todo_list.json"))) {
            Type listType = new TypeToken<List<TodoItem>>() {}.getType();
            tasks = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private void updateTaskList(int year, int month, int day) {
        String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, day);

        // Фильтрация задач по выбранной дате
        List<TodoItem> filteredTasks = new ArrayList<>();
        Log.d("Selected Date", "Selected date: " + selectedDate);
        try {
            for (TodoItem task : tasks) {
                if (task.getDate().equals(selectedDate)) {
                    filteredTasks.add(task);
                }
            }
        } catch (Exception e) {
            Log.e("Error", "Error filtering tasks", e);
        }

        Log.d("Filtered Tasks", "Filtered tasks: " + filteredTasks.size());

        if (hourAdapter == null) {
            hourAdapter = new HourAdapter(filteredTasks, this);
            recyclerView.setAdapter(hourAdapter);
        } else {
            hourAdapter.updateTasks(filteredTasks); // Предполагается, что вы добавите метод для обновления задач в адаптере
        }
    }

    private void updateTaskList() {
        Calendar today = Calendar.getInstance();
        updateTaskList(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
    }
}