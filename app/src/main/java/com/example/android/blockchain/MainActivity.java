package com.example.android.blockchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button viewBC;

    Button dev1;
    Button dev2;

    Button dev3;

int totalDev = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewBC = (Button) findViewById(R.id.view_bc);
dev1 = (Button) findViewById(R.id.dev1);
        dev2= (Button) findViewById(R.id.dev2);
        dev3 = (Button) findViewById(R.id.dev3);

        dev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dev1.setEnabled(false);
                totalDev = totalDev*10+1;
            }
        });

        dev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dev2.setEnabled(false);
                totalDev = totalDev*10+2;
            }
        });
        dev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dev3.setEnabled(false);
                totalDev = totalDev*10+3;
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),BlocksOfBC.class);
                i.putExtra("devIDS",totalDev);
                startActivity(i);
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
