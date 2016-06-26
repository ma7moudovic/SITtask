package com.sharkawy.sittask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sharkawy.sittask.R;
import com.sharkawy.sittask.dataModel.CompanyModel;

import java.util.List;

/**
 * Created by T on 6/23/2016.
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder>{
    private final Context pContext;
    private final CompanyModel pLock = new CompanyModel();
    private List<CompanyModel> pObjects;


    public CompanyAdapter(Context pContext ,List<CompanyModel> pObjects) {
        this.pObjects = pObjects;
        this.pContext = pContext;
    }
    public CompanyModel getItem(int position){
        return pObjects.get(position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_row, parent, false);

        // create a new view
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleView.setText(pObjects.get(position).getNameen());

        Glide.with(pContext)
                .load(pObjects.get(position).getImagename())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageView;
        protected TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.company_image);
            titleView = (TextView) itemView.findViewById(R.id.company_name);
        }

    }//holder

}
