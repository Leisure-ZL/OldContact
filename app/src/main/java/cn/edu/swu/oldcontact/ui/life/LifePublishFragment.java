package cn.edu.swu.oldcontact.ui.life;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.utils.DB;
import cn.edu.swu.oldcontact.utils.GlideRoundTransform;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.PermissionChecker.checkSelfPermission;


public class LifePublishFragment extends Fragment {

    Unbinder unbinder;
    String location;
    public List<String> mItemList;
    public LifePublishImgAdapter adapter;
    @BindView(R.id.btn1)
    TextView mBtn1;
    @BindView(R.id.btn2)
    TextView mBtn2;
    @BindView(R.id.btn3)
    TextView mBtn3;
//    @BindView(R.id.btn4)
//    TextView mBtn4;
    @BindView(R.id.btn5)
    TextView mBtn5;
    @BindView(R.id.top_left)
    ImageView mBack;
    @BindView(R.id.top_right)
    ImageView mTip;
    @BindView(R.id.add_img)
    TextView mAddImg;
    @BindView(R.id.title)
    EditText mTitle;
    @BindView(R.id.content)
    EditText mContent;
    @BindView(R.id.publish_btn)
    TextView mPubBtn;
    @BindView(R.id.pub_img_recycler)
    RecyclerView mImgRecycler;
    @BindView(R.id.location_text)
    TextView mLocation;
    int[] typeFlag = {0,0,0,0,0};

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LifePublishFragment() {
        // Required empty public constructor
    }


    public static LifePublishFragment newInstance(String param1, String param2) {
        LifePublishFragment fragment = new LifePublishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life_publish, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectType();

        mItemList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mImgRecycler.setLayoutManager(layoutManager);
        adapter = new LifePublishImgAdapter(mItemList,getContext());
        mImgRecycler.setAdapter(adapter);

        //Ui
        location = ((IApp)getActivity().getApplication()).mCurLocation;
        mLocation.setText(location);

        mBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });

        mAddImg.setOnClickListener(v->{
            requestPower();
            Intent intent = new Intent();
            intent.setType("image/*");// ??????Pictures??????Type?????????image
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, 1);

        });

        mPubBtn.setOnClickListener(v->{
            LifeItem lifeItem = new LifeItem();
            lifeItem.setTitle(mTitle.getText().toString());
            lifeItem.setContent(mContent.getText().toString());
            lifeItem.setImgIdList(mItemList);
            lifeItem.setLocation(location);
            lifeItem.setTime(getDate());
            for(int i=0;i<typeFlag.length;i++ ){
                if(typeFlag[i] == 1){
                    lifeItem.setIndex(i);
                }
            }

            IApp app = (IApp) getActivity().getApplication();
            app.db.lifeDao().insert(lifeItem);

            getActivity().onBackPressed();
        });


    }

    private String getDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String sim = dateFormat.format(date);
        return sim;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){//?????????
            Uri sourceUri = data.getData();
            startUCrop(sourceUri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_activity_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void requestPower() {
        //??????????????????????????????
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //????????????????????????????????????????????????????????????????????????????????? true???
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????.??????????????????"????????????"??????????????????false
            } else {
                //????????????????????????????????????????????????????????????????????????1??????????????????????????????????????????onRequestPermissionsResult????????????????????????
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void selectType() {
        mBtn1.setOnClickListener(v->{
            if(typeFlag[0] == 0){
                mBtn1.setBackground(getResources().getDrawable(R.drawable.layout_border));
                mBtn1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                typeFlag[0] = 1;
            }else {
                mBtn1.setBackgroundColor(getResources().getColor(R.color.transparent));
                mBtn1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                typeFlag[0] = 0;
            }
        });

        mBtn2.setOnClickListener(v->{
            if(typeFlag[1] == 0){
                mBtn2.setBackground(getResources().getDrawable(R.drawable.layout_border));
                mBtn2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                typeFlag[1] = 1;
            }else {
                mBtn2.setBackgroundColor(getResources().getColor(R.color.transparent));
                mBtn2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                typeFlag[1] = 0;
            }
        });

        mBtn3.setOnClickListener(v->{
            if(typeFlag[2] == 0){
                mBtn3.setBackground(getResources().getDrawable(R.drawable.layout_border));
                mBtn3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                typeFlag[2] = 1;
            }else {
                mBtn3.setBackgroundColor(getResources().getColor(R.color.transparent));
                mBtn3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                typeFlag[2] = 0;
            }
        });
//        mBtn4.setOnClickListener(v->{
//            if(typeFlag[3] == 0){
//                mBtn4.setBackground(getResources().getDrawable(R.drawable.layout_border));
//                mBtn4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                typeFlag[3] = 1;
//            }else {
//                mBtn4.setBackgroundColor(getResources().getColor(R.color.transparent));
//                mBtn4.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
//                typeFlag[3] = 0;
//            }
//        });
        mBtn5.setOnClickListener(v->{
            if(typeFlag[4] == 0){
                mBtn5.setBackground(getResources().getDrawable(R.drawable.layout_border));
                mBtn5.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                typeFlag[4] = 1;
            }else {
                mBtn5.setBackgroundColor(getResources().getColor(R.color.transparent));
                mBtn5.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                typeFlag[4] = 0;
            }
        });

    }

    /**
     * ????????????
     *
     * @param sourceUri
     */
    private void startUCrop(Uri sourceUri) {
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        UCrop.Options options = new UCrop.Options();
        //????????????????????????????????????
        Uri destinationUri = Uri.fromFile(outFile);
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);//??????????????????????????????uri,??????????????????????????????uri
        uCrop.withAspectRatio(1, 1);//??????????????????????????????
        //???????????????????????????,??????,??????????????????
        options.setAllowedGestures(com.yalantis.ucrop.UCropActivity.ALL, com.yalantis.ucrop.UCropActivity.NONE, com.yalantis.ucrop.UCropActivity.ALL);
        options.setToolbarTitle("???????????????");//?????????????????????
        options.setCropGridStrokeWidth(2);//??????????????????????????????(?????????????????????????????????????????????)
        //options.setCropFrameStrokeWidth(1);//????????????????????????
        options.setMaxScaleMultiplier(3);//????????????????????????
        //options.setHideBottomControls(true);//?????????????????????
        options.setShowCropGrid(true);  //??????????????????????????????
        //options.setOvalDimmedLayer(true);//??????????????????????????????
        options.setShowCropFrame(true); //??????????????????????????????(true???????????????)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//????????????????????????????????????
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//?????????????????????
        options.setToolbarColor(Color.parseColor("#000000")); // ?????????????????????
        options.setStatusBarColor(Color.parseColor("#000000"));//?????????????????????
        options.setCropGridColor(Color.parseColor("#ffffff"));//???????????????????????????
        options.setCropFrameColor(Color.parseColor("#ffffff"));//????????????????????????
        uCrop.withOptions(options);
        /*//???????????????????????????
        Uri destinationUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/myxmpp/" + "test1.jpg"));
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //????????????????????????????????????
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //??????toolbar??????
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.orange2));
        //?????????????????????
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.orange2));
        //????????????????????????
        options.setFreeStyleCropEnabled(true);
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//????????????????????????????????????
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//?????????????????????
        options.setToolbarColor(Color.parseColor("#000000")); // ?????????????????????
        options.setStatusBarColor(Color.parseColor("#000000"));//?????????????????????
        options.setCropGridColor(Color.parseColor("#ffffff"));//???????????????????????????
        options.setCropFrameColor(Color.parseColor("#ffffff"));//????????????????????????
        //options.setShowCropFrame(false); //??????????????????????????????(true???????????????)
        uCrop.withOptions(options);*/
        uCrop.start(getActivity());
    }



}