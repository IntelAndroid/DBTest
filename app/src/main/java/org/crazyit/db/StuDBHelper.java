package org.crazyit.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 该类是SQLiteDatabase一个辅助类。这个类主要生成一个数据库，
 * 并对数据库的版本进行管理。当在程序当中调用这个类的方法getWritableDatabase()
 * 或者 getReadableDatabase()方法的时候，如果当时没有数据，那么Android系统就会自动生成一个数据库。
 * SQLiteOpenHelper 是一个抽象类，我们通常需要继承它，并且实现里面的3个函数：
 * Created by Android on 2016/6/16.
 */
//public class StuDBHelper extends SQLiteOpenHelper {
//
//
//    private static final String TAG = "TestSQLite";
//    public static final int VERSION = 1;
//
//    //必须要有构造函数
//    public StuDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//
//    /**
//     * 在数据库第一次生成的时候会调用这个方法，
//     * 也就是说，只有在创建数据库的时候才会调用，
//     * 当然也有一些其它的情况，一般我们在这个方法里边生成数据库表。
//     */
//    // 当第一次创建数据库的时候，调用该方法
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table stu_table(_id int,sname varchar(20),sage int,ssex varchar(10))";
//        //输出创建数据库的日志信息
//        Log.i(TAG, "create Database------------->");
//        //execSQL函数用于执行SQL语句
//        db.execSQL(sql);
//    }
//
//    /**
//     * 当数据库需要升级的时候，Android系统会主动的调用这个方法。
//     * 一般我们在这个方法里边删除数据表，并建立新的数据表，
//     * 当然是否还需要做其他的操作，完全取决于应用的需求。
//     */
//    //当更新数据库的时候执行该方法
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//       //输出更新数据库的日志信息
//        Log.i(TAG, "update Database------------->");
//    }
//
//    /**
//     * 这是当打开数据库时的回调函数，一般在程序中不是很常使用。
//     */
//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//    }
//}
