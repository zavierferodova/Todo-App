package com.zavierdev.todoapp.ui.listtodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavierdev.todoapp.R;
import com.zavierdev.todoapp.model.TodoModel;
import com.zavierdev.todoapp.utils.DateTimeUtils;

import java.util.ArrayList;

public class ListTodoRecyclerViewAdapter extends RecyclerView.Adapter<ListTodoRecyclerViewAdapter.ViewHolder> {
    private ArrayList<TodoModel> todoList = new ArrayList<>();

    public void setTodoList(ArrayList<TodoModel> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(todoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(todoList.get(position));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_date;
        private TextView tv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_status = itemView.findViewById(R.id.tv_status);
        }

        public void bind(TodoModel todoModel) {
            String formattedDate = DateTimeUtils.stringDateIndonesiaFormat(todoModel.getCompletedAt());

            tv_id.setText("Id : " + todoModel.getId());
            tv_name.setText(todoModel.getName());
            tv_date.setText(formattedDate);

            if (todoModel.isCompleted()) {
                tv_status.setText("Completed");
            } else {
                tv_status.setText("Uncompleted");
            }
        }
    }
}
