package com.example.returnto0;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.returnto0.room.Room;
import com.example.returnto0.room.RoomService;
import com.example.returnto0.task.addtask;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Fetch room data from the server
        fetchRoomData();
    }
    private void fetchRoomData() {
        RoomService apiService = RetrofitClient.getClient("http://10.0.2.2:5000").create(RoomService.class);
        Call<List<Room>> call = apiService.getAllRooms();
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Room> rooms = response.body();
                    populateCardViews(rooms);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch room data", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                String errorMessage;

                if (t instanceof IOException) {
                    // Network error
                    errorMessage = "Network error occurred: " + t.getMessage();
                } else {
                    // Other exceptions
                    errorMessage = "Failed to fetch room data. Unexpected error occurred: " + t.getMessage();
                }
                // Log the error
                Log.e("Fetch Room Data Error", errorMessage, t); // Log the exception details as well
                // Show toast message with error for a longer duration
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }

        });
    }
    private void populateCardViews(List<Room> rooms) {
        // Find all CardViews by their IDs
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);
        CardView cardView6 = findViewById(R.id.cardView6);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);

        // Set click listeners and text for each card dynamically
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            CardView cardView;
            TextView textView;

            switch (i) {
                case 0:
                    cardView = cardView1;
                    textView = textView1;
                    break;
                case 1:
                    cardView = cardView2;
                    textView = textView2;
                    break;
                case 2:
                    cardView = cardView3;
                    textView = textView3;
                    break;
                case 3:
                    cardView = cardView4;
                    textView = textView4;
                    break;
                case 4:
                    cardView = cardView5;
                    textView = textView5;
                    break;
                case 5:
                    cardView = cardView6;
                    textView = textView6;
                    break;
                default:
                    // If there are more than 6 rooms, ignore them
                    return;
            }

            // Set click listener
            final Room finalRoom = room; // capture the room object
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNextActivity(finalRoom); // pass the captured room object
                }
            });

            // Set the text of TextView
            textView.setText(room.getName());
        }
    }


    // Method to open the next activity and pass the text data
    private void openNextActivity(Room room) {
        Intent intent = new Intent(MainActivity.this, addtask.class);
        intent.putExtra("ROOM_OBJECT", room);
        startActivity(intent);
    }





}
