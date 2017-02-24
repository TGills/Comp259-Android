package com.cornez.todotoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "toDo_Today";
    private static final String DATABASE_TABLE = "toDo_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String KEY_TASK_ID = "_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IS_DONE = "is_done";

    private int taskCount;

    public DBHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_TASK_ID + " INTEGER PRIMARY KEY, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_IS_DONE + " INTEGER" + ")";
        database.execSQL (table);
        taskCount = 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //********** DATABASE OPERATIONS:  ADD, EDIT, DELETE


    public void addToDoItem(ToDo_Item task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        taskCount++;
        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(KEY_TASK_ID, taskCount);

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(KEY_DESCRIPTION, task.getDescription()); // task name

        //ADD KEY-VALUE PAIR INFORMATION FOR IS_DONE
        //  0- NOT DONE, 1 - IS DONE
        values.put(KEY_IS_DONE, task.getIs_done());

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public void editTaskItem(ToDo_Item task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_IS_DONE, task.getIs_done());

        db.update(DATABASE_TABLE, values, KEY_TASK_ID + " = ?",
                new String[]{
                        String.valueOf(task.getId())
                });
        db.close();
    }

    public ToDo_Item getToDo_Task(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{KEY_TASK_ID, KEY_DESCRIPTION, KEY_IS_DONE},
                KEY_TASK_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();

        ToDo_Item task = new ToDo_Item(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2));
        db.close();
        return task;
    }

    public void deleteTaskItem (ToDo_Item task){
        SQLiteDatabase database = this.getReadableDatabase();

        // DELETE THE TABLE ROW
        database.delete(DATABASE_TABLE, KEY_TASK_ID + " = ?",
                new String[]
                        {String.valueOf(task.getId())});
        database.close();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<ToDo_Item> getAllTaskItems() {
        ArrayList<ToDo_Item> taskList = new ArrayList<ToDo_Item>();
        String queryList = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(queryList, null);

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                ToDo_Item task = new ToDo_Item();
                task.setId(cursor.getInt(0));
                task.setDescription(cursor.getString(1));
                task.setIs_done(cursor.getInt(2));

                //ADD TO THE QUERY LIST
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }
}
