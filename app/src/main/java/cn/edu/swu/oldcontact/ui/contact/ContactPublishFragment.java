package cn.edu.swu.oldcontact.ui.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.ui.main.MainActivity;
import cn.edu.swu.oldcontact.R;


public class ContactPublishFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.btn1)
    TextView mBtn1;
    @BindView(R.id.btn2)
    TextView mBtn2;
    @BindView(R.id.btn3)
    TextView mBtn3;
    @BindView(R.id.btn4)
    TextView mBtn4;
    @BindView(R.id.btn5)
    TextView mBtn5;
    @BindView(R.id.act_name)
    EditText mActName;
    @BindView(R.id.act_place)
    EditText mActPlace;
    @BindView(R.id.act_person_num)
    public TextView mActPersonNum;
    @BindView(R.id.act_phone_num)
    EditText mActPhoneNum;
    @BindView(R.id.act_note)
    EditText mActNote;
    @BindView(R.id.publish_btn)
    TextView mPubBtn;
    @BindView(R.id.img)
    public ImageView mImg;
    @BindView(R.id.location_text)
    TextView mLocation;

    int[] typeFlag = {0,0,0,0,0};
    PickNumDialog dialog;
    public ContactActItem item = new ContactActItem();

    public ContactPublishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_publish, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IApp app = (IApp) getActivity().getApplication();
        mLocation.setText(app.mCurLocation);

        selectType();
        mActPersonNum.setOnClickListener(v->{
            dialog = new PickNumDialog();
            dialog.show(getFragmentManager(),"dialog");
        });
        mImg.setOnClickListener(v->{
            requestPower();
            Intent intent = new Intent();
            intent.setType("image/*");// 开启Pictures画面Type设定为image
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, 1);
        });

        mPubBtn.setOnClickListener(v->{
            for(int i=0;i<typeFlag.length;i++ ){
                if(typeFlag[i] == 1){
                    item.setIndex(i);
                }
            }
            item.setTitle(mActName.getText().toString());
            item.setAlreadyNum(0);
            item.setAllNum(Integer.parseInt(mActPersonNum.getText().toString()));
            item.setActPhoneNum(mActPhoneNum.getText().toString());
            item.setActPlace(mActPlace.getText().toString());
            item.setNote(mActNote.getText().toString());
            //TODO: distance??


            app.db.contactContentDao().insert(item);
            getActivity().onBackPressed();
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){//相册选
            Uri sourceUri = data.getData();
            startUCrop(sourceUri);
            ((MainActivity)getActivity()).contactPubFlag = 1;
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
        mBtn4.setOnClickListener(v->{
            if(typeFlag[3] == 0){
                mBtn4.setBackground(getResources().getDrawable(R.drawable.layout_border));
                mBtn4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                typeFlag[3] = 1;
            }else {
                mBtn4.setBackgroundColor(getResources().getColor(R.color.transparent));
                mBtn4.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                typeFlag[3] = 0;
            }
        });
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

    private void startUCrop(Uri sourceUri) {
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(outFile);
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(9, 16);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(com.yalantis.ucrop.UCropActivity.ALL, com.yalantis.ucrop.UCropActivity.NONE, com.yalantis.ucrop.UCropActivity.ALL);
        options.setToolbarTitle("移动和缩放");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        //options.setCropFrameStrokeWidth(1);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        //options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(true);  //设置是否显示裁剪网格
        //options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(true); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        /*//裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/myxmpp/" + "test1.jpg"));
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.orange2));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.orange2));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        //options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        uCrop.withOptions(options);*/
        uCrop.start(getActivity());
    }

    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}