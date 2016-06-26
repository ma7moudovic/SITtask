package com.sharkawy.sittask.adapters;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.sharkawy.sittask.R;

import java.io.File;
import java.util.List;


/**
 * Created by T on 4/26/2016.
 */
public class GallaryAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<String> mObjects;

    public GallaryAdapter(Context context, List<String> mObjects) {
        this.mContext = context;
        this.mObjects = mObjects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return mContext;
    }

    public void add(String object) {
        synchronized (object) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    public void clear() {
            mObjects.clear();

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public String getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_photo, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) view.getTag();

        Glide.with(getContext())
                .load(getItem(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);

//        viewHolder.nameView.setText(trailer.getName());

        return view;
    }

    public static class ViewHolder {
        public final ImageView imageView;
//        public final TextView nameView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.trailer_image);
//            nameView = (TextView) view.findViewById(R.id.trailer_name);
        }
    }

}
