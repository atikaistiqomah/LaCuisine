package com.example.lacuisine;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TambahFragment extends Fragment {

    public String tnama,tjumlah,tnomor,key;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public TambahFragment(String tnama, String tjumlah, String tnomor, String key) {
        this.tnama = tnama;
        this.tjumlah = tjumlah;
        this.tnomor = tnomor;
        this.key = key;
    }

    public TextView tvnama,tvjumlah,tvnomor;
    public Button btnadd;

    public TambahFragment() {
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
        View v= inflater.inflate(R.layout.fragment_tambah, container, false);

        tvnama = v.findViewById(R.id.edt_nama);
        tvjumlah = v.findViewById(R.id.edt_jumlah);
        tvnomor = v.findViewById(R.id.edt_nomor);

        btnadd = v.findViewById(R.id.btn_save);

        tvnama.setText(tnama);
        tvjumlah.setText(tjumlah);
        tvnomor.setText(tnomor);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String vnama = tvnama.getText().toString();
                String vjumlah = tvjumlah.getText().toString();
                String vnomor = tvnomor.getText().toString();

                if (TextUtils.isEmpty(vnama)) {
                    input((EditText) tvnama, "Nama Menu");
                } else if (TextUtils.isEmpty(vjumlah)) {
                    input((EditText) tvjumlah, "Jumlah");
                } else if (TextUtils.isEmpty(vnomor)) {
                    input((EditText) tvnomor, "Nomor Meja");
                } else {
                    databaseReference.child("test").push().setValue(new Tambah(vnama, vjumlah, vnomor)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Pesanan terekam", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), "Pesanan tidak terekam", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return v;
    }

    private void input(EditText txt, String s) {
        txt.setError(s+" Tidak boleh kosong");
        txt.requestFocus();
    }
}