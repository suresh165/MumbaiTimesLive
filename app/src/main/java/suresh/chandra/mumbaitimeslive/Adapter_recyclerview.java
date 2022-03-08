package suresh.chandra.mumbaitimeslive;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import suresh.chandra.mumbaitimeslive.Activitys.Post_Activity;

public class Adapter_recyclerview extends RecyclerView.Adapter<Adapter_recyclerview.HolderView> {
    List<WP_post> datalist;
    Context context;

    public Adapter_recyclerview(List<WP_post> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_design,parent,false);
        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        WP_post model = datalist.get(position);
        holder.title.setText(model.getTitle().getRendered());
        Picasso.get().load(model.getJetpackFeaturedMediaUrl()).into(holder.images);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String data = simpleDateFormat.format(new Date());
        holder.date.setText(data);
        // Log.i("date",data);
        //redirect to information post activity
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),Post_Activity.class);
                i.putExtra("title",model.getTitle().getRendered());
                i.putExtra("content",model.getContent().getRendered());
                i.putExtra("date",model.getDate());
                i.putExtra("comment",model.getCommentCount());
                i.putExtra("image",model.getJetpackFeaturedMediaUrl());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        TextView title,date;
        ImageView img_bookmark,img_share,images;
        View view;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            img_bookmark = itemView.findViewById(R.id.img_bookmark);
            img_share = itemView.findViewById(R.id.img_share);
            images = itemView.findViewById(R.id.images);
        }
    }
}
