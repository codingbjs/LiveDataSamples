package com.codingbjs.livedatasamples;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    // List<T> 를 LiveData 로 리턴
    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getAll();

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);
}
