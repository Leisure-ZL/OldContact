package cn.edu.swu.oldcontact.ui.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactClassifyItem;
import cn.edu.swu.oldcontact.ui.service.ServiceFragment;


public class ContactFragment extends Fragment {
    Unbinder unbinder;
   // public RecyclerView mClassifyRecycler;
    ServiceFragment mServiceFragment;
    @BindView(R.id.location)
    public LinearLayout mLocation;
    @BindView(R.id.location_text)
    TextView mLocationText;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;


    @BindView(R.id.serviceBtn)
    public FloatingActionButton mServiceBtn;
    List<ContactClassifyItem> mItemList;


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
        mServiceBtn.setVisibility(View.VISIBLE);
        mLocation.setVisibility(View.VISIBLE);
     //   mClassifyRecycler.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startLocation();
        mItemList = new ArrayList<>();
        initData();

        mServiceFragment = new ServiceFragment();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        mClassifyRecycler.setLayoutManager(layoutManager);
//        ContactClassifyAdapter adapter = new ContactClassifyAdapter(mItemList);
//        mClassifyRecycler.setAdapter(adapter);

        //TODO:通过构造函数，创建不同数据源的Fragment
        ContactContentFragment fragment = new ContactContentFragment(0);
        replaceFragment(fragment);

//        adapter.setOnItemClickListener(new ContactClassifyAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                ContactContentFragment tFragment = new ContactContentFragment(position);
//                replaceFragment(tFragment);
//            }
//        });

        mServiceBtn.setOnClickListener(v->{
            replaceFragment(mServiceFragment);
            mServiceBtn.setVisibility(View.INVISIBLE);
            mLocation.setVisibility(View.GONE);
        //    mClassifyRecycler.setVisibility(View.GONE);
            getActivity().findViewById(R.id.top_bar).setVisibility(View.GONE);
            getActivity().findViewById(R.id.bottom_bar).setVisibility(View.GONE);
        });

        mLocation.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), LocationActivity.class);
            startActivity(intent);
        });


    }

    private void initData() {
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

        mItemList.add(classifyItem1);
        mItemList.add(classifyItem2);
        mItemList.add(classifyItem3);
        mItemList.add(classifyItem4);
        mItemList.add(classifyItem5);

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