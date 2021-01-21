package com.example.lacuisine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.HViewHolder> {

    private List<Tambah> mList;
    private Activity activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public HasilAdapter(List<Tambah> mList, Activity activity) {
        this.mList = mList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public HViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View hasilview = inflater.inflate(R.layout.item_tambah, parent, false);
        return new HViewHolder(hasilview);
    }

    @Override
    public void onBindViewHolder(@NonNull HViewHolder holder, final int position) {
        holder.tv_nama.setText("Nama menu : "+ mList.get(position).getTnama());
        holder.tv_jumlah.setText("Jumlah pesan : " + mList.get(position).getTjumlah());
        holder.tv_nomor.setText("Nomor meja : "+mList.get(position).getTnomor());
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("test").child(mList.get(position).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(activity, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Yakin mau menghapus pesanan " + mList.get(position).getTnama()+" ?");
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HViewHolder extends RecyclerView.ViewHolder{
        TextView tv_nama, tv_jumlah, tv_nomor;
        CardView cardView;
        Button btnadd;

        public HViewHolder(View hasilview) {
            super(hasilview);

            tv_nama = hasilview.findViewById(R.id.nama_id);
            tv_jumlah = hasilview.findViewById(R.id.jumlah_id);
            tv_nomor = hasilview.findViewById(R.id.meja_id);
            cardView = hasilview.findViewById(R.id.card_view);
            btnadd = hasilview.findViewById(R.id.delete_id);
        }
    }
}
