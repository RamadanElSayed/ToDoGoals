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
    void insertAzkarData(ToDoModelDB toDoModelDB);

    @Query("SELECT * FROM ToDoModelDB")
    Flowable<List<ToDoModelDB>> getAllToDoList();

    @Query("DELETE FROM ToDoModelDB WHERE todoId= :id")
    void deleteToDoById(Long id);

    @Query("SELECT COUNT(todoId) FROM ToDoModelDB where todoDone=0")
    Flowable<Integer> getUnDoneToDoCount();

    @Query("UPDATE ToDoModelDB SET todoDone= :isDone WHERE todoId = :id")
    void updateDutyState(int id, Boolean isDone);

}
