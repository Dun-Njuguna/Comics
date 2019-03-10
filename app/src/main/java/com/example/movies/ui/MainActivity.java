package com.example.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.search) Button search;
    @BindView(R.id.searchtext) EditText title;

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
            String titleSearch = title.getText().toString();
            Intent intent = new Intent(MainActivity.this, ComicsActivity.class);
            intent.putExtra("titleSearch", titleSearch);
            startActivity(intent);
        }
    }

}
