package com.zavierdev.todoapp.ui.listtodo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zavierdev.todoapp.constant.ApiConstant;
import com.zavierdev.todoapp.model.TodoModel;
import com.zavierdev.todoapp.resource.ResponseStatus;
import com.zavierdev.todoapp.resource.TodoResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListTodoViewModel extends ViewModel {
    public MutableLiveData<TodoResource> apiResponse = new MutableLiveData<>();

    public void getWeatherInformation() {
        String url = ApiConstant.BASE_URL + ApiConstant.GET_TODO_ENDPOINT;
        AsyncHttpClient client = new AsyncHttpClient();

        apiResponse.postValue(new TodoResource(ResponseStatus.LOADING, new ArrayList<>())); // Status Loading

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    ArrayList<TodoModel> todoList = new ArrayList<>();
                    JSONArray responseTodoArray = new JSONArray(result);

                    for (int i = 0; i < responseTodoArray.length(); i++) {
                        JSONObject todoObject = new JSONObject(String.valueOf(responseTodoArray.get(i)));
                        TodoModel model = new TodoModel();

                        model.setId(todoObject.getInt("id"));
                        model.setName(todoObject.getString("name"));
                        model.setCompleted(todoObject.getBoolean("isComplete"));
                        model.setCompletedAt(todoObject.getString("completedAt"));

                        todoList.add(model);
                    }

                    TodoResource resourceResponse = new TodoResource(ResponseStatus.SUCCESS, todoList);
                    apiResponse.postValue(resourceResponse);

                } catch (JSONException e) {
                    apiResponse.postValue(new TodoResource(ResponseStatus.FAILED, new ArrayList<>())); // Status Failed
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                apiResponse.postValue(new TodoResource(ResponseStatus.FAILED, new ArrayList<>())); // Status Failed
            }
        });
    }
}
