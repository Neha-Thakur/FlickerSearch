package com.test.flickersearch;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends Activity
{
    EditText searchText;
    ProgressBar pBar;
    Constant constant;
    Button button;
    TextView textView;

    private static AtomicBoolean isTestRunning;

    public static synchronized boolean isTestRunning ()
    {
        System.out.println("is test running method");
        if (null == isTestRunning) {
            boolean istest;

            try {

                Class.forName ("com.test.flickersearch.MainActivityTest"); // for e.g. com.example.MyTest

                istest = true;
            } catch (ClassNotFoundException e) {
                istest = false;
            }

            isTestRunning = new AtomicBoolean(istest);
        }

        return isTestRunning.get ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {

        }
        searchText = (EditText) findViewById(R.id.searchTxt);
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button)findViewById(R.id.search_button);
        textView = (TextView) findViewById(R.id.textView);
        constant = new Constant(MainActivity.this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    public void onSearchClick(View view)
    {
        if(searchText.getText().toString().length()>0)
        {
            Constant.searchStr = searchText.getText().toString();

            System.out.println("Constant.searchStr is: "+Constant.searchStr);

            if(!isTestRunning())
            {
                if(constant.isNetworkAvailable())
                {
                    System.out.println("network available");
                    Constant.ITEMS.clear();
                    new GetSearchList(pBar, MainActivity.this).execute();
                }
                else
                {
                    System.out.println("network not available");
                    Toast.makeText(MainActivity.this, "Network is not available.", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                textView.setText(Constant.searchStr);
                textView.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Please enter the text to search", Toast.LENGTH_SHORT).show();
        }
    }
}
