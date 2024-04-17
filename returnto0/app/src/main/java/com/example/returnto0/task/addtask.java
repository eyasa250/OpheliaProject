package com.example.returnto0.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.returnto0.R;
import com.example.returnto0.room.Room;

public class addtask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        // Get the text from the intent
        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra("ROOM_OBJECT");
        // Find the TextView in the layout
        TextView textView = findViewById(R.id.textView);

        // Set the text to the TextView
        textView.setText(room.getName());

    }

}