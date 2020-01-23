package com.test.flickersearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.test.flickersearch.items.SearchItems;

import java.util.ArrayList;
import java.util.List;

public class Constant
{
    public static String API_KEY = "96358825614a5d3b1a1c3fd87fca2b47";
    public static String searchStr;
    public static String requestURI = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+API_KEY+"&text=";

    public static  int page =1;

    public static String formatType = "&format=json&nojsoncallback=";
    public static boolean FLICKER_GALLERY = false;


    public Context context;
    public static List<SearchItems> ITEMS = new ArrayList<SearchItems>();

    public Constant(Context context)
    {
        this.context = context;
    }

    public boolean isNetworkAvailable()
    {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
