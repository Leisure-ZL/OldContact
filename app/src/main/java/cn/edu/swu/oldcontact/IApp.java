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
        group1.setName("?????????");

        Group group2 = new Group();
        group2.setImg(Utils.idToUri(this,R.drawable.head_test).toString());
        group2.setIndex(0);
        group2.setName("?????????");

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
        user1.setUsername("??????");
        user1.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user1.setIntegral(100);

        User user2 = new User();
        user2.setUsername("???");
        user2.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user2.setIntegral(180);

        User user3 = new User();
        user3.setUsername("???");
        user3.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user3.setIntegral(260);

        User user4 = new User();
        user4.setUsername("???");
        user4.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user4.setIntegral(300);

        User user5 = new User();
        user5.setUsername("???");
        user5.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user5.setIntegral(500);

        User user6 = new User();
        user6.setUsername("???");
        user6.setHeadImg(Utils.idToUri(this,R.drawable.head_test).toString());
        user6.setIntegral(500);

        User user7 = new User();
        user7.setUsername("???");
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
        item1.setUsername("??????");
        item1.setContent("????????????");
        item1.setLike(4);
        item1.setClickFlag(0);
        for(int i=0;i<50;i++){
            item1.setLifeId(i);
            db.lifeCommentDao().insert(item1);
        }

        item2.setHeadImg(R.drawable.head_test);
        item2.setUsername("???");
        item2.setContent("?????????");
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



        item.setTitle("????????????");
        item.setContent("??????????????????");
        item.setHeadImgId(R.drawable.head_test);
        item.setUsername("??????");
        item.setLikeNum(0);
        item.setImgIdList(imgList1);
        item.setTime("11-22");
        item.setIndex(0);

        item2.setTitle("?????????????????????");
        item2.setContent("??????????????????");
        item2.setHeadImgId(R.drawable.head_test);
        item2.setUsername("??????");
        item2.setLikeNum(0);
        item2.setImgIdList(imgList2);
        item2.setTime("11-22");
        item2.setIndex(0);

        item3.setTitle("?????????????????????");
        item3.setContent("??????????????????");
        item3.setHeadImgId(R.drawable.head_test);
        item3.setUsername("??????");
        item3.setLikeNum(0);
        item3.setImgIdList(imgList3);
        item3.setTime("11-22");
        item3.setIndex(0);

        item4.setTitle("?????????????????????");
        item4.setContent("??????????????????");
        item4.setHeadImgId(R.drawable.head_test);
        item4.setUsername("??????");
        item4.setLikeNum(0);
        item4.setImgIdList(imgList4);
        item4.setTime("11-22");
        item4.setIndex(0);

        item5.setTitle("?????????????????????");
        item5.setContent("??????????????????");
        item5.setHeadImgId(R.drawable.head_test);
        item5.setUsername("??????");
        item5.setLikeNum(0);
        item5.setImgIdList(imgList5);
        item5.setTime("11-22");
        item5.setIndex(1);

        item6.setTitle("?????????????????????");
        item6.setContent("??????????????????");
        item6.setHeadImgId(R.drawable.head_test);
        item6.setUsername("??????");
        item6.setLikeNum(0);
        item6.setImgIdList(imgList6);
        item6.setTime("11-22");
        item6.setIndex(1);

        item7.setTitle("?????????????????????");
        item7.setContent("??????????????????");
        item7.setHeadImgId(R.drawable.head_test);
        item7.setUsername("??????");
        item7.setLikeNum(0);
        item7.setImgIdList(imgList7);
        item7.setTime("11-22");
        item7.setIndex(1);

        item8.setTitle("?????????????????????");
        item8.setContent("??????????????????");
        item8.setHeadImgId(R.drawable.head_test);
        item8.setUsername("??????");
        item8.setLikeNum(0);
        item8.setImgIdList(imgList8);
        item8.setTime("11-22");
        item8.setIndex(1);

        item9.setTitle("?????????????????????");
        item9.setContent("??????????????????");
        item9.setHeadImgId(R.drawable.head_test);
        item9.setUsername("??????");
        item9.setLikeNum(0);
        item9.setImgIdList(imgList9);
        item9.setTime("11-22");
        item9.setIndex(1);

        item10.setTitle("?????????????????????");
        item10.setContent("??????????????????");
        item10.setHeadImgId(R.drawable.head_test);
        item10.setUsername("??????");
        item10.setLikeNum(0);
        item10.setImgIdList(imgList10);
        item10.setTime("11-22");
        item10.setIndex(1);

        item11.setTitle("?????????????????????");
        item11.setContent("??????????????????");
        item11.setHeadImgId(R.drawable.head_test);
        item11.setUsername("??????");
        item11.setLikeNum(0);
        item11.setImgIdList(imgList11);
        item11.setTime("11-22");
        item11.setIndex(1);

        item12.setTitle("?????????????????????");
        item12.setContent("??????????????????");
        item12.setHeadImgId(R.drawable.head_test);
        item12.setUsername("??????");
        item12.setLikeNum(0);
        item12.setImgIdList(imgList12);
        item12.setTime("11-22");
        item12.setIndex(1);

        item13.setTitle("?????????????????????");
        item13.setContent("??????????????????");
        item13.setHeadImgId(R.drawable.head_test);
        item13.setUsername("??????");
        item13.setLikeNum(0);
        item13.setImgIdList(imgList13);
        item13.setTime("11-22");
        item13.setIndex(1);

        item14.setTitle("?????????????????????");
        item14.setContent("??????????????????");
        item14.setHeadImgId(R.drawable.head_test);
        item14.setUsername("??????");
        item14.setLikeNum(0);
        item14.setImgIdList(imgList14);
        item14.setTime("11-22");
        item14.setIndex(0);

        item15.setTitle("?????????????????????");
        item15.setContent("??????????????????");
        item15.setHeadImgId(R.drawable.head_test);
        item15.setUsername("??????");
        item15.setLikeNum(0);
        item15.setImgIdList(imgList15);
        item15.setTime("11-22");
        item15.setIndex(0);

        item16.setTitle("?????????????????????");
        item16.setContent("??????????????????");
        item16.setHeadImgId(R.drawable.head_test);
        item16.setUsername("??????");
        item16.setLikeNum(0);
        item16.setImgIdList(imgList16);
        item16.setTime("11-22");
        item16.setIndex(0);

        item17.setTitle("?????????????????????");
        item17.setContent("??????????????????");
        item17.setHeadImgId(R.drawable.head_test);
        item17.setUsername("??????");
        item17.setLikeNum(0);
        item17.setImgIdList(imgList17);
        item17.setTime("11-22");
        item17.setIndex(0);

        item18.setTitle("?????????????????????");
        item18.setContent("??????????????????");
        item18.setHeadImgId(R.drawable.head_test);
        item18.setUsername("??????");
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
        item1.setTitle("??????");
        item1.setAllNum(20);
        item1.setAlreadyNum(11);
        item1.setDist("<100???");

        item2.setIndex(0);
        item2.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item2.setTitle("??????");
        item2.setAllNum(20);
        item2.setAlreadyNum(11);
        item2.setDist("<200???");

        item3.setIndex(0);
        item3.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item3.setTitle("??????");
        item3.setAllNum(30);
        item3.setAlreadyNum(11);
        item3.setDist("<500???");

        item4.setIndex(0);
        item4.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item4.setTitle("??????");
        item4.setAllNum(20);
        item4.setAlreadyNum(11);
        item4.setDist("<200???");

        item5.setIndex(0);
        item5.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item5.setTitle("??????");
        item5.setAllNum(10);
        item5.setAlreadyNum(9);
        item5.setDist("<200???");

        item6.setIndex(0);
        item6.setBgImg(Utils.idToUri(this,R.drawable.act_img).toString());
        item6.setTitle("??????");
        item6.setAllNum(20);
        item6.setAlreadyNum(11);
        item6.setDist("<1000???");
        item6.setActPlace("????????????????????????????????????");
        item6.setActPhoneNum("18323306841");
        item6.setNote("???");


        db.contactContentDao().insert(item1);
        db.contactContentDao().insert(item2);
        db.contactContentDao().insert(item3);
        db.contactContentDao().insert(item4);
        db.contactContentDao().insert(item5);
        db.contactContentDao().insert(item6);

        //index 1
        item5.setIndex(1);
        item5.setBgImg(Utils.idToUri(this,R.drawable.majiang).toString());
        item5.setTitle("??????");
        item5.setAllNum(4);
        item5.setAlreadyNum(3);
        item5.setDist("<100???");

        item6.setIndex(1);
        item6.setBgImg(Utils.idToUri(this,R.drawable.puke).toString());
        item6.setTitle("??????");
        item6.setAllNum(3);
        item6.setAlreadyNum(1);
        item6.setDist("<200???");

        item7.setIndex(1);
        item7.setBgImg(Utils.idToUri(this,R.drawable.xiangqi).toString());
        item7.setTitle("??????");
        item7.setAllNum(2);
        item7.setAlreadyNum(1);
        item7.setDist("<500???");

        item8.setIndex(1);
        item8.setBgImg(Utils.idToUri(this,R.drawable.weiqi).toString());
        item8.setTitle("??????");
        item8.setAllNum(2);
        item8.setAlreadyNum(1);
        item8.setDist("<200???");

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
        classifyItem1.setTitle("??????");
        classifyItem2.setFlag(0);
        classifyItem2.setTitle("??????");
        classifyItem3.setFlag(0);
        classifyItem3.setTitle("??????");
        classifyItem4.setFlag(0);
        classifyItem4.setTitle("??????");
        classifyItem5.setFlag(0);
        classifyItem5.setTitle("??????");

        mClassifyList.add(classifyItem1);
        mClassifyList.add(classifyItem2);
        mClassifyList.add(classifyItem3);
    //    mClassifyList.add(classifyItem4);
        mClassifyList.add(classifyItem5);

    }



}
