package com.example.ophelia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.cardview.widget.CardView cardView6 = findViewById(R.id.cardView6);

        // Set OnClickListener to the CardView

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the second activity
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }

    });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/room")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Initialize RoomService using Retrofit
        roomService = retrofit.create(RoomService.class);

        // Fetch room data from the server
        fetchRoomData();
}
    private void fetchRoomData() {
        Call<List<Room>> call = roomService.getRooms();

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
                Toast.makeText(MainActivity.this, "Failed to fetch room data. Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateCardViews(List<Room> rooms) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Loop through the list of rooms and populate card views
        int roomIndex = 0;
        for (Room room : rooms) {
            if (roomIndex < tableLayout.getChildCount()) {
                TableRow row = (TableRow) tableLayout.getChildAt(roomIndex);
                for (int i = 0; i < row.getChildCount(); i++) {
                    View view = row.getChildAt(i);
                    if (view instanceof CardView) {
                        CardView cardView = (CardView) view;
                        // Populate the card view with room data
                        // For example, set room name as card view's content
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(room.getNom());
                        cardView.addView(textView);
                        // Increment room index
                        roomIndex++;
                        break;
                    }
                }
            } else {
                break; // Stop populating if there are no more card views available
            }
        }
    }
}
