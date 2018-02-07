package br.com.fischborn.classificados.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fischborn.classificados.R;
import br.com.fischborn.classificados.view.ItemView;

/**
 * Created by Karen on 22/01/2018.
 */

public class DetailActivity extends BaseActivity {

    public static final String ITEM_KEY = "ITEM_KEY";

    private TextView mTvTitle;
    private ImageView mIvImage;
    private TextView mTvDescription;
    private TextView mTvValue;
    private Button mBtLike;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        init();

        loadContent();
    }

    private void loadContent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(ITEM_KEY)) {
            ItemView item = (ItemView) intent.getSerializableExtra(ITEM_KEY);

            mTvTitle.setText(item.getTitle());
            mTvDescription.setText(item.getDescription());
            mTvValue.setText(getString(R.string.formatted_value, item.getValue()));
        }
    }

    private void init() {
        mTvTitle = findViewById(R.id.tv_title);
        mIvImage = findViewById(R.id.iv_image);
        mTvDescription = findViewById(R.id.tv_description);
        mTvValue = findViewById(R.id.tv_value);
        mBtLike = findViewById(R.id.bt_like);

        like();

        mIvImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_image_1));

        setToolbar(R.string.activity_item_title);
    }

    private void like() {
        mBtLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, R.string.bt_buy_clicked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btBuyClicked(View view) {
        Toast.makeText(DetailActivity.this, R.string.bt_buy_bought, Toast.LENGTH_SHORT).show();
    }
}
