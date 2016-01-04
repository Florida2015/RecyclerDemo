package com.jash.recyclerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

/**
 * Created by jash
 * Date: 16-1-4
 * Time: 下午3:52
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> list;
    private OnChildClickListener listener;
    private RecyclerView recyclerView;

    public ImageAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setListener(OnChildClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        //设置点击事件
        view.setOnClickListener(this);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open("images/" + list.get(position)));
            holder.image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取到当前的RecyclerView
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            //获取到点击View在RecyclerView中的位置
            int position = recyclerView.getChildAdapterPosition(v);
            //调用接口
            listener.onChildClick(v, position, list.get(position));
        }
    }
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void add(int position, String data){
        list.add(position, data);
        notifyItemInserted(position);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.item_image));
        }
    }
    //自定义监听接口
    public interface OnChildClickListener{
        void onChildClick(View v, int position, String data);
    }
}
