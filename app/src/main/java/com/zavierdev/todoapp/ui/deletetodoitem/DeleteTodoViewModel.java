package com.zavierdev.todoapp.ui.deletetodoitem;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zavierdev.todoapp.constant.ApiConstant;
import com.zavierdev.todoapp.resource.ResponseStatus;
import com.zavierdev.todoapp.resource.TodoResource;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DeleteTodoViewModel extends ViewModel {
    public MutableLiveData<TodoResource> apiResponse = new MutableLiveData<>();

    public void deleteTodoItem(int id) {
        String url = ApiConstant.BASE_URL + ApiConstant.DELETE_TODO_ENDPOINT + id;
        AsyncHttpClient client = new AsyncHttpClient();
        apiResponse.postValue(new TodoResource(ResponseStatus.LOADING, new ArrayList<>())); // Status Loading

        client.delete(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                apiResponse.postValue(new TodoResource(ResponseStatus.SUCCESS, new ArrayList<>())); // Status Success
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                apiResponse.postValue(new TodoResource(ResponseStatus.FAILED, new ArrayList<>())); // Status Failed
            }
        });
    }
}