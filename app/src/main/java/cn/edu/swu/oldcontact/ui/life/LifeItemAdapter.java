package cn.edu.swu.oldcontact.ui.life;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeItem;

public class LifeItemAdapter extends RecyclerView.Adapter<LifeItemAdapter.ViewHolder> {

    public List<LifeItem> mItemList;
    private Context mContext;
    private Activity mActivity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.headImg)
        ImageView headImg;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.likeNum)
        TextView likeNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public LifeItemAdapter(List<LifeItem> itemList, Context c, Activity activity) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = c;
        mActivity = activity;
    }

    @NonNull
    @Override
    public LifeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_life, parent, false);
        LifeItemAdapter.ViewHolder holder = new LifeItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LifeItemAdapter.ViewHolder holder, int position) {
        LifeItem item = mItemList.get(position);

//        Glide.with(context)
//                .load(R.drawable.img_test)
//                .transform(new CenterCrop(context), new GlideRoundTransform(context,10))
//                .into(holder.img);
        holder.title.setText(item.getTitle());
        holder.img.setImageURI(Uri.parse(item.getImgIdList().get(0)));
        holder.headImg.setImageBitmap(BitmapFactory. decodeResource (mContext.getResources(),item.getHeadImgId()));
        holder.likeNum.setText(String.valueOf(item.getLikeNum()));
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(mActivity, LifeContentActivity.class);
            intent.putExtra("LifeItemId",String.valueOf(item.getId()));
            mActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }



}