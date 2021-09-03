package com.example.androiddevelopertask;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class GetJsonDataToArray {

   public String[] strings;

    public GetJsonDataToArray(InputStream is) throws IOException, JSONException {

        strings=AddElementsToArray(readJson(is));

       // AddElementsToArray(json)[1];

    }



    public  String[] AddElementsToArray(JSONObject json) throws JSONException {

       JSONArray jsonArray=json.getJSONArray("urls");
       String[] urlArray=new String[jsonArray.length()];
       for (int i=0; i<jsonArray.length();i++){
           urlArray[i]=jsonArray.getString(i);
       }

        return urlArray;

    }

    public  JSONObject readJson(InputStream inputStream) throws IOException, JSONException {

        InputStream is = inputStream;
        StringBuilder stringBuilder = new StringBuilder();


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }


            String jsonText = stringBuilder.toString();

            JSONObject json = new JSONObject(jsonText);

            return json;
        } finally {
            is.close();
        }


    }
}






