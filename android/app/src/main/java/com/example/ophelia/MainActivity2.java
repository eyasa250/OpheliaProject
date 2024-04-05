package com.example.ophelia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {
    private RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8090/room/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Initialize RoomService using Retrofit
        roomService = retrofit.create(RoomService.class);

        // Handle click event of Next button
        Button nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoomToDatabase();
            }
        });
    }

    private void addRoomToDatabase() {
        RadioGroup radioGroup = findViewById(R.id.radioGroupRoomType);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String roomName = "";

        if (checkedRadioButtonId == R.id.radioButtonSingleRoom) {
            roomName = "Single Room";
        } else if (checkedRadioButtonId == R.id.radioButtonDoubleRoom) {
            roomName = "Double Room";
        } else if (checkedRadioButtonId == R.id.radioButtonSuite) {
            roomName = "Suite";
        }

        // Create a Room object
        Room room = new Room(12,roomName);
//        Room room = new Room(12,"testlocal");


        // Make an HTTP request to add the room
        Call<Room> call = roomService.addRoom(room);

        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Room addedRoom = response.body();
                    String roomName = addedRoom.getNom();
                    // Display success message with room name
                    Toast.makeText(MainActivity2.this, "Room added successfully: " + roomName, Toast.LENGTH_SHORT).show();
                } else {
                    // Error handling
                    Toast.makeText(MainActivity2.this, "Failed to add room", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                if (t instanceof IOException) {
                    // Network error
                    Toast.makeText(MainActivity2.this, "Failed to add room.  error", Toast.LENGTH_SHORT).show();
                } else {
                    // Other errors
                    Toast.makeText(MainActivity2.this, "Failed to add room: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }            }
        });
    }
}
