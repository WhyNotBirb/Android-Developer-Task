package com.example.androiddevelopertask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PictureDownload extends AsyncTask<String[], Void, ArrayList<Bitmap>> {

    public interface AsyncTaskListener {
        void onPreExecute();
        void onPostExecute(ArrayList<Bitmap> result);
    }

    String[] imageURLs;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    static int countCurrentSize=0;
    int iterator=50;

    URL url;

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Downloading Images");
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(false);
        dialog.show();


    }

    public PictureDownload(String[] imageURLs, Context context) {
        this.context = context;
        this.imageURLs = imageURLs;

    }

    private AsyncTaskListener mListener;

    final public void setListener(AsyncTaskListener listener) {
        mListener = listener;
    }

       protected ArrayList<Bitmap> doInBackground(String[]... imageURL) {





           HttpURLConnection connection = null;

           try  {
               for (int i=countCurrentSize; i< countCurrentSize+iterator; i++) {

                   try {
                       url = new URL(imageURLs[i].toString()) ;
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   }
                   connection = (HttpURLConnection) url.openConnection();
                   connection.connect();


                    try{

                            InputStream is = connection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            bitmaps.add(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
               }


            } catch (Exception e) {
                e.printStackTrace();
            }
           //connection.disconnect();
            countCurrentSize += iterator;
           return bitmaps;
        }

        protected void onPostExecute(ArrayList<Bitmap> bitmaps){
            if (mListener != null)

            mListener.onPostExecute(bitmaps);

            dialog.dismiss();


        }




    }












