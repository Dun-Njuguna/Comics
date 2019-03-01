package com.example.movies;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.search) Button search;
    @BindView(R.id.searchtext) EditText searchtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == search) {
            String text = searchtext.getText().toString();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    }

}
