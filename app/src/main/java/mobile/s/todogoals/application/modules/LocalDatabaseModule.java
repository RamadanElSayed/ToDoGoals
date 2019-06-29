package mobile.s.todogoals.application.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mobile.s.todogoals.application.ApplicationScope;
import mobile.s.todogoals.database.ToDoDao;
import mobile.s.todogoals.database.ToDoDataBase;

@Module(includes = {ContextModule.class})
public class LocalDatabaseModule {

    @ApplicationScope
    @Provides
    ToDoDataBase getToDoDatabaseApp(Context context)
    {
        return  Room.databaseBuilder(context,ToDoDataBase.class, "todolist_db").build();
    }

    @ApplicationScope
    @Provides
    ToDoDao getToDoDoa(ToDoDataBase toDoDataBase)
    {
        return toDoDataBase.toDoDao();
    }

}
