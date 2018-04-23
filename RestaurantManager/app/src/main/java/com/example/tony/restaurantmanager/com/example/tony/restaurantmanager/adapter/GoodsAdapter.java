package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tony.restaurantmanager.R;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Goods;

import java.util.List;

/**
 * Created by phanx on 02/01/2018.
 */

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Goods> goodsList;

    public GoodsAdapter(Context context, int layout, List<Goods> goodsList) {
        this.context = context;
        this.layout = layout;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        private ImageView imgGoods;
        private TextView txtGoodsInfo;
        private TextView txtGoodsPrice;
        private TextView txtGoodsDateInOut;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgGoods = view.findViewById(R.id.imgGoods);
            viewHolder.txtGoodsInfo = view.findViewById(R.id.txtGoodsInfo);
            viewHolder.txtGoodsPrice = view.findViewById(R.id.txtGoodsPrice);
            viewHolder.txtGoodsDateInOut = view.findViewById(R.id.txtGoodsDateInOut);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Goods goods = goodsList.get(position);
        if (goods.getGoodsImage() != null) {
            byte[] goodsImage_inBlob = goods.getGoodsImage();
            Bitmap goodsImage_inBitmap = BitmapFactory.decodeByteArray(goodsImage_inBlob, 0, goodsImage_inBlob.length);
            viewHolder.imgGoods.setImageBitmap(goodsImage_inBitmap);
        } else viewHolder.imgGoods.setImageResource(R.drawable.icon_buy_color);
        viewHolder.txtGoodsInfo.setText(goods.getGoodsName() + " " + goods.getGoodsQuantity());
        viewHolder.txtGoodsPrice.setText(goods.getGoodsPrice() + "/" + goods.getGoodsUnit());
        viewHolder.txtGoodsDateInOut.setText(goods.getGoodsReceiptedDate() + " - " + goods.getGoodsExpiredDate());
        return view;
    }
}
