package br.com.myapp.classifiedads.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.myapp.classifiedads.R;

/**
 * Created by aurora on 24/01/18.
 */

public class DetailActivity extends Activity {

    private TextView mTvTitle;
    private ImageView mIvImage;
    private Button mBtLike;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        
        init();
    }

    private void init() {
        mTvTitle = findViewById(R.id.tv_title);
        mIvImage = findViewById(R.id.iv_image);
        mBtLike = findViewById(R.id.bt_like);
        
        like();
    }

    private void like() {
        mBtLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, R.string.bt_like_clicked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
