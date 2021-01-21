package com.example.lacuisine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HasilFragment extends Fragment {
    RecyclerView hRecyclerView;
    HasilAdapter Hadapter;
    ArrayList<Tambah>  listOrder;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public HasilFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hasil, container, false);

        hRecyclerView = v.findViewById(R.id.rv_hasil);

        hRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showdata();

        return v;
    }

    private void showdata() {
        databaseReference.child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOrder = new ArrayList<>();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Tambah tmb = item.getValue(Tambah.class);
                    tmb.setKey(item.getKey());
                    listOrder.add(tmb);
                }

                Hadapter = new HasilAdapter(listOrder, getActivity());
                hRecyclerView.setAdapter(Hadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}