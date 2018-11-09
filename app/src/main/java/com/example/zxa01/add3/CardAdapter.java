package com.example.zxa01.add3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<Card> mCardData;
    private Context mContext;

    CardAdapter(Context context, ArrayList<Card> mCardData) {
        this.mCardData = mCardData;
        this.mContext = context;
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        holder.bindTo(mCardData.get(position));
    }

    @Override
    public int getItemCount() {
        return mCardData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTitleText;
        private TextView mInfoText;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.cardTextTitle);
            mInfoText = (TextView) itemView.findViewById(R.id.cardTextInfo);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);

        }

        void bindTo(Card currentCard) {
            mTitleText.setText(currentCard.getTitle());
            mInfoText.setText(currentCard.getInfo());
            mImageView.setImageResource(currentCard.getImageResource());
        }
    }
}
