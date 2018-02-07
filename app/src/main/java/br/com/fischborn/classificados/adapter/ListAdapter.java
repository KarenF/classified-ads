package br.com.fischborn.classificados.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.fischborn.classificados.R;
import br.com.fischborn.classificados.activity.DetailActivity;
import br.com.fischborn.classificados.view.ItemView;

import static br.com.fischborn.classificados.activity.DetailActivity.ITEM_KEY;

/**
 * Created by Karen on 23/01/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    private Context mContext;
    private List<ItemView> mViewList;

    public ListAdapter(Context context, List<ItemView> viewList) {
        mContext = context;
        mViewList = viewList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final ItemView item = mViewList.get(position);

        holder.mTvTitle.setText(item.getTitle());
        holder.mTvDescription.setText(item.getDescription());
        holder.mTvValue.setText
                (mContext.getString(R.string.formatted_value, item.getValue()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private TextView mTvDescription;
        private TextView mTvValue;

        public ItemHolder(View itemView) {
            super(itemView);

            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mTvValue = itemView.findViewById(R.id.tv_value);
        }
    }
}
