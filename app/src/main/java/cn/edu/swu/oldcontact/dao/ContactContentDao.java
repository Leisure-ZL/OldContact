package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.ContactContentItem;

@Dao
public interface ContactContentDao {

    @Query("SELECT * FROM ContactContentItem")
    List<ContactContentItem> getAll();

    @Query("SELECT * FROM ContactContentItem where :ind=`index`")
    List<ContactContentItem> getListByIndex(int ind);

    @Insert
    void insert(ContactContentItem item);

    @Delete
    void delete(ContactContentItem item);


    @Query("DELETE FROM ContactContentItem")
    void deleteAll();

}
