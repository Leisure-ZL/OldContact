package cn.edu.swu.oldcontact.ui.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;

public class ContactActActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.act_name)
    TextView mActName;
    @BindView(R.id.act_place)
    TextView mActPlace;
    @BindView(R.id.act_all_num)
    TextView mActAllNum;
    @BindView(R.id.act_already_num)
    TextView mActAlreadyNum;
    @BindView(R.id.act_phone_num)
    TextView mActPhoneNum;
    @BindView(R.id.act_note)
    TextView mActNote;
    @BindView(R.id.join_btn)
    TextView mJoinBtn;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.act_type)
    TextView mActType;

    int mActId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_act);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String idStr = intent.getStringExtra("actId");
        mActId = Integer.parseInt(idStr);
        init();

    }

    private void init() {
        IApp app = (IApp)getApplication();
        ContactActItem item = app.db.contactContentDao().getItemById(mActId);

        String[] typeArr = {"旅游","棋牌","运动","其他"};

        mActType.setText(typeArr[item.getIndex()]);
        mImg.setImageURI(Uri.parse(item.getBgImg()));
        mActName.setText(item.getTitle());
        mActNote.setText(item.getNote());
        mActPlace.setText(item.getActPlace());
        mActPhoneNum.setText(item.getActPhoneNum());
        mActAlreadyNum.setText(String.valueOf(item.getAlreadyNum()));
        mActAllNum.setText(String.valueOf(item.getAllNum()));

        mJoinBtn.setOnClickListener(v->{
            onBackPressed();
        });



    }



}