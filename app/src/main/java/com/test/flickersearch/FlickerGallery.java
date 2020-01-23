package com.test.flickersearch;

import android.app.Activity;
import android.os.Bundle;
//import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.flickersearch.items.SearchItems;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlickerGallery extends Activity
{
    RecyclerView recyclerView;
    private EndlessRecyclerViewScrollListener scrollListener;

    ProgressBar pBar;
    String searchOuput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flicker_gallery);

        if(savedInstanceState == null)
        {
            getView();

            if(Constant.ITEMS.size() > 0)
            {
                CustomAdapter customAdapter = new CustomAdapter(FlickerGallery.this, Constant.ITEMS);
                recyclerView.setAdapter(customAdapter);
            }
             parseJsonObj(searchOuput, recyclerView);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        System.out.println("onSaveInstanceState");

        outState.putString("searchOuput", searchOuput);
        outState.putParcelable("saved_layout_mgmr", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState");
        searchOuput = savedInstanceState.getString("searchOuput");
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        System.out.println("onResume");
        getView();

        System.out.println("onResume  search output: "+searchOuput);
        parseJsonObj(searchOuput, recyclerView);
    }

    private void getView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.rvList);

        pBar = (ProgressBar) findViewById(R.id.progressBar);

        searchOuput = getIntent().getStringExtra("searchOutput");

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                System.out.println("previous page is: "+page);

                page = page+1;
                Constant.FLICKER_GALLERY = true;

                System.out.println("next page is: "+page);

                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadNextDataFromApi(int page)
    {
        Constant.page = page;

        new GetSearchList(pBar, FlickerGallery.this, recyclerView).execute();
    }

    public void parseJsonObj(String searchOutput, RecyclerView rv)
    {
        this.recyclerView = rv;
        System.out.println("in parseJsonObj");

        try
        {
            JSONObject jObj = new JSONObject(searchOutput);
            JSONObject jPhotosObj = jObj.getJSONObject("photos");
            JSONArray photosArray = jPhotosObj.getJSONArray("photo");

            for(int i = 0; i< photosArray.length();i++)
            {
                JSONObject photoObj = photosArray.getJSONObject(i);
                String id = photoObj.getString("id");
                String owner = photoObj.getString("owner");
                String secret = photoObj.getString("secret");
                String server = photoObj.getString("server");
                String farm = photoObj.getString("farm");

                String flickerImageURL = "http://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret+".jpg";


                Constant.ITEMS.add(new SearchItems(flickerImageURL));
            }
            recyclerView.setAdapter(new CustomAdapter(FlickerGallery.this, Constant.ITEMS));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.out.println("onBackPressed");
        Constant.FLICKER_GALLERY = false;
        FlickerGallery.this.finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.out.println("onDestroy");
        Constant.FLICKER_GALLERY = false;
       /* Constant.FLICKER_GALLERY = false;
        FlickerGallery.this.finish();*/
    }
}
