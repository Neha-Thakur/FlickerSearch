package com.test.flickersearch;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/*import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;*/
import com.squareup.picasso.Picasso;
import com.test.flickersearch.items.SearchItems;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    List<SearchItems> searchList;
    Context context;

    public CustomAdapter(Context context, List<SearchItems> list)
    {
        System.out.println("in CustomAdapter");
        this.context = context;
        this.searchList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        searchList.get(position);

        // download images
       String img_url = searchList.get( position ).flickerImageURL;

      // Glide.with( context ).load( img_url ).into( holder.img1 );
        Picasso.get().load(img_url).into(holder.img1);

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img1 = null;

        public ViewHolder(View itemView)
        {
            super(itemView);
            img1 = (ImageView) itemView.findViewById(R.id.img1);

        }
    }
}
