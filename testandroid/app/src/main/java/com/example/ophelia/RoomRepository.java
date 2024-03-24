package com.example.ophelia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomRepository {
    private RoomService roomService;

    public RoomRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        roomService = retrofit.create(RoomService.class);
    }

    public LiveData<List<Room>> getRooms() {
        MutableLiveData<List<Room>> roomsLiveData = new MutableLiveData<>();

        roomService.getRooms().enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    roomsLiveData.setValue(response.body());
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                // Handle failure
            }
        });

        return roomsLiveData;
    }
}
