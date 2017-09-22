package con.gionee.bloodsoulnote.recyitemdelete;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgz on 17-9-22.
 */

public class DBManager {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void addPersonInfo(Person person){
        database.beginTransaction();//开启事务
        try{
            database.execSQL("INSERT INTO person VALUES(NULL,?,?)",new Object[]{person.getName(),person.getMoney()});
            database.setTransactionSuccessful();//设置事务完成成功
        }finally {
            database.endTransaction();//设置结束事务
        }
    }

    public boolean deleteByPerson(Person person){
        int n = database.delete("person","name =  ?", new String[]{person.getName()});
        return n != -1;
    }

    /**
     * 拿到数据库中所有的Person并放入集合中
     * @return
     */
    public List<Person> getPersonListData(){
        List<Person> listData = new ArrayList<>();
        Cursor c = getAllPersonInfo();
        while (c.moveToNext()){
            Person person = new Person();
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setMoney(c.getString(c.getColumnIndex("money")));
            listData.add(person);
        }
        c.close();
        return listData;
    }

    private Cursor getAllPersonInfo(){
        Cursor c = database.rawQuery("SELECT * FROM person", null);
        return c;
    }

    /**
     * 关闭  database；
     */
    public void closeDB() {
        database.close();
    }

}
