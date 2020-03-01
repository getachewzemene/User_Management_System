package com.getch.user_management_system;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
public class UserInfoDatabas extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="users.db";
    public static final String TABLE_USERS="users";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_USERSFULNAME="fulname";
    public static final String COLUMN_USERSNAME="username";
    public static final String COLUMN_USERSEMAIL="email";
    public static final String COLUMN_USERPASSORD="pass";
    public static final String COLUMN_USERSPHONE="phone";
    public static final String COLUMN_USERSGENDER="gender";
    Context context;
    SQLiteDatabase userdb;
    public static final String CREATE_TABLE_USERS="CREATE TABLE "+TABLE_USERS+"("
         +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_USERSFULNAME+" TEXT,"
            +COLUMN_USERSNAME+" TEXT,"
            +COLUMN_USERSEMAIL+" TEXT,"
            +COLUMN_USERPASSORD+" TEXT,"
            +COLUMN_USERSPHONE+" TEXT,"
            +COLUMN_USERSGENDER+" TEXT);";
    public UserInfoDatabas(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Database Operations", "Database Created..");
        this.context=context;    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        Log.i("Database Operations", "Table Created..");
        // Toast.makeText(context, "Table Created",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }
    public void addUser(String fulname,String username,String email,String password,String phone,String gender){
        userdb=this.getWritableDatabase();
        ContentValues values=new ContentValues();
                values.put(COLUMN_USERSFULNAME, fulname);
                values.put(COLUMN_USERSNAME, username);
                values.put(COLUMN_USERSEMAIL, email);
                values.put(COLUMN_USERPASSORD, password);
                values.put(COLUMN_USERSPHONE, phone);
                values.put(COLUMN_USERSGENDER, gender);
                long flag=userdb.insert(TABLE_USERS, null, values);
                userdb.close();
        Log.i("Database Operations","One Raw added");
        Toast.makeText(context, "Data Saved Successfully", Toast.LENGTH_LONG).show();
    }
    public boolean deleteUser(String username){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from users where username="+username);
        db.close();
        return true;
    }
    public boolean checkUserLogin(String username,String password){
        String [] columns={COLUMN_ID};
        SQLiteDatabase userdb=this.getReadableDatabase();
        String selectUandP= COLUMN_USERSNAME+"=?"+" and "+COLUMN_USERPASSORD+"=?";
        String [] selctionArguments={username,password};
        Cursor cursor=userdb.query(TABLE_USERS, columns, selectUandP,
                selctionArguments, null, null, null);
        int counter=cursor.getCount();
        cursor.close();
        userdb.close();
        if(counter>0)
            return true;
        else
            return false;
    }
}
