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

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {
    private List<TodoItem> taskList;
    private Context context;

    public HourAdapter(List<TodoItem> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }
    public void updateTasks(List<TodoItem> newTasks) {
        this.taskList.clear();
        this.taskList.addAll(newTasks);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.taskName);
            timeText = itemView.findViewById(R.id.taskTime);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem task = taskList.get(position);

        holder.nameText.setText(task.getName());
        holder.timeText.setText(task.getHourStart() + " - " + task.getHourFinish());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("taskName", task.getName());
            intent.putExtra("taskDate", task.getDate());
            intent.putExtra("taskStartTime", task.getHourStart());
            intent.putExtra("taskFinishTime", task.getHourFinish());
            intent.putExtra("taskDescription", task.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}