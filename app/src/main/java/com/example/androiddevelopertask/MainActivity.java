package com.example.androiddevelopertask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PictureDownload.AsyncTaskListener{




    public MainActivity() throws IOException, JSONException {
    }



    InputStream is;
    GetJsonDataToArray get;
    PictureDownload pictureDownload;
    GridView gridView;
    PictureAdapter pictureAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        pictureAdapter = new PictureAdapter(this, gridView);
        gridView.setAdapter(pictureAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pictureAdapter.bitmaps.get(i).compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent itemOnClickedIntent = new Intent(MainActivity.this, ItemOnClickedActivity.class);
                itemOnClickedIntent.putExtra("image",byteArray);

                startActivity(itemOnClickedIntent);

            }
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstVisibleItem, int VisibleItemCount, int TotalDataCount) {
                if (TotalDataCount == pictureDownload.countCurrentSize&&TotalDataCount>=50&&TotalDataCount<=get.strings.length){

                    if(FirstVisibleItem+VisibleItemCount>=TotalDataCount){

                        pictureDownload=new PictureDownload(get.strings, MainActivity.this);
                        pictureDownload.setListener(MainActivity.this);
                        pictureDownload.execute();
                    }

            }}


        });


        is =this.getResources().openRawResource(R.raw.dog_urls);


        try {
            get=new GetJsonDataToArray(is);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        pictureDownload=new PictureDownload(get.strings, this);
        pictureDownload.setListener(this);
        pictureDownload.execute();

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(ArrayList<Bitmap> bitmaps) {

        pictureAdapter.addBitmaps(bitmaps);
        print();

    }

    public void print(){

        pictureAdapter.notifyDataSetChanged();

    }

}