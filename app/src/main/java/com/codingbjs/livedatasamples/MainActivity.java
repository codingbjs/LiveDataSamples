package com.codingbjs.livedatasamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.codingbjs.livedatasamples.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        db = Room.databaseBuilder(this, AppDatabase.class, "todo_db")
                .allowMainThreadQueries()
                .build();

        mainBinding.resultText.setText(db.todoDao().getAll().toString());

        mainBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.todoDao().insert(new Todo(mainBinding.todoEditText.getText().toString()));
                mainBinding.resultText.setText(db.todoDao().getAll().toString());
            }
        });


    }
}