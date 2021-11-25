package cn.edu.swu.oldcontact.ui.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ServiceItem;


public class ServiceFragment extends Fragment {

    RecyclerView recyclerView;
    Button mSendBtn;
    EditText mEdit;
    ImageButton mPhoneBtn,mVideoBtn,mAlbumBtn,mPhotographBtn;
    RelativeLayout mPhotoLayout;
    ImageView mPhotoView;
    ImageView mBack;
    Bitmap mBitmap= null;

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.service_recycler); //获取Recyclerview布局
        mSendBtn = view.findViewById(R.id.sendBtn);
        mEdit = view.findViewById(R.id.edit);
        mPhoneBtn = view.findViewById(R.id.phoneBtn);
        mVideoBtn = view.findViewById(R.id.videoBtn);
        mAlbumBtn = view.findViewById(R.id.albumBtn);
        mPhotographBtn = view.findViewById(R.id.photographBtn);
        mPhotoLayout = view.findViewById(R.id.photoLayout);
        mPhotoView = view.findViewById(R.id.photo);
        mBack = view.findViewById(R.id.back);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<ServiceItem> itemList = new ArrayList<>();
        ServiceItem item = new ServiceItem();
        item.setFlag(1);
        item.setContent("测试消息");
        item.setTime("12:00");

        ServiceItem item2 = new ServiceItem();
        item2.setFlag(0);
        item2.setContent("测试消息");
        item2.setTime("13:00");

        itemList.add(item);
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ServiceItemAdapter adapter = new ServiceItemAdapter(itemList);
        recyclerView.setAdapter(adapter);


        mSendBtn.setOnClickListener(v->{
            String content = mEdit.getText().toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date(System.currentTimeMillis());
            String time = simpleDateFormat.format(date);
            ServiceItem tItem = new ServiceItem();
            if(mBitmap != null){
                tItem.setBitmap(mBitmap);
            }else {
                tItem.setContent(content);
            }
            tItem.setFlag(1);
            tItem.setTime(time);
            adapter.notifyDataSetChanged();
            itemList.add(tItem);
            mEdit.setText("");
            mBitmap = null;
            mPhotoLayout.setVisibility(View.GONE);
        });

        mPhoneBtn.setOnClickListener(v->{
            String number = "18223345669";  //客服电话
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
            startActivity(intent);
        });

        mVideoBtn.setOnClickListener(v->{


        });

        mAlbumBtn.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setType("image/*");// 开启Pictures画面Type设定为image
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);

        });

        mPhotographBtn.setOnClickListener(v->{
            startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 2);
        });

        mBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){//相册选
            Bitmap bitmap= null;
            try {
                mBitmap = BitmapFactory.decodeStream
                        (getActivity().getContentResolver().openInputStream(data.getData()));
                mPhotoView.setImageBitmap(mBitmap);
                mPhotoLayout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == 2 && data != null){//拍照
            mBitmap = (Bitmap) data.getExtras().get("data");
            mPhotoView.setImageBitmap(mBitmap);
            mPhotoLayout.setVisibility(View.VISIBLE);
        }

    }


}