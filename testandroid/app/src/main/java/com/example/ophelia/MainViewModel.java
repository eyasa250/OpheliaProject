package com.example.ophelia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private RoomRepository roomRepository;
    private LiveData<List<Room>> roomsLiveData;

    public MainViewModel() {
        roomRepository = new RoomRepository();
        roomsLiveData = roomRepository.getRooms();
    }

    public LiveData<List<Room>> getRooms() {
        return roomsLiveData;
    }
}
