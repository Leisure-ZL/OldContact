package cn.edu.swu.oldcontact;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import cn.edu.swu.oldcontact.javaBean.BannerItem;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.javaBean.ContactClassifyItem;
import cn.edu.swu.oldcontact.javaBean.Group;
import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.utils.DB;
import cn.edu.swu.oldcontact.utils.Utils;

public class IApp extends Application {

    public DB db;
    public String mCurLocation;
    public List<ContactClassifyItem> mClassifyList = new ArrayList<>();
    public List<BannerItem> mBannerList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        initClassifyData();
        initBannerData();

        db = Room.databaseBuilder(this, DB.class,
                "DB").allowMainThreadQueries().build();

        db.lifeDao().deleteAll();
        db.lifeCommentDao().deleteAll();
        db.contactContentDao().deleteAll();
        db.userDao().deleteAll();
        db.groupDao().deleteAll();

        initContactContentData();
        initLifeData();
        initLifeCommentData();
        initUserData();
        initGroupData();

    }

    private void initBannerData() {

        BannerItem bannerItem = new BannerItem();
        bannerItem.setImg(Utils.idToUri(this,R.drawable.banner_img).toString());
        mBannerList.add(bannerItem);
        mBannerList.add(bannerItem);
        mBannerList.add(bannerItem);

    }


    private void initGroupData() {
//        Group group = new Group();
//        group.setImg(Utils.idToUri(this,R.drawable.add2).toString());
//        group.setIndex(0);
//        db.groupDao().insert(group);

        Group group1 = new Group();
        group1.setImg(Utils.idToUri(this,R.drawable.group_img).toString());
        group1.setIndex(0);
        group1.setName("朝天门");

        Group group2 = new Group();
        group2.setImg(Utils.idToUri(this,R.drawable.head_test).toString());
        group2.setIndex(0);
        group2.setName("朝天门");

        db.groupDao().insert(group1);
        db.groupDao().insert(group2);
        db.groupDao().insert(group2);
        db.groupDao().insert(group2);

    }

    private void initUserData() {
//        User user = new User();
//        user.setHeadImg(Utils.idToUri(this,R.drawable.add2).toString());
//        user.setIntegral(0);

        User user1 = new User();
        user1.setUsername("用户");
        user1.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user1.setIntegral(100);

        User user2 = new User();
        user2.setUsername("啦");
        user2.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user2.setIntegral(180);

        User user3 = new User();
        user3.setUsername("啦");
        user3.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user3.setIntegral(260);

        User user4 = new User();
        user4.setUsername("啦");
        user4.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user4.setIntegral(300);

        User user5 = new User();
        user5.setUsername("啦");
        user5.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user5.setIntegral(500);

        User user6 = new User();
        user6.setUsername("啦");
        user6.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user6.setIntegral(500);

        User user7 = new User();
        user7.setUsername("啦");
        user7.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user7.setIntegral(500);

      //  db.userDao().insert(user);
        db.userDao().insert(user1);
        db.userDao().insert(user2);
        db.userDao().insert(user3);
        db.userDao().insert(user4);
        db.userDao().insert(user5);
        db.userDao().insert(user6);
        db.userDao().insert(user7);

    }


    private void initLifeCommentData() {
        LifeContentCommentItem item1 = new LifeContentCommentItem();
        LifeContentCommentItem item2 = new LifeContentCommentItem();
        LifeContentCommentItem item3 = new LifeContentCommentItem();
        LifeContentCommentItem item4 = new LifeContentCommentItem();

        item1.setHeadImg(R.drawable.head_test);
        item1.setUsername("平凡");
        item1.setContent("还不错！");
        item1.setLike(4);
        item1.setClickFlag(0);
        for(int i=0;i<50;i++){
            item1.setLifeId(i);
            db.lifeCommentDao().insert(item1);
        }

        item2.setHeadImg(R.drawable.head_test);
        item2.setUsername("山");
        item2.setContent("棒！！");
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
        LifeItem item3 = new LifeItem();
        LifeItem item4 = new LifeItem();
        LifeItem item5 = new LifeItem();
        LifeItem item6 = new LifeItem();
        LifeItem item7 = new LifeItem();
        LifeItem item8 = new LifeItem();
        LifeItem item9 = new LifeItem();
        LifeItem item10 = new LifeItem();
        LifeItem item11 = new LifeItem();
        LifeItem item12 = new LifeItem();
        LifeItem item13 = new LifeItem();
        LifeItem item14 = new LifeItem();
        LifeItem item15 = new LifeItem();
        LifeItem item16 = new LifeItem();
        LifeItem item17 = new LifeItem();
        LifeItem item18 = new LifeItem();
        List<String> imgList1 = new ArrayList<>();
        imgList1.add(Utils.idToUri(this,R.drawable.all_img).toString());


        List<String> imgList2 = new ArrayList<>();
        imgList2.add(Utils.idToUri(this,R.drawable.all_img).toString());
        imgList2.add(Utils.idToUri(this,R.drawable.all_img).toString());
        imgList2.add(Utils.idToUri(this,R.drawable.all_img).toString());

        List<String> imgList3 = new ArrayList<>();
        imgList3.add(Utils.idToUri(this,R.drawable.all_img).toString());

        List<String> imgList4 = new ArrayList<>();
        imgList4.add(Utils.idToUri(this,R.drawable.all_img).toString());


        List<String> imgList5 = new ArrayList<>();
        imgList5.add(Utils.idToUri(this,R.drawable.qp1).toString());

        List<String> imgList6 = new ArrayList<>();
        imgList6.add(Utils.idToUri(this,R.drawable.qp2).toString());

        List<String> imgList7 = new ArrayList<>();
        imgList7.add(Utils.idToUri(this,R.drawable.qp3).toString());

        List<String> imgList8 = new ArrayList<>();
        imgList8.add(Utils.idToUri(this,R.drawable.qp4).toString());

        List<String> imgList9 = new ArrayList<>();
        imgList9.add(Utils.idToUri(this,R.drawable.qp5).toString());

        List<String> imgList10 = new ArrayList<>();
        imgList10.add(Utils.idToUri(this,R.drawable.qp6).toString());

        List<String> imgList11 = new ArrayList<>();
        imgList11.add(Utils.idToUri(this,R.drawable.qp7).toString());

        List<String> imgList12 = new ArrayList<>();
        imgList12.add(Utils.idToUri(this,R.drawable.qp8).toString());

        List<String> imgList13 = new ArrayList<>();
        imgList13.add(Utils.idToUri(this,R.drawable.qp9).toString());

        List<String> imgList14 = new ArrayList<>();
        imgList14.add(Utils.idToUri(this,R.drawable.all_img).toString());

        List<String> imgList15 = new ArrayList<>();
        imgList15.add(Utils.idToUri(this,R.drawable.all_img).toString());

        List<String> imgList16 = new ArrayList<>();
        imgList16.add(Utils.idToUri(this,R.drawable.all_img).toString());

        List<String> imgList17 = new ArrayList<>();
        imgList17.add(Utils.idToUri(this,R.drawable.img7).toString());

        List<String> imgList18 = new ArrayList<>();
        imgList18.add(Utils.idToUri(this,R.drawable.all_img).toString());



        item.setTitle("测试文字");
        item.setContent("测试文字内容");
        item.setHeadImgId(R.drawable.head_test);
        item.setUsername("用户");
        item.setLikeNum(0);
        item.setImgIdList(imgList1);
        item.setTime("11-22");
        item.setIndex(0);

        item2.setTitle("今天天气不错！");
        item2.setContent("测试文字内容");
        item2.setHeadImgId(R.drawable.head_test);
        item2.setUsername("用户");
        item2.setLikeNum(0);
        item2.setImgIdList(imgList2);
        item2.setTime("11-22");
        item2.setIndex(0);

        item3.setTitle("今天天气不错！");
        item3.setContent("测试文字内容");
        item3.setHeadImgId(R.drawable.head_test);
        item3.setUsername("用户");
        item3.setLikeNum(0);
        item3.setImgIdList(imgList3);
        item3.setTime("11-22");
        item3.setIndex(0);

        item4.setTitle("今天天气不错！");
        item4.setContent("测试文字内容");
        item4.setHeadImgId(R.drawable.head_test);
        item4.setUsername("用户");
        item4.setLikeNum(0);
        item4.setImgIdList(imgList4);
        item4.setTime("11-22");
        item4.setIndex(0);

        item5.setTitle("今天天气不错！");
        item5.setContent("测试文字内容");
        item5.setHeadImgId(R.drawable.head_test);
        item5.setUsername("用户");
        item5.setLikeNum(0);
        item5.setImgIdList(imgList5);
        item5.setTime("11-22");
        item5.setIndex(1);

        item6.setTitle("今天天气不错！");
        item6.setContent("测试文字内容");
        item6.setHeadImgId(R.drawable.head_test);
        item6.setUsername("用户");
        item6.setLikeNum(0);
        item6.setImgIdList(imgList6);
        item6.setTime("11-22");
        item6.setIndex(1);

        item7.setTitle("今天天气不错！");
        item7.setContent("测试文字内容");
        item7.setHeadImgId(R.drawable.head_test);
        item7.setUsername("用户");
        item7.setLikeNum(0);
        item7.setImgIdList(imgList7);
        item7.setTime("11-22");
        item7.setIndex(1);

        item8.setTitle("今天天气不错！");
        item8.setContent("测试文字内容");
        item8.setHeadImgId(R.drawable.head_test);
        item8.setUsername("用户");
        item8.setLikeNum(0);
        item8.setImgIdList(imgList8);
        item8.setTime("11-22");
        item8.setIndex(1);

        item9.setTitle("今天天气不错！");
        item9.setContent("测试文字内容");
        item9.setHeadImgId(R.drawable.head_test);
        item9.setUsername("用户");
        item9.setLikeNum(0);
        item9.setImgIdList(imgList9);
        item9.setTime("11-22");
        item9.setIndex(1);

        item10.setTitle("今天天气不错！");
        item10.setContent("测试文字内容");
        item10.setHeadImgId(R.drawable.head_test);
        item10.setUsername("用户");
        item10.setLikeNum(0);
        item10.setImgIdList(imgList10);
        item10.setTime("11-22");
        item10.setIndex(1);

        item11.setTitle("今天天气不错！");
        item11.setContent("测试文字内容");
        item11.setHeadImgId(R.drawable.head_test);
        item11.setUsername("用户");
        item11.setLikeNum(0);
        item11.setImgIdList(imgList11);
        item11.setTime("11-22");
        item11.setIndex(1);

        item12.setTitle("今天天气不错！");
        item12.setContent("测试文字内容");
        item12.setHeadImgId(R.drawable.head_test);
        item12.setUsername("用户");
        item12.setLikeNum(0);
        item12.setImgIdList(imgList12);
        item12.setTime("11-22");
        item12.setIndex(1);

        item13.setTitle("今天天气不错！");
        item13.setContent("测试文字内容");
        item13.setHeadImgId(R.drawable.head_test);
        item13.setUsername("用户");
        item13.setLikeNum(0);
        item13.setImgIdList(imgList13);
        item13.setTime("11-22");
        item13.setIndex(1);

        item14.setTitle("今天天气不错！");
        item14.setContent("测试文字内容");
        item14.setHeadImgId(R.drawable.head_test);
        item14.setUsername("用户");
        item14.setLikeNum(0);
        item14.setImgIdList(imgList14);
        item14.setTime("11-22");
        item14.setIndex(0);

        item15.setTitle("今天天气不错！");
        item15.setContent("测试文字内容");
        item15.setHeadImgId(R.drawable.head_test);
        item15.setUsername("用户");
        item15.setLikeNum(0);
        item15.setImgIdList(imgList15);
        item15.setTime("11-22");
        item15.setIndex(0);

        item16.setTitle("今天天气不错！");
        item16.setContent("测试文字内容");
        item16.setHeadImgId(R.drawable.head_test);
        item16.setUsername("用户");
        item16.setLikeNum(0);
        item16.setImgIdList(imgList16);
        item16.setTime("11-22");
        item16.setIndex(0);

        item17.setTitle("今天天气不错！");
        item17.setContent("测试文字内容");
        item17.setHeadImgId(R.drawable.head_test);
        item17.setUsername("用户");
        item17.setLikeNum(0);
        item17.setImgIdList(imgList17);
        item17.setTime("11-22");
        item17.setIndex(0);

        item18.setTitle("今天天气不错！");
        item18.setContent("测试文字内容");
        item18.setHeadImgId(R.drawable.head_test);
        item18.setUsername("用户");
        item18.setLikeNum(0);
        item18.setImgIdList(imgList18);
        item18.setTime("11-22");
        item18.setIndex(0);


        db.lifeDao().insert(item);
        db.lifeDao().insert(item2);
        db.lifeDao().insert(item3);
        db.lifeDao().insert(item4);
        db.lifeDao().insert(item5);
        db.lifeDao().insert(item6);
        db.lifeDao().insert(item7);
        db.lifeDao().insert(item8);
        db.lifeDao().insert(item9);
        db.lifeDao().insert(item10);
        db.lifeDao().insert(item11);
        db.lifeDao().insert(item12);
        db.lifeDao().insert(item13);
        db.lifeDao().insert(item14);
        db.lifeDao().insert(item15);
        db.lifeDao().insert(item16);
        db.lifeDao().insert(item17);
        db.lifeDao().insert(item18);
    }

    private void initContactContentData() {
        ContactActItem item1 = new ContactActItem();
        ContactActItem item2 = new ContactActItem();
        ContactActItem item3 = new ContactActItem();
        ContactActItem item4 = new ContactActItem();
        ContactActItem item5 = new ContactActItem();
        ContactActItem item6 = new ContactActItem();
        ContactActItem item7 = new ContactActItem();
        ContactActItem item8 = new ContactActItem();

        item1.setIndex(0);
        item1.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item1.setTitle("地点");
        item1.setAllNum(20);
        item1.setAlreadyNum(11);
        item1.setDist("<100米");

        item2.setIndex(0);
        item2.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item2.setTitle("地点");
        item2.setAllNum(20);
        item2.setAlreadyNum(11);
        item2.setDist("<200米");

        item3.setIndex(0);
        item3.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item3.setTitle("地点");
        item3.setAllNum(30);
        item3.setAlreadyNum(11);
        item3.setDist("<500米");

        item4.setIndex(0);
        item4.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item4.setTitle("地点");
        item4.setAllNum(20);
        item4.setAlreadyNum(11);
        item4.setDist("<200米");

        item5.setIndex(0);
        item5.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item5.setTitle("地点");
        item5.setAllNum(10);
        item5.setAlreadyNum(9);
        item5.setDist("<200米");

        item6.setIndex(0);
        item6.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item6.setTitle("地点");
        item6.setAllNum(20);
        item6.setAlreadyNum(11);
        item6.setDist("<1000米");
        item6.setActPlace("重庆市沙坪坝区三峡博物馆");
        item6.setActPhoneNum("18323306841");
        item6.setNote("无");


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
    //    mClassifyList.add(classifyItem4);
        mClassifyList.add(classifyItem5);

    }



}
