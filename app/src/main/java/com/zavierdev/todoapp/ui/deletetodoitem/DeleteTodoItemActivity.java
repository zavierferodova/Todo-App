package com.zavierdev.todoapp.ui.deletetodoitem;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zavierdev.todoapp.R;
import com.zavierdev.todoapp.resource.TodoResource;

public class DeleteTodoItemActivity extends AppCompatActivity {
    private DeleteTodoItemViewModel viewModel;
    private EditText edtId;
    private Button btnDeleteTodo;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_todo_item);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DeleteTodoItemViewModel.class);

        edtId = findViewById(R.id.edt_id);
        btnDeleteTodo = findViewById(R.id.btn_delete_todo);
        tvStatus = findViewById(R.id.tv_status);

        viewModel.apiResponse.observe(this, new Observer<TodoResource>() {
            @Override
            public void onChanged(TodoResource todoResource) {
                switch (todoResource.getResponse()) {
                    case SUCCESS:
                        tvStatus.setText("Data berhasil dihapus !");
                        break;
                    case LOADING:
                        tvStatus.setText("Loading ...");
                        break;
                    case FAILED:
                        tvStatus.setText("Data gagal dihapus !");
                        break;
                }
            }
        });

        btnDeleteTodo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (validateEmptyInput() == false) {
                    viewModel.deleteTodoItem(Integer.parseInt(edtId.getText().toString()));
                }
            }
        });
    }

    private boolean validateEmptyInput() {
        String valueEdtId = edtId.getText().toString();
        boolean isEmptyField = false;

        if (valueEdtId.isEmpty()) {
            isEmptyField = true;
            edtId.setError("Field Id Tidak Boleh Kosong !");
        }

        return isEmptyField;
    }
}