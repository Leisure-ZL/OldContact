package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.LifeItem;

@Dao
public interface LifeDao {

    @Query("SELECT * FROM LifeItem")
    List<LifeItem> getAll();

    @Query("SELECT * FROM LifeItem WHERE id = :lifeId")
    LifeItem getItemById(int lifeId);

    @Query("SELECT * FROM Lifeitem WHERE `index` = :index")
    List<LifeItem> getItemByIndex(int index);

    @Insert
    void insert(LifeItem item);

    @Delete
    void delete(LifeItem item);


    @Query("DELETE FROM LifeItem")
    void deleteAll();

}
