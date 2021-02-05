package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private LinearLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ln=(LinearLayout) findViewById(R.id.ly_liste);
        addRepoToList();
    }
    private void addRepoToList(){
        Intent i=getIntent();
        Repos repos=(Repos) i.getSerializableExtra("repositories");
        List<Repo> repo_list=repos.getItems();
        for (int j=0;j<repo_list.size();j++){
            TextView tv=new TextView(this);
            tv.setText(repo_list.get(j).getName());
            ln.addView(tv);
        }
    }
}