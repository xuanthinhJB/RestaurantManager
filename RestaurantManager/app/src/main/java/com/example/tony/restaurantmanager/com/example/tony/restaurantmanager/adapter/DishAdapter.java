package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.adapter;

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
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by phanx on 10/01/2018.
 */

public class DishAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Dishes> dishList;

    public DishAdapter(Context context, int layout, List<Dishes> dishList) {
        this.context = context;
        this.layout = layout;
        this.dishList = dishList;
    }

    @Override
    public int getCount() {
        return dishList.size();
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
        private ImageView imgDish;
        private TextView txtDishName;
        private TextView txtDishPrice;
        private TextView txtDishUnit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.imgDish = convertView.findViewById(R.id.imgDish);
            viewHolder.txtDishName = convertView.findViewById(R.id.txtDishName);
            viewHolder.txtDishUnit = convertView.findViewById(R.id.txtDishUnit);
            viewHolder.txtDishPrice = convertView.findViewById(R.id.txtDishPrice);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();
        
        Dishes dish = dishList.get(position);
        if (dish.getDishImage() != null) {
            byte[] dishImage_inBlob = dish.getDishImage();
            Bitmap dishImage_inBitmap = BitmapFactory.decodeByteArray(dishImage_inBlob, 100, dishImage_inBlob.length);
            viewHolder.imgDish.setImageBitmap(dishImage_inBitmap);
        } else viewHolder.imgDish.setImageResource(R.drawable.icon_meal_color);
        viewHolder.txtDishName.setText(dish.getDishName());
        viewHolder.txtDishUnit.setText(dish.getDishUnit());
        viewHolder.txtDishPrice.setText((int) dish.getDishPrice());
        return convertView;
    }
}
