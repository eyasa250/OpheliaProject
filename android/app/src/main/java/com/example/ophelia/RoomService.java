package com.example.ophelia;// com.example.ophelia.RoomService.java
import androidx.room.Room;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RoomService {
    @POST("/rooms/add")
    Call<Void> addRoom(@Body com.example.ophelia.Room room);

}