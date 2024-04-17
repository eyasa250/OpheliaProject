package com.example.returnto0.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.returnto0.MainActivity;
import com.example.returnto0.R;
import com.example.returnto0.RetrofitClient;
import com.example.returnto0.databinding.FragmentMylistBinding;
import com.example.returnto0.room.Room;
import com.example.returnto0.room.RoomService;
import com.example.returnto0.task.addtask;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    private AppCompatActivity activity;

    private FragmentMylistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentMylistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fetchRoomData();
        return root;
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
                    Toast.makeText(requireContext(), "Failed to fetch room data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                String errorMessage;
                if (t instanceof IOException) {
                    errorMessage = "Network error occurred: " + t.getMessage();
                } else {
                    errorMessage = "Failed to fetch room data. Unexpected error occurred: " + t.getMessage();
                }
                Log.e("Fetch Room Data Error", errorMessage, t);
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateCardViews(List<Room> rooms) {
        if (getView() == null) {
            return; // Fragment view not yet created
        }

        // Find all CardViews by their IDs
        CardView cardView1 = getView().findViewById(R.id.cardView1);
        CardView cardView2 = getView().findViewById(R.id.cardView2);
        CardView cardView3 = getView().findViewById(R.id.cardView3);
        CardView cardView4 = getView().findViewById(R.id.cardView4);
        CardView cardView5 = getView().findViewById(R.id.cardView5);
        CardView cardView6 = getView().findViewById(R.id.cardView6);
        TextView textView1 = getView().findViewById(R.id.textView1);
        TextView textView2 = getView().findViewById(R.id.textView2);
        TextView textView3 = getView().findViewById(R.id.textView3);
        TextView textView4 = getView().findViewById(R.id.textView4);
        TextView textView5 = getView().findViewById(R.id.textView5);
        TextView textView6 = getView().findViewById(R.id.textView6);

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
        Intent intent = new Intent(requireContext(), addtask.class);
        intent.putExtra("ROOM_OBJECT", room);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
