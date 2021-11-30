package cn.edu.swu.oldcontact.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;

public class IndexActivity extends AppCompatActivity {

    String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE};
    //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if(getSharedPreferences("isNew",MODE_PRIVATE).getBoolean("isNew",true)){
            showDialog();
        }else {
            initPermission();
        }
    }

    private void initPermission() {

        mPermissionList.clear();//清空没有通过的权限

        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        }else{
            //说明权限都已经通过，可以做你想做的事情去
            Timer timer = new Timer();
            TimerTask tast = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            };
            timer.schedule(tast, 2000);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {

            } else {
                //全部权限通过，可以进行下一步操作。。。
                Timer timer = new Timer();
                TimerTask tast = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                timer.schedule(tast, 2000);
            }
        }

    }



    private void showDialog() {
        final Dialog mDialog;
        mDialog = new Dialog(this, R.style.Teldialog);
        mDialog.setContentView(R.layout.dialog_index);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.show();
        TextView content = mDialog.findViewById(R.id.tv_content);

        String str = "请您务必审慎阅读、充分理解“用户协议”和“隐私政策”各条款，包括但不限于：" +
                "为了向您提供交易相关基本功能，我们会收集、使用必要的信息。你可阅读" +
                "《用户协议》" + "和" +
                "《隐私政策》" +
                "了解详细信息。如您同意，请点击“同意”接受我们的服务。";
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(str);
        //第一个出现的位置
        final int start = str.indexOf("《");
        ssb.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                //用户服务协议点击事件
                Intent intent = new Intent(IndexActivity.this,ProtocolActivity.class);
             //   intent.putExtra("msg","1");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文件颜色
                ds.setColor(getResources().getColor(R.color.green));
                // 去掉下划线
                ds.setUnderlineText(false);
            }

        }, start, start + 6, 0);

        //最后一个出现的位置
        final int end = str.lastIndexOf("《");
        ssb.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                //隐私协议点击事件
                Intent intent = new Intent(IndexActivity.this,ProtocolActivity.class);
                intent.putExtra("msg","2");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文件颜色
                ds.setColor(getResources().getColor(R.color.green));
                // 去掉下划线
                ds.setUnderlineText(false);
            }

        }, end, end + 6, 0);
        content.setMovementMethod(LinkMovementMethod.getInstance());
        content.setText(ssb, TextView.BufferType.SPANNABLE);
        //设置点击后的背景颜色为透明
        content.setHighlightColor(ContextCompat.getColor(this, R.color.transparent));

        mDialog.findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            mDialog.dismiss();
            finish();
        });
        mDialog.findViewById(R.id.tv_sure).setOnClickListener(v -> {
            mDialog.dismiss();
            //更改状态，同意下次进入软件则不再弹出弹框
            SharedPreferences sp = getSharedPreferences("isNew",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isNew",false);
            editor.apply();

            initPermission();
        });
    }


}