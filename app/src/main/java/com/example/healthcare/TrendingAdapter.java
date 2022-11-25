package com.example.healthcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {
    String data1[];
    int images[];
    NewsActivity context;

    public TrendingAdapter(NewsActivity ct, String[] s1, int[] img){
        context = ct;
        data1 = s1;
        images = img;
    }

    @NonNull
    @Override
    public TrendingAdapter.TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_trending, parent, false);
        return new TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.TrendingViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {
        TextView myText1;
        ImageView myImage;
        LinearLayout mainLayout;
        public TrendingViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView1);
            myImage = itemView.findViewById(R.id.imageView1);
            mainLayout = itemView.findViewById(R.id.trendingLayout);
        }
    }
}

