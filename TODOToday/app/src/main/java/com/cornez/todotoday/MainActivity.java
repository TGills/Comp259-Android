package com.cornez.todotoday;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EXPERIMENT 1: CREATE THE DATABASE

        DBHelper database = new DBHelper(this);

        //              ADD FIVE TASK ITEMS TO THE DATABASE
        database.addToDoItem(new ToDo_Item(
                1, "Read Hamlet", 1));
        database.addToDoItem(new ToDo_Item(
                2, "Study for exam", 1));
        database.addToDoItem(new ToDo_Item(
                3, "Call Andy and Sam", 0));
        database.addToDoItem(new ToDo_Item(
                4, "Create newsletter", 1));
        database.addToDoItem(new ToDo_Item(
                5, "Buy a dog", 0));

        //            DISPLAY ALL THE TASK ITEMS IN THE TABLE
        String taskItemList = "\n";
        ArrayList<ToDo_Item> taskList = database.getAllTaskItems();
        for (int i = 0; i < database.getTaskCount(); i++) {
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" +
                    task.getIs_done();
        }
        Log.v("DATABASE RECORDS", taskItemList);

        // EXPERIMENT 2: MODIFY A RECORD
        database.editTaskItem(new ToDo_Item(
                1, "Read newspaper", 1));

        //EXPERIMENT 3: DISPLAY A SPECIFIC RECORD
        ToDo_Item anItem = database.getToDo_Task(2);
        Log.v("DATABASE RECORDS", anItem.getDescription());

        //EXPERIMENT 4: DELETE A RECORD
        database.deleteTaskItem(new ToDo_Item(
                15, "Buy a dog", 0));

        //              DISPLAY ALL THE TASK ITEMS IN THE TABLE
        taskItemList = "\n";
        taskList = database.getAllTaskItems();
        for (int i = 0; i < database.getTaskCount(); i++) {
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" +
                    task.getIs_done();
        }
        Log.v("DATABASE RECORDS", taskItemList);
    }

}
