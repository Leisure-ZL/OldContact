package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.Group;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM `Group`")
    List<Group> getAll();

    @Query("SELECT * FROM `Group` WHERE id = :id")
    Group getGroupById(int id);

    @Insert
    void insert(Group item);

    @Delete
    void delete(Group item);


    @Query("DELETE FROM `Group`")
    void deleteAll();

}
