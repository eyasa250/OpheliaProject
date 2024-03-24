package com.example.ophelia;// com.example.ophelia.RoomService.java

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RoomService {
    @POST("room/addroom")
    Call<Room> addRoom(@Body Room roomData);
    @GET("/room")
    Call<List<Room>> getRooms();
}