package com.zavierdev.todoapp.ui.updatetodoitem;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zavierdev.todoapp.R;
import com.zavierdev.todoapp.model.TodoModel;
import com.zavierdev.todoapp.resource.TodoResource;

import java.util.Date;

public class UpdateTodoActivity extends AppCompatActivity {
    private UpdateTodoViewModel viewModel;
    private EditText edtId;
    private EditText edtName;
    private Button btnUpdateTodo;
    private RadioButton radioCompleted;
    private RadioButton radioUncompleted;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UpdateTodoViewModel.class);

        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        btnUpdateTodo = findViewById(R.id.btn_put_todo);
        radioCompleted = findViewById(R.id.radio_completed);
        radioUncompleted = findViewById(R.id.radio_uncompleted);
        tvStatus = findViewById(R.id.tv_status);

        viewModel.apiResponse.observe(this, new Observer<TodoResource>() {
            @Override
            public void onChanged(TodoResource todoResource) {
                switch (todoResource.getResponse()) {
                    case SUCCESS:
                        tvStatus.setText("Data berhasil diubah !");
                        break;
                    case LOADING:
                        tvStatus.setText("Loading ...");
                        break;
                    case FAILED:
                        tvStatus.setText("Data gagal diubah !");
                        break;
                }
            }
        });

        btnUpdateTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmptyInput() == false) {
                    TodoModel model = new TodoModel();
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

                    model.setId(Integer.parseInt(edtId.getText().toString()));
                    model.setName(edtName.getText().toString());
                    model.setCompletedAt(simpleDateFormat.format(date));
                    if (radioCompleted.isChecked()) {
                        model.setCompleted(true);
                    } else if (radioUncompleted.isChecked()) {
                        model.setCompleted(false);
                    }

                    viewModel.updateTodoItem(UpdateTodoActivity.this, model);
                }
            }
        });
    }

    private boolean validateEmptyInput() {
        String valueEdtId = edtId.getText().toString();
        String valueEdtName = edtName.getText().toString();
        boolean isEmptyField = false;

        if (valueEdtId.isEmpty()) {
            isEmptyField = true;
            edtId.setError("Field Id Tidak Boleh Kosong !");
        }

        if (valueEdtName.isEmpty()) {
            isEmptyField = true;
            edtName.setError("Field Name Tidak Boleh Kosong !");
        }

        return isEmptyField;
    }
}