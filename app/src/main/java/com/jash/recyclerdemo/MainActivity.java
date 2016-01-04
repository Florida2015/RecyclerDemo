package com.jash.recyclerdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnChildClickListener {

    private RecyclerView recycler;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("第%03d条数据%s", i, i % 2 == 0 ? "" : "--------------------------------"));
        }
        recycler.setAdapter(new MyAdapter(this, list));
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            /**
             * Item的偏移量
             * @param outRect
             * @param view
             * @param parent
             * @param state
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(2, 2, 2, 2);
            }

            /**
             * 在绘制Item之前调用,绘制在item的下层
             * @param c
             * @param parent
             * @param state
             */
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.RED);
            }

            /**
             * 在绘制Item之后调用,绘制在item的上层
             * @param c
             * @param parent
             * @param state
             */
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
//                c.drawColor(0x8800ff00);
            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0){
                    return 3;
                }
                return 1;
            }
        });
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        try {
            String[] strings = getAssets().list("images");
            adapter = new ImageAdapter(this, new ArrayList<String>(Arrays.asList(strings)));
            adapter.setListener(this);
            recycler.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setRemoveDuration(2000);
        recycler.setItemAnimator(animator);
    }

    @Override
    public void onChildClick(View v, int position, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
//        adapter.remove(position);
        adapter.add(position + 1, data);
    }
}
