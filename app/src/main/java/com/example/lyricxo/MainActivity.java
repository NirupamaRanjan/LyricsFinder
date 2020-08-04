package com.example.lyricxo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
     EditText mArtistName;
     EditText mSongName;
     TextView mLyrics;
     Button mbtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArtistName=findViewById(R.id.edtArtistName);
        mSongName=findViewById(R.id.edtSongName);
        mLyrics=findViewById(R.id.txtLyrics);
        mbtnOk=findViewById(R.id.btnOK);


        mbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://api.lyrics.ovh/v1/"+mArtistName.getText().toString()+"/"+mSongName.getText().toString();
                url.replace("","20%");
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            mLyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                }},
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mLyrics.setText("Invalid Information");
                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });


    }

}