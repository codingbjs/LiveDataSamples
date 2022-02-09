package com.codingbjs.livedatasamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.codingbjs.livedatasamples.databinding.ActivityMainBinding;

import java.util.List;

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

        // todoDao().getAll()로 리턴 되는  LiveData 를  observe 로 갱신 체크
        db.todoDao().getAll().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                mainBinding.resultText.setText(todos.toString());
            }
        });

        // 버튼 클릭 시 DB에 데이터 저장
        mainBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.todoDao().insert(new Todo(mainBinding.todoEditText.getText().toString()));
            }
        });


    }
}