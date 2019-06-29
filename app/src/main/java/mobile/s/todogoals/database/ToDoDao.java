package mobile.s.todogoals.database;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import io.reactivex.Flowable;

@Dao
public interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToDoData(ToDoModelDB toDoModelDB);

    @Query("SELECT * FROM ToDoModelDB where todoDone=0")
    Flowable<List<ToDoModelDB>> getComingAllToDoList();

    @Query("SELECT * FROM ToDoModelDB where todoDone=1")
    Flowable<List<ToDoModelDB>> getFinishedAllToDoList();

    @Query("DELETE FROM ToDoModelDB WHERE todoId= :id")
    void deleteToDoById(int id);

    @Query("SELECT COUNT(todoId) FROM ToDoModelDB where todoDone=0")
    Flowable<Integer> getUnDoneToDoCount();

    @Query("UPDATE ToDoModelDB SET todoDone= :isDone WHERE todoId = :id")
    void updateDutyState(int id, Boolean isDone);

}
