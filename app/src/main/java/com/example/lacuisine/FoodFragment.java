package com.example.lacuisine;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FoodFragment extends Fragment {

    View v;
    private RecyclerView mRecyclerView;
    private MenuAdapter recycleradapter;
    private ArrayList<Menu> arrayList=new ArrayList<Menu>();
    private DatabaseHelper databaseHelper;
    private Cursor cursor;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_food, container, false);

        mRecyclerView = v.findViewById(R.id.rv_food);

        loadDatabase();

        return v;
    }

    public void loadDatabase() {
        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        try{
            databaseHelper.checkNCopyDatabase();
            databaseHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        try {
            //databaseHelper.getReadableDatabase();
            cursor = databaseHelper.QueryFood();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Menu mMenu = new Menu();

                        mMenu.setNama(cursor.getString(1));
                        mMenu.setGambar(cursor.getString(2));
                        mMenu.setHarga(cursor.getString(3));
                        mMenu.setDeskripsi(cursor.getString(4));

                        arrayList.add(mMenu);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        recycleradapter = new MenuAdapter(getContext(), arrayList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.setAdapter(recycleradapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }
}