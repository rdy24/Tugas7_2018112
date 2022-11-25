package com.example.healthcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.HealthViewHolder> {
    String data1[], data2[];
    int images[];
    NewsActivity context;

    public HealthAdapter(NewsActivity ct, String[] s1, String[] s2, int[] img){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public HealthAdapter.HealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_health, parent, false);
        return new HealthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthAdapter.HealthViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class HealthViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;
        LinearLayout mainLayout;
        public HealthViewHolder(View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView1);
            myText2 = itemView.findViewById(R.id.textView2);
            myImage = itemView.findViewById(R.id.imageView1);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
