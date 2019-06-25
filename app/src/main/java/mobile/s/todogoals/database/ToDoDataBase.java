package mobile.s.todogoals.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ToDoModelDB.class}, version = 1,exportSchema = false)
public abstract class ToDoDataBase extends RoomDatabase {
    public abstract ToDoDao toDoDao();
}
