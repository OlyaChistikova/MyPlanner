package com.example.mycalendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<TodoItem> todoItems;
    private Context context;
    private List<String> timeSlots; // Список временных слотов

    public TodoAdapter(Context context, List<TodoItem> todoItems) {
        this.context = context;
        this.todoItems = todoItems;
        initTimeSlots(); // Инициализация временных слотов
    }

    private void initTimeSlots() {
        timeSlots = new ArrayList<>();
        // Пример временных слотов: от 9:00 до 18:00 с шагом в 1 час
        for (int hour = 9; hour <= 18; hour++) {
            timeSlots.add(String.format("%02d:00", hour));
        }
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if (position < timeSlots.size()) {
            String timeSlot = timeSlots.get(position);
            holder.timeTextView.setText(timeSlot);

            // Проверка, есть ли задача на этот временной слот
            TodoItem task = findTaskForTimeSlot(timeSlot);
            if (task != null) {
                holder.titleTextView.setText(task.getName());
                holder.titleTextView.setVisibility(View.VISIBLE);
            } else {
                holder.titleTextView.setText("");
                holder.titleTextView.setVisibility(View.GONE);
            }
        }
    }

    private TodoItem findTaskForTimeSlot(String timeSlot) {
        for (TodoItem item : todoItems) {
            // Предположим, у вас есть метод getTime() в TodoItem, который возвращает время
            if (item.getHourStart().equals(timeSlot)) {
                return item;
            }
        }
        return null; // нет задачи на этот временной слот
    }

    @Override
    public int getItemCount() {
        return timeSlots.size(); // Возвращает количество временных слотов
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        TextView titleTextView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.text_view_time);
            titleTextView = itemView.findViewById(R.id.text_view_title);
        }
    }
}