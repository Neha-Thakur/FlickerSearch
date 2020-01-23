package com.test.flickersearch;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetSearchList extends AsyncTask
{
    String result = null;
    Context context;
    ProgressBar pBar;
    RecyclerView recyclerView;

    public GetSearchList(ProgressBar pBar, Context context)
    {
        this.context = context;
        this.pBar = pBar;
    }

    public GetSearchList(ProgressBar pBar, Context context, RecyclerView recyclerView)
    {
        this.recyclerView =recyclerView;
        this.context = context;
        this.pBar = pBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pBar.setVisibility( View.VISIBLE );
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        try
        {
            String URI = Constant.requestURI + Constant.searchStr + Constant.formatType + Constant.page;
            System.out.println("URL 1 is: "+URI);
            OkHttpClient httpClient = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(URI).newBuilder();
            String URL = urlBuilder.build().toString();

            System.out.println("URL 2 is: "+URL);

            Request request = new Request.Builder()
                    .url(URL).build();

            Response response = httpClient.newCall(request).execute();
            result = response.body().string().toString();

            System.out.println("result is: "+result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        pBar.setVisibility( View.GONE );

        System.out.println("post execute:  "+o.toString());

        if(Constant.FLICKER_GALLERY)
        {
            System.out.println("no start new activity");
            Constant.ITEMS.clear();
            FlickerGallery flickerGallery = new FlickerGallery();

            flickerGallery.parseJsonObj(o.toString(), recyclerView);
        }
        else
        {
            System.out.println("start new activity");
            context.startActivity(new Intent(context, FlickerGallery.class)
                    .putExtra("searchOutput", o.toString()));
        }
    }
}
