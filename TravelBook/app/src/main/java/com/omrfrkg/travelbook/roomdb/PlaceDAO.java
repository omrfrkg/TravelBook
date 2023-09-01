package com.omrfrkg.travelbook.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.omrfrkg.travelbook.model.Place;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlaceDAO {

    @Query("SELECT * FROM PlaceDb")
    Flowable<List<Place>> getAll();

    /*@Query("SELECT * FROM PlaceDb WHERE name = :deneme")
    List<Place> getNameAll(String deneme);*/

    @Insert
    Completable insert(Place place);

    @Delete
    Completable delete(Place place);

}
