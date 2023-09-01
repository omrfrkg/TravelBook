package com.omrfrkg.travelbook.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.omrfrkg.travelbook.R;
import com.omrfrkg.travelbook.adapter.RecyclerViewAdapter;
import com.omrfrkg.travelbook.databinding.ActivityMainBinding;
import com.omrfrkg.travelbook.model.Place;
import com.omrfrkg.travelbook.roomdb.PlaceDAO;
import com.omrfrkg.travelbook.roomdb.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    PlaceDAO placeDAO;
    PlaceDatabase placeDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        placeDatabase = Room.databaseBuilder(getApplicationContext(), PlaceDatabase.class,"PlaceDb").build();
        placeDAO = placeDatabase.placeDAO();

        compositeDisposable.add(placeDAO.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainActivity.this::handleResponse));



    }

    private void handleResponse(List<Place> placeList){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(placeList);
        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_place){
            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}