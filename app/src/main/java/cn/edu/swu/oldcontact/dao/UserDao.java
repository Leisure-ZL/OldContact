package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.javaBean.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id = :id")
    User getUserById(int id);

    @Insert
    void insert(User item);

    @Delete
    void delete(User item);


    @Query("DELETE FROM User")
    void deleteAll();

}
