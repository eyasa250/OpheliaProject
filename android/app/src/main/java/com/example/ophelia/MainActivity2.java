package com.example.ophelia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        roomService = RetrofitClient.getRoomService();

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
        String roomType = "";

        if (checkedRadioButtonId == R.id.radioButtonSingleRoom) {
            roomType = "Single Room";
        } else if (checkedRadioButtonId == R.id.radioButtonDoubleRoom) {
            roomType = "Double Room";
        } else if (checkedRadioButtonId == R.id.radioButtonSuite) {
            roomType = "Suite";
        }
// Handle other radio buttons here


        // Create a Room object
        Room room = new Room(0,roomType) ;// Assuming 0 for the ID of a new room

        // Make an HTTP request to add the room
        Call<Void> call = roomService.addRoom(room);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Room added successfully
                    Toast.makeText(MainActivity2.this, "Room added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Error handling
                    Toast.makeText(MainActivity2.this, "Failed to add room", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Network error handling
                Toast.makeText(MainActivity2.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
