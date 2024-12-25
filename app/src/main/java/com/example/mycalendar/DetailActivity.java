package com.example.mycalendar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Получение данных из Intent
        String taskName = getIntent().getStringExtra("taskName");
        String taskDate = getIntent().getStringExtra("taskDate");
        String taskStartTime = getIntent().getStringExtra("taskStartTime");
        String taskFinishTime = getIntent().getStringExtra("taskFinishTime");
        String taskDescription = getIntent().getStringExtra("taskDescription");

        // Отображение данных
        TextView taskNameView = findViewById(R.id.taskName);
        TextView taskDateView = findViewById(R.id.taskDate);
        TextView taskTimeView = findViewById(R.id.taskTime);
        TextView taskDescriptionView = findViewById(R.id.taskDescription);

        taskNameView.setText(taskName);
        taskDateView.setText(taskDate);
        taskTimeView.setText(taskStartTime + " - " + taskFinishTime);
        taskDescriptionView.setText(taskDescription);
    }
}