package cn.edu.swu.oldcontact;

import android.app.Application;
import android.net.Uri;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.oldcontact.javaBean.ContactClassifyItem;
import cn.edu.swu.oldcontact.javaBean.ContactContentItem;
import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.utils.DB;
import cn.edu.swu.oldcontact.utils.Utils;

public class IApp extends Application {

    public DB db;
    public String mCurLocation;
    public List<ContactClassifyItem> mClassifyList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        initClassifyData();

        db = Room.databaseBuilder(this, DB.class,
                "DB").allowMainThreadQueries().build();

        db.lifeDao().deleteAll();
        db.lifeCommentDao().deleteAll();
        db.contactContentDao().deleteAll();
        db.userDao().deleteAll();

        initContactContentData();
        initLifeData();
        initLifeCommentData();
        initUserData();

    }

    private void initUserData() {
        User user1 = new User();
        user1.setUsername("啦啦啦");
        user1.setHeadImg(Utils.idToUri(this,R.drawable.head_test2).toString());
        db.userDao().insert(user1);
        db.userDao().insert(user1);
        db.userDao().insert(user1);
        db.userDao().insert(user1);
        db.userDao().insert(user1);

    }


    private void initLifeCommentData() {
        LifeContentCommentItem item1 = new LifeContentCommentItem();
        LifeContentCommentItem item2 = new LifeContentCommentItem();
        LifeContentCommentItem item3 = new LifeContentCommentItem();
        LifeContentCommentItem item4 = new LifeContentCommentItem();

        item1.setHeadImg(R.drawable.head_test);
        item1.setUsername("啦啦啦");
        item1.setContent("还不错！");
        item1.setLike(4);
        item1.setClickFlag(0);
        for(int i=0;i<50;i++){
            item1.setLifeId(i);
            db.lifeCommentDao().insert(item1);
        }

        item2.setHeadImg(R.drawable.head_test);
        item2.setUsername("啦啦啦");
        item2.setContent("还不错！");
        item2.setClickFlag(0);
        item2.setLike(1);
        for(int i=0;i<50;i++){
            item2.setLifeId(i);
            db.lifeCommentDao().insert(item2);
        }
    }

    private void initLifeData() {
        LifeItem item = new LifeItem();
        LifeItem item2 = new LifeItem();
        List<String> imgList1 = new ArrayList<>();
        imgList1.add(Utils.idToUri(this,R.drawable.img_test).toString());
        imgList1.add(Utils.idToUri(this,R.drawable.img2).toString());
        imgList1.add(Utils.idToUri(this,R.drawable.img3).toString());
        imgList1.add(Utils.idToUri(this,R.drawable.img3).toString());


        List<String> imgList2 = new ArrayList<>();
        imgList2.add(Utils.idToUri(this,R.drawable.img1).toString());
        imgList2.add(Utils.idToUri(this,R.drawable.img2).toString());
        imgList2.add(Utils.idToUri(this,R.drawable.img3).toString());

        item.setTitle("测试文字");
        item.setContent("测试文字内容");
        item.setHeadImgId(R.drawable.head_test);
        item.setUsername("啦啦啦");
        item.setLikeNum(0);
        item.setImgIdList(imgList1);
        item.setTime("11-22");
        item.setIndex(0);

        item2.setTitle("今天天气不错！");
        item2.setContent("测试文字内容");
        item2.setHeadImgId(R.drawable.head_test);
        item2.setUsername("啦啦啦");
        item2.setLikeNum(0);
        item2.setImgIdList(imgList2);
        item2.setTime("11-22");
        item2.setIndex(1);


        db.lifeDao().insert(item);
        db.lifeDao().insert(item2);
        db.lifeDao().insert(item);
        db.lifeDao().insert(item2);
        db.lifeDao().insert(item);
        db.lifeDao().insert(item);
        db.lifeDao().insert(item2);
        db.lifeDao().insert(item);
    }

    private void initContactContentData() {
        ContactContentItem item1 = new ContactContentItem();
        ContactContentItem item2 = new ContactContentItem();
        ContactContentItem item3 = new ContactContentItem();
        ContactContentItem item4 = new ContactContentItem();
        ContactContentItem item5 = new ContactContentItem();
        ContactContentItem item6 = new ContactContentItem();
        ContactContentItem item7 = new ContactContentItem();
        ContactContentItem item8 = new ContactContentItem();

        item1.setIndex(0);
        item1.setBgImg(Utils.idToUri(this,R.drawable.chaotianmen).toString());
        item1.setTitle("朝天门");
        item1.setAllNum(20);
        item1.setAlreadyNum(11);
        item1.setDist("<100米");

        item2.setIndex(0);
        item2.setBgImg(Utils.idToUri(this,R.drawable.hongyadong).toString());
        item2.setTitle("洪崖洞");
        item2.setAllNum(20);
        item2.setAlreadyNum(11);
        item2.setDist("<200米");

        item3.setIndex(0);
        item3.setBgImg(Utils.idToUri(this,R.drawable.jieffangbei).toString());
        item3.setTitle("解放碑");
        item3.setAllNum(30);
        item3.setAlreadyNum(11);
        item3.setDist("<500米");

        item4.setIndex(0);
        item4.setBgImg(Utils.idToUri(this,R.drawable.ciqikou).toString());
        item4.setTitle("磁器口");
        item4.setAllNum(20);
        item4.setAlreadyNum(11);
        item4.setDist("<200米");

        item5.setIndex(0);
        item5.setBgImg(Utils.idToUri(this,R.drawable.shibati).toString());
        item5.setTitle("十八梯");
        item5.setAllNum(10);
        item5.setAlreadyNum(9);
        item5.setDist("<200米");

        item6.setIndex(0);
        item6.setBgImg(Utils.idToUri(this,R.drawable.sxbowuguan).toString());
        item6.setTitle("三峡博物馆");
        item6.setAllNum(20);
        item6.setAlreadyNum(11);
        item6.setDist("<1000米");


        db.contactContentDao().insert(item1);
        db.contactContentDao().insert(item2);
        db.contactContentDao().insert(item3);
        db.contactContentDao().insert(item4);
        db.contactContentDao().insert(item5);
        db.contactContentDao().insert(item6);

        //index 1
        item5.setIndex(1);
        item5.setBgImg(Utils.idToUri(this,R.drawable.majiang).toString());
        item5.setTitle("麻将");
        item5.setAllNum(4);
        item5.setAlreadyNum(3);
        item5.setDist("<100米");

        item6.setIndex(1);
        item6.setBgImg(Utils.idToUri(this,R.drawable.puke).toString());
        item6.setTitle("扑克");
        item6.setAllNum(3);
        item6.setAlreadyNum(1);
        item6.setDist("<200米");

        item7.setIndex(1);
        item7.setBgImg(Utils.idToUri(this,R.drawable.xiangqi).toString());
        item7.setTitle("象棋");
        item7.setAllNum(2);
        item7.setAlreadyNum(1);
        item7.setDist("<500米");

        item8.setIndex(1);
        item8.setBgImg(Utils.idToUri(this,R.drawable.weiqi).toString());
        item8.setTitle("围棋");
        item8.setAllNum(2);
        item8.setAlreadyNum(1);
        item8.setDist("<200米");

        db.contactContentDao().insert(item5);
        db.contactContentDao().insert(item6);
        db.contactContentDao().insert(item7);
        db.contactContentDao().insert(item8);
    }

    private void initClassifyData() {
        ContactClassifyItem classifyItem1 = new ContactClassifyItem();
        ContactClassifyItem classifyItem2 = new ContactClassifyItem();
        ContactClassifyItem classifyItem3 = new ContactClassifyItem();
        ContactClassifyItem classifyItem4 = new ContactClassifyItem();
        ContactClassifyItem classifyItem5 = new ContactClassifyItem();
        classifyItem1.setFlag(1);
        classifyItem1.setTitle("旅游");
        classifyItem2.setFlag(0);
        classifyItem2.setTitle("棋牌");
        classifyItem3.setFlag(0);
        classifyItem3.setTitle("运动");
        classifyItem4.setFlag(0);
        classifyItem4.setTitle("聊天");
        classifyItem5.setFlag(0);
        classifyItem5.setTitle("其他");

        mClassifyList.add(classifyItem1);
        mClassifyList.add(classifyItem2);
        mClassifyList.add(classifyItem3);
        mClassifyList.add(classifyItem4);
        mClassifyList.add(classifyItem5);

    }



}
