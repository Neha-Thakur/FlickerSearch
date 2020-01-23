package com.test.flickersearch.items;

public class SearchItems
{
    public String flickerImageURL;

    public SearchItems(String flickerImageURL)
    {
       this.flickerImageURL = flickerImageURL;
    }

     @Override
    public String toString() {
        return flickerImageURL;
    }
}
