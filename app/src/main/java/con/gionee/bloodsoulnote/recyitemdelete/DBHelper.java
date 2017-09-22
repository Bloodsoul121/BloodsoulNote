package con.gionee.bloodsoulnote.recyitemdelete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cgz on 17-9-22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DEFAULT_NAME = "app_1_person.db";
    private static final int DEFAULT_VERSION = 1;

    public DBHelper(Context context) {
        super(context,DEFAULT_NAME, null, DEFAULT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS person(_id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(20) , money TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            //TODO
        }
    }
}
