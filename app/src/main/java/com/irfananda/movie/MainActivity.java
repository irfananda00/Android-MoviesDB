package com.irfananda.movie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<MovieDao.ResultsBean> list = new ArrayList<>();
    private NotesAdapter adapter;
    private RecyclerView rv;
    private LinearLayout linearLayout;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        linearLayout = (LinearLayout) findViewById(R.id.refres);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setHasFixedSize(true);
        progress = ProgressDialog.show(this,null, "Mohon Tunggu", true);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){
        NotesClient client = Service.createService(NotesClient.class);
        Call<MovieDao> call = client.getMovie(1);
        call.enqueue(new Callback<MovieDao>() {
            @Override
            public void onResponse(Call<MovieDao> call, Response<MovieDao> response) {
                if (linearLayout.getVisibility() == View.VISIBLE){
                    linearLayout.setVisibility(View.GONE);
                }
                MovieDao movieDao = response.body();
                list = movieDao.getResults();
                adapter = new NotesAdapter(MainActivity.this,list);
                rv.setAdapter(adapter);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<MovieDao> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                linearLayout.setVisibility(View.VISIBLE);
                progress.dismiss();
            }
        });

    }

    public void onClick(View view){
        progress = ProgressDialog.show(this,"Loading", "Harap Sabar", true);
        loadData();
    }
}
