package com.zavierdev.todoapp.resource;

import com.zavierdev.todoapp.model.TodoModel;

import java.util.ArrayList;

public class TodoResource {
    private ResponseStatus response;
    private ArrayList<TodoModel> listTodoItem;

    public TodoResource(ResponseStatus response, ArrayList<TodoModel> listTodoItem) {
        this.response = response;
        this.listTodoItem = listTodoItem;
    }

    public ResponseStatus getResponse() {
        return response;
    }

    public ArrayList<TodoModel> getListTodoItem() {
        return listTodoItem;
    }
}
