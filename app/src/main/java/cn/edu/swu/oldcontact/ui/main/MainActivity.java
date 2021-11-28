package cn.edu.swu.oldcontact.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.yalantis.ucrop.UCrop;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.ui.BottomDialog;
import cn.edu.swu.oldcontact.ui.contact.ContactFragment;
import cn.edu.swu.oldcontact.ui.contact.ContactPublishFragment;
import cn.edu.swu.oldcontact.ui.life.LifeFragment;
import cn.edu.swu.oldcontact.ui.life.LifePublishFragment;
import cn.edu.swu.oldcontact.ui.my.MyFragment;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;
    LifePublishFragment mLifePublishFragment;
    ContactPublishFragment mContactPublishFragment;
    LifeFragment mLifeFragment;
    ContactFragment mContactFragment;
    MyFragment mMyFragment;
    BottomDialog mBottomDialog;

    @BindView(R.id.classify_recycler)
    RecyclerView mClassifyRecycler;

    @BindView(R.id.top_bar)
    RelativeLayout mTopBar;
    @BindView(R.id.bottom_bar)
    LinearLayout mBottomBar;
    @BindView(R.id.contact)
    TextView mContactBtn;
    @BindView(R.id.life)
    TextView mLifeBtn;
    @BindView(R.id.headImg)
    ImageView mHeadImg;
    @BindView(R.id.addBtn)
    ImageView mAddBtn;

    @BindView(R.id.care_recycler)
    RecyclerView mCareRecycler;
    public int contactPubFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        init();
    }



    private void init() {
        mLifeFragment = new LifeFragment();
        mContactFragment = new ContactFragment();
        mMyFragment = new MyFragment();
        mLifePublishFragment = new LifePublishFragment();
        mContactPublishFragment = new ContactPublishFragment();

        replaceFragment(mLifeFragment);

        IApp app = (IApp) getApplication();

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        mClassifyRecycler.setLayoutManager(layoutManager1);
        ClassifyAdapter classifyAdapter = new ClassifyAdapter(app.mClassifyList);
        mClassifyRecycler.setAdapter(classifyAdapter);

        classifyAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if(getSupportFragmentManager().findFragmentById(R.id.main_activity_content) instanceof LifeFragment){
                    List<LifeItem> items = app.db.lifeDao().getItemByIndex(position);
                    mLifeFragment.adapter.mItemList = items;
                    mLifeFragment.adapter.notifyDataSetChanged();
                }else {
                    List<ContactActItem> itemList1 = app.db.contactContentDao().getListByIndex(position);
                    Collections.reverse(itemList1);//倒叙
                    mContactFragment.adapter.mItemList = itemList1;
                    mContactFragment.adapter.notifyDataSetChanged();
                }
            }
        });


        List<User> itemList = app.db.userDao().getAll();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        mCareRecycler.setLayoutManager(layoutManager2);
        CareAdapter adapter2 = new CareAdapter(itemList,this);
        mCareRecycler.setAdapter(adapter2);


        mContactBtn.setOnClickListener(v->{
           replaceFragment(mContactFragment);
            mContactBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mLifeBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mCareRecycler.setVisibility(View.VISIBLE);
        });

        mLifeBtn.setOnClickListener(v->{
            replaceFragment(mLifeFragment);
            mLifeBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mContactBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mCareRecycler.setVisibility(View.GONE);
        });

        mHeadImg.setOnClickListener(v->{
            replaceFragment(mMyFragment);
            mTopBar.setVisibility(View.GONE);
            mBottomBar.setVisibility(View.GONE);
            mCareRecycler.setVisibility(View.GONE);
        });

        mAddBtn.setOnClickListener(view->{
            mBottomDialog = new BottomDialog();
            mBottomDialog.show(getSupportFragmentManager(),"BottomDialog");
            mBottomDialog.setOnDlgLeftBtnClickListener(new BottomDialog.onDlgLeftBtnClickListener() {
                @Override
                public void onClick() {
                    replaceFragment(mContactPublishFragment);
                    mTopBar.setVisibility(View.GONE);
                    mBottomBar.setVisibility(View.GONE);
                    mCareRecycler.setVisibility(View.GONE);
                    mBottomDialog.dismiss();
                }
            });

            mBottomDialog.setOnDlgRightBtnClickListener(new BottomDialog.onDlgRightBtnClickListener() {
                @Override
                public void onClick() {
                    replaceFragment(mLifePublishFragment);
                    mTopBar.setVisibility(View.GONE);
                    mBottomBar.setVisibility(View.GONE);
                    mCareRecycler.setVisibility(View.GONE);
                    mBottomDialog.dismiss();
                }
            });
        });



    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.main_activity_content);
        if(current instanceof ContactFragment){
            mContactFragment.mLocation.setVisibility(View.VISIBLE);
            mCareRecycler.setVisibility(View.VISIBLE);
            mTopBar.setVisibility(View.VISIBLE);
            mBottomBar.setVisibility(View.VISIBLE);
            mContactBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mLifeBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            mCareRecycler.setVisibility(View.GONE);
        }
        if (current instanceof LifeFragment){
            mTopBar.setVisibility(View.VISIBLE);
            mBottomBar.setVisibility(View.VISIBLE);
            mCareRecycler.setVisibility(View.VISIBLE);
            mLifeBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            mContactBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }



    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_activity_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if(contactPubFlag == 1){
                mContactPublishFragment.mImg.setImageURI(resultUri);
                mContactPublishFragment.item.setBgImg(resultUri.toString());
            }else {
                mLifePublishFragment.mItemList.add(resultUri.toString());
                mLifePublishFragment.adapter.notifyDataSetChanged();
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }



}