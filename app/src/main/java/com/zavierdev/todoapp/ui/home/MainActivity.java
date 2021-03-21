package com.zavierdev.todoapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zavierdev.todoapp.R;
import com.zavierdev.todoapp.ui.addtodoitem.AddTodoActivity;
import com.zavierdev.todoapp.ui.deletetodoitem.DeleteTodoItemActivity;
import com.zavierdev.todoapp.ui.listtodo.ListTodoActivity;
import com.zavierdev.todoapp.ui.updatetodoitem.UpdateTodoActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnTodoInfo;
    private Button btnTodoAdd;
    private Button btnTodoUpdate;
    private Button btnTodoDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTodoInfo = findViewById(R.id.btn_todo_info);
        btnTodoAdd = findViewById(R.id.btn_todo_add);
        btnTodoUpdate = findViewById(R.id.btn_todo_update);
        btnTodoDelete = findViewById(R.id.btn_todo_delete);

        btnTodoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListTodoActivity.class);
                startActivity(intent);
            }
        });

        btnTodoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });

        btnTodoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateTodoActivity.class);
                startActivity(intent);
            }
        });

        btnTodoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteTodoItemActivity.class);
                startActivity(intent);
            }
        });
    }
}