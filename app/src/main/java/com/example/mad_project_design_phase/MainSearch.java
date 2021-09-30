package com.example.mad_project_design_phase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class MainSearch extends AppCompatActivity implements View.OnClickListener{

    public CardView itl, jap, ind, amr, vege, other;
    SearchView search;
    Button submit;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        i = new Intent(MainSearch.this,all_the_restaurants.class);
        search = (SearchView) findViewById(R.id.searchView);
        submit = (Button) findViewById(R.id.searchSubmit);

        itl = (CardView) findViewById(R.id.italian);
        jap = (CardView) findViewById(R.id.japanese);
        ind = (CardView) findViewById(R.id.indian);
        amr = (CardView) findViewById(R.id.american);
        vege = (CardView) findViewById(R.id.vegetarian);
        other = (CardView) findViewById(R.id.others);

        itl.setOnClickListener(this);
        jap.setOnClickListener(this);
        ind.setOnClickListener(this);
        amr.setOnClickListener(this);
        vege.setOnClickListener(this);
        other.setOnClickListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = search.getQuery().toString();
                i.putExtra("filter",type);
                startActivity(i);

            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.italian:
                i.putExtra("filter", "italian");
                startActivity(i);
                break;

            case R.id.japanese:
                i.putExtra("filter", "japanese");
                startActivity(i);
                break;

            case R.id.american:
                i.putExtra("filter", "american");
                startActivity(i);
                break;

            case R.id.indian:
                i.putExtra("filter", "indian");
                startActivity(i);
                break;

            case R.id.vegetarian:
                i.putExtra("filter", "vegetarian");
                startActivity(i);
                break;

            case R.id.others:
                i.putExtra("filter", "other");
                startActivity(i);
                break;

        }
    }

}