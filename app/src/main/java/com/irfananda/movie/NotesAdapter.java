package com.irfananda.movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skday on 2/22/17.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private Context context;
    private List<MovieDao.ResultsBean> list = new ArrayList<>();

    public NotesAdapter(Context context, List<MovieDao.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvLanguage;
        ImageView ivImg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvLanguage = (TextView) itemView.findViewById(R.id.tv_language);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, final int position) {
        holder.tvName.setText(list.get(position).getTitle());
        holder.tvLanguage.setText(list.get(position).getRelease_date());
        Picasso.with(context)
                .load(Service.IMG_URL+list.get(position).getPoster_path())
                .resize(400,600)
                .into(holder.ivImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
