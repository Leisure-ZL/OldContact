package cn.edu.swu.oldcontact.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cn.edu.swu.oldcontact.dao.ContactContentDao;
import cn.edu.swu.oldcontact.dao.LifeCommentDao;
import cn.edu.swu.oldcontact.dao.LifeDao;
import cn.edu.swu.oldcontact.dao.UserDao;
import cn.edu.swu.oldcontact.javaBean.ContactContentItem;
import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.javaBean.User;

@Database(entities = {ContactContentItem.class, LifeItem.class, LifeContentCommentItem.class, User.class}, version = 1,exportSchema=false)
public abstract class DB extends RoomDatabase{
    public abstract ContactContentDao contactContentDao();
    public abstract LifeDao lifeDao();
    public abstract LifeCommentDao lifeCommentDao();
    public abstract UserDao userDao();
}
