package org.crazyit.db;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity {
    SQLiteDatabase db;
    Button bn = null;
    ListView listView;
    EditText data;
    Button deletedata;
    Button qureydata;
    Button updata;
    private String mToString;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        data = (EditText) findViewById(R.id.data_up);

        deletedata = (Button) findViewById(R.id.delete_data);
        qureydata = (Button) findViewById(R.id.query_data);
        updata = (Button) findViewById(R.id.up_data);

        updata.setOnClickListener(new dataclick());
        qureydata.setOnClickListener(new dataclick());
        deletedata.setOnClickListener(new dataclick());


        // 创建或打开数据库（此处需要使用绝对路径）
        db = SQLiteDatabase.openOrCreateDatabase(
                this.getFilesDir().toString()
                        + "/my.db3", null); // ①
        listView = (ListView) findViewById(R.id.show);
        bn = (Button) findViewById(R.id.ok);
        bn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View source) {

                // 获取用户输入
                String title = ((EditText) findViewById(
                        R.id.title)).getText().toString();
                String content = ((EditText) findViewById(R.id.content))
                        .getText().toString();
                try {
                    insertData(db, title, content);
                    Cursor cursor = db.rawQuery("select * from news_inf"
                            , null);
                    inflateList(cursor);
                } catch (SQLiteException se) {
                    // 执行DDL创建数据表
                    db.execSQL("create table news_inf(_id integer"
                            + " primary key autoincrement,"
                            + " news_title varchar(50),"
                            + " news_content varchar(255))");
                    // 执行insert语句插入数据
                    insertData(db, title, content);
                    // 执行查询
                    Cursor cursor = db.rawQuery("select * from news_inf"
                            , null);
                    Log.d(TAG, "onClick: -------------->" + db);
                    inflateList(cursor);
                }
            }
        });
    }

    private void insertData(SQLiteDatabase db
            , String title, String content)  // ②
    {
        // 执行插入语句
        db.execSQL("insert into news_inf values(null , ? , ?)"
                , new String[]{title, content});
    }

    /**
     * 要在MainActivity中执行删除，插入数据正常，删除不运行 不报错 ，刷新View已处理
     *
     * @param db
     */
    private void deleteData(SQLiteDatabase db) {
        mToString = data.getText().toString();


        //执行查找并刷洗
        Cursor cursor = db.rawQuery("select * from news_inf"
                , null);
        Log.d(TAG, "onClick: -------------->" + db);
        //判断游标是否为空
        if (cursor.moveToFirst()) {
            //遍历游标
            for (int i = 0; i < cursor.getCount(); i++) {
cursor.move(i);
                //删除条件
                String whereClause = "_id=?";
                //删除条件参数
                String[] whereArgs = {String.valueOf(mToString)};
                Log.d(TAG, "deleteData:-------------> " + mToString);
                //删除SQL语句
//        String sql = "delete from news_inf where _id = 5";
//        db.execSQL(sql);
                //执行删除
                db.delete("news_inf", whereClause, whereArgs);
            }
        }
        inflateList(cursor);
    }


    private void inflateList(Cursor cursor) {
        // 填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.line, cursor,
                new String[]{"news_title", "news_content"}
                , new int[]{R.id.my_title, R.id.my_content},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  // ③

        // 显示数据
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private class dataclick implements OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.delete_data:
                    Log.d(TAG, "onClick: ---------------------->" + db);
                    deleteData(db);
                    break;
                case R.id.query_data:

                    break;
                case R.id.up_data:

                    break;
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出程序时关闭SQLiteDatabase
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}


