package com.zavierdev.todoapp.ui.addtodoitem;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zavierdev.todoapp.constant.ApiConstant;
import com.zavierdev.todoapp.model.TodoModel;
import com.zavierdev.todoapp.resource.ResponseStatus;
import com.zavierdev.todoapp.resource.TodoResource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddTodoViewModel extends ViewModel {
    public MutableLiveData<TodoResource> apiResponse = new MutableLiveData<>();

    public void postTodoItem(Context context, TodoModel todoModel) {
        String url = ApiConstant.BASE_URL + ApiConstant.POST_TODO_ENDPOINT;
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject todoObject = new JSONObject();

        try {
            todoObject.put("id", todoModel.getId());
            todoObject.put("name", todoModel.getName());
            todoObject.put("isComplete", todoModel.isCompleted());
            todoObject.put("completedAt", todoModel.getCompletedAt());

            StringEntity jsonRequest = new StringEntity(todoObject.toString());
            apiResponse.postValue(new TodoResource(ResponseStatus.LOADING, new ArrayList<>())); // Status Loading

            client.post(context, url, jsonRequest, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    apiResponse.postValue(new TodoResource(ResponseStatus.SUCCESS, new ArrayList<>())); // Status Success
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    apiResponse.postValue(new TodoResource(ResponseStatus.FAILED, new ArrayList<>())); // Status Failed
                }
            });
        } catch (JSONException | UnsupportedEncodingException e) {
            apiResponse.postValue(new TodoResource(ResponseStatus.FAILED, new ArrayList<>())); // Status Failed
        }
    }
}
