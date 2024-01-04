package org.parth.moviebooking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.parth.moviebooking.Adapter.FilmListAdapter;
import org.parth.moviebooking.Domain.FilmItem;
import org.parth.moviebooking.Domain.Listfilm;
import org.parth.moviebooking.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovie, adapterUpComming;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComming;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2;
    private ProgressBar loading1, loading2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVIew();
        sendRequest1();
        sendRequest2();
    }

    private void sendRequest1() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            Listfilm items=gson.fromJson(response, Listfilm.class);
            adapterNewMovie=new FilmListAdapter(items);
            recyclerViewNewMovies.setAdapter(adapterNewMovie);
        }, error -> {
            loading1.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequest2() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);

            Listfilm items=gson.fromJson(response, Listfilm.class);
            adapterUpComming=new FilmListAdapter(items);
            recyclerViewUpComming.setAdapter(adapterUpComming);
        }, error -> {
            loading2.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initVIew() {
    recyclerViewNewMovies = findViewById(R.id.view1);
    recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerViewUpComming=findViewById(R.id.view2);
    recyclerViewUpComming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    loading1=findViewById(R.id.loading1);
    loading2=findViewById(R.id.loading2);

    }
}