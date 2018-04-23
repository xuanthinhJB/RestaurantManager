package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.helper_classes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Dishes;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.Goods;
import com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models.TableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by phanx on 19/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TableManager";

    private static final String TABLE_TABLES = "Tables";
    private static final String KEY_TABLE_ID = "id";
    private static final String KEY_TABLE_NAME = "table_name";
    private static final String KEY_NUMBER_OF_CUSTOMER = "number_of_customer";
    private static final String KEY_DISHES_ON_TABLE = "dishes_on_table";
    private static final String KEY_TIME_IN = "table_time_in";
    private static final String KEY_TIME_OUT = "table_time_out";
    private static final String KEY_TABLE_ACTIVE = "table_active";

    private static final String GOODS = "Goods";
    private static final String KEY_GOODS_ID = "goods_id";
    private static final String KEY_GOODS_IMAGE = "goods_image";
    private static final String KEY_GOODS_NAME = "goods_name";
    private static final String KEY_GOODS_PRICE = "goods_price";
    private static final String KEY_GOODS_QUANTITY = "goods_quantity";
    private static final String KEY_GOODS_UNIT = "goods_unit";
    private static final String KEY_GOODS_RECEIPT_DATE = "goods_receipt_date";
    private static final String KEY_GOODS_EXPIRED_DATE = "goods_expired_date";

    private static final String DISHES = "Dishes";
    private static final String KEY_DISHES_ID = "dishes_id";
    private static final String KEY_DISHES_IMAGE = "dishes_image";
    private static final String KEY_DISHES_NAME = "dishes_name";
    private static final String KEY_DISHES_PRICE = "dishes_price";
    private static final String KEY_DISHES_UNIT = "dishes_unit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLES_TABLE = "CREATE TABLE " + TABLE_TABLES + "(" + KEY_TABLE_ID + " INTEGER PRIMARY KEY," + KEY_TABLE_NAME + " TEXT,"
                + KEY_TABLE_ACTIVE + " BOOLEAN," + KEY_NUMBER_OF_CUSTOMER + " INT," + KEY_DISHES_ON_TABLE + " TEXT," + KEY_TIME_IN + " LONG,"
                + KEY_TIME_OUT + " LONG" + ")";

        String CREATE_GOODS_TABLE = "CREATE TABLE " + GOODS + "(" + KEY_GOODS_ID + " INTEGER PRIMARY KEY," + KEY_GOODS_IMAGE + " BLOB,"
                + KEY_GOODS_NAME + " TEXT," + KEY_GOODS_QUANTITY + " INT," + KEY_GOODS_PRICE + " LONG," + KEY_GOODS_UNIT + " TEXT,"
                + KEY_GOODS_RECEIPT_DATE + " LONG," + KEY_GOODS_EXPIRED_DATE + " LONG" + ")";

        String CREATE_DISHES_TABLE = "CREATE TABLE " + DISHES + "(" + KEY_DISHES_ID + " INTEGER PRIMARY KEY," + KEY_DISHES_IMAGE + " BLOB," + KEY_DISHES_NAME + " TEXT,"
                + KEY_DISHES_PRICE + " LONG," + KEY_DISHES_UNIT + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLES_TABLE);
        sqLiteDatabase.execSQL(CREATE_GOODS_TABLE);
        sqLiteDatabase.execSQL(CREATE_DISHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GOODS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DISHES);

        onCreate(sqLiteDatabase);
    }

    public void addTable(TableModel table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TABLE_NAME,  table.getTableName());
        values.put(KEY_NUMBER_OF_CUSTOMER, table.getTableNumberOfPeople());
        values.put(KEY_DISHES_ON_TABLE, table.getTableDishes()); // check if we can put an array to database
        values.put(KEY_TIME_IN, table.getTableTimeIn());
        values.put(KEY_TIME_OUT, table.getTableTimeOut());

        db.insert(TABLE_TABLES, null, values);
        db.close();
    }

    public TableModel getTable(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TABLES, new String[]{KEY_TABLE_ID, KEY_TABLE_NAME, KEY_DISHES_ON_TABLE, KEY_NUMBER_OF_CUSTOMER, KEY_TIME_IN, KEY_TIME_OUT}
                        ,KEY_TABLE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null );

        TableModel table = null;
        if (cursor != null) {
            cursor.moveToFirst();

            table = new TableModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3),
                    cursor.getLong(4), cursor.getLong(5), cursor.getInt(6) == 1);
        }
        db.close();
        return table;
    }

    public List<TableModel> getAllTables() {
        List<TableModel> tableList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TABLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TableModel table = new TableModel();
                table.setTableID(Integer.parseInt(cursor.getString(0)));
                table.setTableName(cursor.getString(1));
                table.setTableActive(cursor.getInt(2) == 1);
                table.setTableNumberOfPeople(cursor.getInt(3));
                table.setTableDishes(cursor.getString(4));
                table.setTableTimeIn(cursor.getLong(5));
                table.setTableTimeOut(cursor.getLong(6));


                tableList.add(table);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return tableList;
    }

    public int updateTable(TableModel table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_ID, table.getTableID());
        values.put(KEY_TABLE_NAME,  table.getTableName());
        values.put(KEY_TABLE_ACTIVE, table.isTableActive());
        values.put(KEY_NUMBER_OF_CUSTOMER, table.getTableNumberOfPeople());
        values.put(KEY_DISHES_ON_TABLE, table.getTableDishes()); // check if we can put an array to database
        values.put(KEY_TIME_IN, table.getTableTimeIn());
        values.put(KEY_TIME_OUT, table.getTableTimeOut());

        return db.update(TABLE_TABLES, values, KEY_TABLE_ID + "=?", new String[]{table.getTableID() + ""});
        //db.update(TABLE_TABLES, values, KEY_TABLE_ID + "=" + table.getTableID(), null);
    }

    public void deleteTable(TableModel table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TABLES, KEY_TABLE_ID + "=?", new String[]{String.valueOf(table.getTableID())});
    }

    public int getTableCount() {
        String countQuery = "SELECT * FROM " + TABLE_TABLES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


    public void addDishes(Dishes dish) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DISHES_IMAGE, dish.getDishImage());
        values.put(KEY_DISHES_NAME, dish.getDishName());
        values.put(KEY_DISHES_PRICE, dish.getDishPrice());
        values.put(KEY_DISHES_UNIT, dish.getDishUnit());

        db.insert(DISHES, null, values);
        db.close();
    }

    public void deleteDishes(Dishes dish) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DISHES, KEY_DISHES_NAME + "=?", new String[]{String.valueOf(dish.getDishName())});
    }

    public List<Dishes> getAllDishes() {
        List<Dishes> dishList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DISHES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Dishes dish = new Dishes();
                dish.setDishImage(cursor.getBlob(0));
                dish.setDishName(cursor.getString(1));
                dish.setDishPrice(cursor.getLong(2));
                dish.setDishUnit(cursor.getString(3));

                dishList.add(dish);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return dishList;
    }

    public void addGoods(Goods goods) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GOODS_IMAGE, goods.getGoodsImage());
        values.put(KEY_GOODS_NAME, goods.getGoodsName());
        values.put(KEY_GOODS_QUANTITY, goods.getGoodsQuantity());
        values.put(KEY_GOODS_PRICE, goods.getGoodsPrice());
        values.put(KEY_DISHES_UNIT, goods.getGoodsUnit());

        long dateRCP_inLong = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat1.parse(goods.getGoodsExpiredDate());
            dateRCP_inLong = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        values.put(KEY_GOODS_RECEIPT_DATE, dateRCP_inLong);

        long dateEXP_inLong = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat2.parse(goods.getGoodsExpiredDate());
            dateEXP_inLong = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        values.put(KEY_GOODS_EXPIRED_DATE, dateEXP_inLong);

        db.insert(GOODS, null, values);
        db.close();
    }

    public void deleteGoods(Goods goods){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(GOODS, KEY_GOODS_NAME + "=?", new String[]{String.valueOf(goods.getGoodsName())});
    }

    public List<Goods> getAllGoods() {
        List<Goods> goodsList = new ArrayList<>();
        String selection = "SELECT * FROM " + GOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selection, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Goods goods = new Goods();
                goods.setGoodsImage(cursor.getBlob(1));
                goods.setGoodsName(cursor.getString(2));
                goods.setGoodsQuantity(cursor.getFloat(3));
                goods.setGoodsPrice(cursor.getLong(4));
                goods.setGoodsUnit(cursor.getString(5));

                @SuppressLint("SimpleDateFormat") String dateRCP_inString = new SimpleDateFormat("dd-MM-yyyy").format(new Date(cursor.getLong(6)));
                goods.setGoodsReceiptedDate(dateRCP_inString);

                @SuppressLint("SimpleDateFormat") String dateEXP_inString = new SimpleDateFormat("dd-MM-yyyy").format(new Date(cursor.getLong(7)));
                goods.setGoodsExpiredDate(dateEXP_inString);

                goodsList.add(goods);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return goodsList;
    }
}
