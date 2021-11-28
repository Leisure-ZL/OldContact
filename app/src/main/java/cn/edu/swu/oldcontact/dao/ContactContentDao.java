package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;

@Dao
public interface ContactContentDao {

    @Query("SELECT * FROM ContactActItem")
    List<ContactActItem> getAll();

    @Query("SELECT * FROM ContactActItem where :ind=`index`")
    List<ContactActItem> getListByIndex(int ind);

    @Query("SELECT * FROM ContactActItem WHERE id = :actId")
    ContactActItem getItemById(int actId);

    @Insert
    void insert(ContactActItem item);

    @Delete
    void delete(ContactActItem item);


    @Query("DELETE FROM ContactActItem")
    void deleteAll();

}
