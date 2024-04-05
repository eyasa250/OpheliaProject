package com.example.ophelia.task;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ophelia.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TaskService taskService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

        taskService = ApiClient.getClient().create(TaskService.class);

        // Fetch tasks
        fetchTasks();
    }

    private void fetchTasks() {
        Call<List<Task>> call = taskService.getAllTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Task> tasks = response.body();
                    // Populate your UI with the received task data
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch task data", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                // Handle network request failure
                Toast.makeText(MainActivity.this, "Network request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

