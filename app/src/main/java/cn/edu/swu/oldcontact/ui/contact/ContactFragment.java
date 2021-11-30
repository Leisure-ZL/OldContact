package cn.edu.swu.oldcontact.ui.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.BannerItem;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.javaBean.Group;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.ui.service.ServiceFragment;
import cn.edu.swu.oldcontact.utils.Utils;


public class ContactFragment extends Fragment {
    Unbinder unbinder;
   // public RecyclerView mClassifyRecycler;
    ServiceFragment mServiceFragment;
    @BindView(R.id.location)
    public LinearLayout mLocation;
    @BindView(R.id.location_text)
    TextView mLocationText;
    @BindView(R.id.act_recycler)
    RecyclerView mActRecycler;
    @BindView(R.id.group_recycler)
    RecyclerView mGroupRecycler;
    @BindView(R.id.banner)
    Banner banner;
  //  @BindView(R.id.rank_recycler)
  //  RecyclerView mRankRecycler;

    @BindView(R.id.bottom_slide)
    TextView mBtmSlide;
    public ContactActAdapter adapter;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;


    public ContactFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
      //  mClassifyRecycler = view.findViewById(R.id.contact_classify_recycler);
        unbinder = ButterKnife.bind(this,view);
        mLocation.setVisibility(View.VISIBLE);
     //   mClassifyRecycler.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startLocation();


        IApp app = (IApp) getActivity().getApplication();
        List<ContactActItem> itemList1 = app.db.contactContentDao().getListByIndex(0);
        Collections.reverse(itemList1);//倒叙
        List<ContactActItem> itemList2 = app.db.contactContentDao().getListByIndex(1);
        Collections.reverse(itemList2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mActRecycler.setLayoutManager(layoutManager);

        adapter = new ContactActAdapter(itemList1,getContext(),getActivity());
        mActRecycler.setAdapter(adapter);

        List<Group> groupList = app.db.groupDao().getAll();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        mGroupRecycler.setLayoutManager(layoutManager2);
        ContactGroupAdapter adapter2 = new ContactGroupAdapter(groupList,getContext());
        mGroupRecycler.setAdapter(adapter2);

        mLocation.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LocationActivity.class);
            startActivity(intent);
        });


        useBanner();

        mBtmSlide.setOnClickListener(v->{
            RankBottomSheet bottomSheet = new RankBottomSheet();
            bottomSheet.show(getFragmentManager(),"RankBottomSheet");


        });




    }

    public void useBanner() {
        List<BannerItem> bannerList = ((IApp)getActivity().getApplication()).mBannerList;
        banner.setAdapter(new BannerImageAdapter<BannerItem>(bannerList) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerItem data, int position, int size) {
                //图片加载自己实现
                Glide.with(getContext())
                        .load(data.getImg())
                     //   .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));
    }


    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void startLocation() {
        AMapLocationClient.updatePrivacyShow(getContext(),true,true);
        AMapLocationClient.updatePrivacyAgree(getContext(),true);
        mLocationListener = new AMapLocationListener(){
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(
                                "" + aMapLocation.getCity() +
                                "" + aMapLocation.getDistrict() +
                                "" + aMapLocation.getStreet() +
                                "" + aMapLocation.getStreetNum());
                        mLocationText.setText(buffer);
                        ((IApp)getActivity().getApplication()).mCurLocation = buffer.toString();
                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        try {
            mLocationClient = new AMapLocationClient(getContext().getApplicationContext());
            mLocationClient.setLocationListener(mLocationListener);
            mLocationOption = new AMapLocationClientOption();
            AMapLocationClientOption option = new AMapLocationClientOption();
            option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
            if(null != mLocationClient){
                mLocationClient.setLocationOption(option);
                //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
                mLocationClient.stopLocation();
                mLocationClient.startLocation();
            }
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationCacheEnable(false);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}