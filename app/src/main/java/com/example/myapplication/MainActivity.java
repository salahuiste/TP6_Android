package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button valider;
    private EditText repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valider=(Button) findViewById(R.id.buttonValider);
        repository=(EditText) findViewById(R.id.repositoryText);
        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
        githubService.listRepos("adrienbusin").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                afficherRepos(response.body());
            }

            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String repo=(String) repository.getText().toString();
                //si repository est vide, on sort de la fct
                if(repo.isEmpty()){
                    Toast.makeText(MainActivity.this,"Le champs repository est vide.", Toast.LENGTH_SHORT).show();
                    return;
                }
                githubService.searchRepos(repo).enqueue(new Callback<Repos>() {
                    @Override
                    public void onResponse(Call<Repos> call, Response<Repos> response) {
                        Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                        intent.putExtra("repositories",(Serializable) response.body());
                        startActivity(intent);
                        //Toast.makeText(MainActivity.this,String.valueOf(response.body().getItems().size()), Toast.LENGTH_SHORT).show();
                    }

                    public void onFailure(Call<Repos> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
    public void afficherRepos(List<Repo> repos) {
        Toast.makeText(this,"nombre de dépots : "+repos.size(), Toast.LENGTH_SHORT).show();
    }

}