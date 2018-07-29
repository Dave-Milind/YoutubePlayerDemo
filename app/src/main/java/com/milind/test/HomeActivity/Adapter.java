package com.milind.test.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.milind.test.Details.DetailsActivity;
import com.milind.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    List<YoutubeModel.Item> itemArrayList;

    public Adapter(Context context, List<YoutubeModel.Item> list) {
        this.context = context;
        itemArrayList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_youtube, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        YoutubeModel.Item item = itemArrayList.get(position);
        YoutubeModel.Snippet snippet = item.getSnippet();
        YoutubeModel.Thumbnails thumbnails = snippet.getThumbnails();
        holder.textViewTitle.setText(snippet.getTitle());

        Picasso.get().load(thumbnails.getHigh().getUrl()).into(holder.imageView);
        YoutubeModel.Id id=item.getId();
        final String videoId=id.getVideoId();
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailsActivity.class);
                intent.putExtra("videoId",videoId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
     ImageView btnComments;
     ImageView btnLikes;
        TextView textViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgview_youtube);
            btnLikes = (ImageView) itemView.findViewById(R.id.btn_youtube_likes);
            btnComments = (ImageView) itemView.findViewById(R.id.btn_youtube_comments);
            textViewTitle = (TextView) itemView.findViewById(R.id.textview_youtube);

        }
    }
}