package com.jash.recyclerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-4
 * Time: 下午2:27
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder>{
    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            text = ((TextView) itemView.findViewById(R.id.item_text));
        }
    }
}
