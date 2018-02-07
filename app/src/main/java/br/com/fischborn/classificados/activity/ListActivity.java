package br.com.fischborn.classificados.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.fischborn.classificados.R;
import br.com.fischborn.classificados.adapter.ListAdapter;
import br.com.fischborn.classificados.util.AnimUtil;
import br.com.fischborn.classificados.view.Category;
import br.com.fischborn.classificados.view.ItemView;

import static br.com.fischborn.classificados.activity.FilterActivity.CATEGORY_KEY;

/**
 * Created by Karen on 23/01/2018.
 */

public class ListActivity extends BaseActivity {

    private final int LIST_ACTIVITY_REQUEST_CODE = 0;

    private RecyclerView mRvList;
    private Button mBtFilter;
    private FloatingActionButton mFabAdd;
    private SwipeRefreshLayout mSwlRefresh;
    private ProgressBar mSpinner;

    private List<ItemView> mList;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        loadContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.show_snack) {
            Snackbar snackbar = Snackbar.make(mRvList, R.string.show_snack,
                    Snackbar.LENGTH_SHORT);

            snackbar.setAction(R.string.bt_ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });
            snackbar.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ListActivity.this);
        dialog.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_msg)
                .setPositiveButton(R.string.bt_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ListActivity.this, R.string.ok_clicked, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.bt_cancel, null)
                .show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LIST_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK
                && data != null && data.hasExtra(CATEGORY_KEY)) {
            Category category = (Category) data.getSerializableExtra(CATEGORY_KEY);

            Toast.makeText(ListActivity.this, category.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        mRvList = findViewById(R.id.rv_list);
        mBtFilter = findViewById(R.id.bt_filter);
        mFabAdd = findViewById(R.id.fab_add);
        mSwlRefresh = findViewById(R.id.swl_refresh);
        mSpinner = findViewById(R.id.spinner);

        setToolbar(R.string.list_title);
    }

    private void loadContent() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new ItemView(i, getString(R.string.item_title, i),

                    getString(R.string.item_description, i), i * 10, null));
        }

        mAdapter = new ListAdapter(ListActivity.this, mList);
        mRvList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        mRvList.setAdapter(mAdapter);

        AnimUtil.replaceView(ListActivity.this, mSpinner, mRvList);

        mBtFilter.setOnClickListener(new View.OnClickListener() {
            public static final int LIST_ACTIVITY_REQUEST_CODE = 0;

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ListActivity.this, FilterActivity.class), LIST_ACTIVITY_REQUEST_CODE);
            }
        });

        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        mSwlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AnimUtil.replaceView(ListActivity.this, mRvList, mSpinner);
                mSwlRefresh.setRefreshing(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mList.add(0, new ItemView(0, "New Item",
                                "Item created on refresh", 10, null));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyItemInserted(0);
                                AnimUtil.replaceView(ListActivity.this, mSpinner, mRvList);
                            }
                        });
                    }
                }).start();
            }
        });
    }

}
