package com.omrfrkg.travelbook.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.omrfrkg.travelbook.model.Place;

@Database(entities = {Place.class},version = 1)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDAO placeDAO();
}
