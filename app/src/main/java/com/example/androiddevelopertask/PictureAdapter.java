package com.example.androiddevelopertask;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class PictureAdapter extends BaseAdapter {

    static ArrayList<Bitmap> bitmaps;
    GridView gridView;
    LayoutInflater layoutInflater;



    PictureAdapter(Context parentContext, GridView grid){
        this.bitmaps=new ArrayList<>();
        this.gridView=grid;
        layoutInflater = LayoutInflater.from(parentContext);
    }

    public void addBitmaps(ArrayList<Bitmap> old){
        for (Bitmap bitmap: old
             ) {
            bitmaps.add(bitmap);

        }
    }

    @Override
    public int getCount() {
        if(bitmaps == null){
            return 0;
        }
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return  0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.image_layout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmaps.get(position));


        return convertView;

    }



}

