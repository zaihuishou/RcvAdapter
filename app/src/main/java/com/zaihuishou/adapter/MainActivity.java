package com.zaihuishou.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zaihuishou.rcvadapter.AdapterItem;
import com.zaihuishou.rcvadapter.DividerItemDecoration;
import com.zaihuishou.rcvadapter.RcvAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Model> mDatas = new ArrayList<>();
    private RcvAdapter<Model> mRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRcvView();
        for (int i = 0; i < 10; i++) {
            Model model = new Model();
            model.setName("RcvAdapter:" + i);
            model.setType(i % 2);
            mDatas.add(model);
        }
        mRcvAdapter.notifyDataSetChanged();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initRcvView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider_1dp)));
        mRcvAdapter = new RcvAdapter<Model>(mDatas) {
            @NonNull
            @Override
            public AdapterItem<Model> getItemView(Object type) {

                if (type instanceof Integer) {
                    int mType = (Integer) type;
                    if (mType == 0)
                        return new LeftItem();
                    else
                        return new RightItem();
                }
                return null;
            }

            @Override
            public Object getItemViewType(Model model) {
                return model.getType();
            }
        };
        mRecyclerView.setAdapter(mRcvAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
