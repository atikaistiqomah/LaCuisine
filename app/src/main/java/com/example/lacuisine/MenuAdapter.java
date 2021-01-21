package com.example.lacuisine;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MViewHolder> {

    private Context mContext;
    private ArrayList<Menu> mMenu;
    private Dialog mDialog;

    public MenuAdapter(Context mContext, ArrayList<Menu> mMenu) {
        this.mContext = mContext;
        this.mMenu = mMenu;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
        final MViewHolder mHolder = new MViewHolder(v);

        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_count);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mHolder.item_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialogName_tv = (TextView) mDialog.findViewById(R.id.dialog_name);
                TextView dialogPrice_tv = (TextView) mDialog.findViewById(R.id.dialog_price);
                TextView dialogDesc_tv = (TextView) mDialog.findViewById(R.id.dialog_desc);
                ImageView dialogImage_iv = (ImageView) mDialog.findViewById(R.id.dialog_image);

                dialogName_tv.setText(mMenu.get(mHolder.getAdapterPosition()).getNama());
                dialogPrice_tv.setText(mMenu.get(mHolder.getAdapterPosition()).getHarga());
                dialogDesc_tv.setText(mMenu.get(mHolder.getAdapterPosition()).getDeskripsi());
                //dialogImage_iv.setTag(mMenu.get(mHolder.getAdapterPosition()).getGambar());
                try {
                    InputStream ims = null;
                    ims = mContext.getAssets().open(mMenu.get(mHolder.getAdapterPosition()).getGambar());
                    Drawable d = Drawable.createFromStream(ims, null);
                    dialogImage_iv.setImageDrawable(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mDialog.show();
            }
        });

        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder holder, int position) {

        holder.tv_name.setText(mMenu.get(position).getNama());
        holder.tv_price.setText(mMenu.get(position).getHarga());
        try {
            InputStream ims = null;
            ims = mContext.getAssets().open(mMenu.get(position).getGambar());
            Drawable d = Drawable.createFromStream(ims, null);
            holder.iv_image.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }

    public static class MViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_price;
        private ImageView iv_image;
        private LinearLayout item_menu;


        public MViewHolder(View menuView) {
            super(menuView);

            item_menu = (LinearLayout) menuView.findViewById(R.id.item_id);
            tv_name = (TextView) menuView.findViewById(R.id.name_id);
            tv_price = (TextView) menuView.findViewById(R.id.price_id);
            iv_image = (ImageView) menuView.findViewById(R.id.img_menu);
        }
    }
}
