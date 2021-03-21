package com.zavierdev.todoapp.ui.listtodo;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavierdev.todoapp.R;
import com.zavierdev.todoapp.resource.TodoResource;

public class ListTodoActivity extends AppCompatActivity {
    private ListTodoViewModel viewModel;
    private ProgressBar progressLoading;
    private TextView tvFailedDisplay;
    private RecyclerView rvTodoItem;
    private ListTodoRecyclerViewAdapter rvTodoItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_todo);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ListTodoViewModel.class);

        progressLoading = findViewById(R.id.progress_loading);
        tvFailedDisplay = findViewById(R.id.tv_failed_display);

        rvTodoItem = findViewById(R.id.rv_todo_item);
        rvTodoItemAdapter = new ListTodoRecyclerViewAdapter();
        rvTodoItem.setLayoutManager(new LinearLayoutManager(this));
        rvTodoItem.setAdapter(rvTodoItemAdapter);

        viewModel.getWeatherInformation();
        viewModel.apiResponse.observe(this, new Observer<TodoResource>() {
            @Override
            public void onChanged(TodoResource todoResource) {
                switch (todoResource.getResponse()) {
                    case SUCCESS:
                        if (todoResource.getListTodoItem() != null) {
                            rvTodoItemAdapter.setTodoList(todoResource.getListTodoItem());
                            rvTodoItemAdapter.notifyDataSetChanged();
                        }

                        progressLoading.setVisibility(View.GONE); // Hide View
                        tvFailedDisplay.setVisibility(View.GONE); // Hide View
                        rvTodoItem.setVisibility(View.VISIBLE); // Show View

                        break;
                    case LOADING:
                        progressLoading.setVisibility(View.VISIBLE); // Show View
                        tvFailedDisplay.setVisibility(View.GONE); // Hide View
                        rvTodoItem.setVisibility(View.GONE); // Hide View
                        break;
                    case FAILED:
                        progressLoading.setVisibility(View.GONE); // Hide View
                        tvFailedDisplay.setVisibility(View.VISIBLE); // Show View
                        rvTodoItem.setVisibility(View.GONE); // Hide View
                        break;
                }
            }
        });
    }
}