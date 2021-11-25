package cn.edu.swu.oldcontact.ui.life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.MainActivity;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.ui.life.LifeContentFragment;

public class LifeContentActivity extends AppCompatActivity {

    @BindView(R.id.headImg)
    ImageView mHeadImg;
    @BindView(R.id.username)
    TextView mUsername;
    @BindView(R.id.back)
    ImageView mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_content);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        IApp app = (IApp)getApplication();
        Intent intent = getIntent();
        String IdStr = intent.getStringExtra("LifeItemId");
        int Id = Integer.parseInt(IdStr);
        LifeItem item = app.db.lifeDao().getItemById(Id);

        //Update Ui
        Bitmap bitmap = BitmapFactory. decodeResource (getResources(),item.getHeadImgId());
        mHeadImg.setImageBitmap(bitmap);
        mUsername.setText(item.getUsername());

        LifeContentFragment lifeContentFragment = new LifeContentFragment(Id);
        replaceFragment(lifeContentFragment);

        mBack.setOnClickListener(v->{
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}