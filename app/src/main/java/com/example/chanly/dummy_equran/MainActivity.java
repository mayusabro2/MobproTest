package com.example.chanly.dummy_equran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue mRequestQueue;
    ArrayList<Surah> mListSurah;
    SurahAdapter mSurahAdapter;
    LinearLayout mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        ListView mSurahLV = findViewById(R.id.surah);
        mProgressBar = findViewById(R.id.progress_bar);aaa
// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();
        mListSurah = new ArrayList<>();

        mSurahAdapter = new SurahAdapter(this, mListSurah);
        mSurahLV.setAdapter(mSurahAdapter);
        String url = "http://api.alquran.cloud/surah";

        mSurahLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSurah = new Intent(MainActivity.this,AyahActivity.class);
                intentSurah.putExtra("NumberOfSurah", String.valueOf(mListSurah.get(position).getNumber()));

                startActivity(intentSurah);
            }
        });
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("MainActivity", "Response: " + response);
                        JSONArray quran = response.optJSONArray("data");
                        if (quran != null) {
                            Log.d("MainActivity", "ResponseData: " + quran);
                            for (int i = 0; i< quran.length() ; i++){
                                JSONObject surah = quran.optJSONObject(i);
                                int surah_number = surah.optInt("number");
                                String surah_name_arabic = surah.optString("name");
                                String surah_name_latin = surah.optString("englishName");
                                String surah_name_trans = surah.optString("englishNameTranslation");
                                int surah_ayahs_number = surah.optInt("numberOfAyahs");
                                mListSurah.add(new Surah(surah_number,surah_name_arabic,surah_name_latin,surah_name_trans,surah_ayahs_number));

                            }
                            Log.d("MainActivity", "ResponseDataTotal: " + mListSurah.size());
                            mSurahAdapter.notifyDataSetChanged();
                            mProgressBar.setVisibility(View.GONE);
                        } else {
                            Log.d("MainActivity", "Response2: " + response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MainActivity", "Error: " + error.toString());
                    }
                }
                );
        // Add the request to the RequestQueue.
        mSurahAdapter.notifyDataSetChanged();
        mRequestQueue.add(jsonObjectRequest);
    }
}
