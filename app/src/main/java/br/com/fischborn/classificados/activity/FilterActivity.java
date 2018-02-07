package br.com.fischborn.classificados.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.fischborn.classificados.R;
import br.com.fischborn.classificados.view.Category;

/**
 * Created by Karen on 24/01/2018.
 */

public class FilterActivity extends BaseActivity {

    public static final String CATEGORY_KEY = "CATEGORY_KEY";
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private Spinner mSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_filter);

        init();

        loadContent();
    }

    private void loadContent() {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            categories.add(new Category(i, getString(R.string.category_name, i)));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(FilterActivity.this,
                android.R.layout.simple_spinner_dropdown_item, categories);

        mSpinner.setAdapter(arrayAdapter);

        int categoryId = getSharedPreferences().getInt(CATEGORY_ID, 0);

        for (int i = 0; i < categories.size(); i++) {
            if (i == categoryId) {
                mSpinner.setSelection(i);
                return;
            }
        }
    }

    private void init() {
        mSpinner = findViewById(R.id.sp_filter);

        setToolbar(R.string.filter_title);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(CATEGORY_KEY, (Category) mSpinner.getSelectedItem());
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Category category = (Category) mSpinner.getSelectedItem();

        getSharedPreferences().edit().putInt(CATEGORY_ID, category.getId()).apply();

        super.onDestroy();
    }
}
