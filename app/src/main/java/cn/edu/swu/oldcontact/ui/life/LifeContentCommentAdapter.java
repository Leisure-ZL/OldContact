package cn.edu.swu.oldcontact.ui.life;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;

public class LifeContentCommentAdapter extends RecyclerView.Adapter<LifeContentCommentAdapter.ViewHolder> {
    private List<LifeContentCommentItem> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headImg)
        ImageView mHeadImg;
        @BindView(R.id.username)
        TextView mUsername;
        @BindView(R.id.content)
        TextView mContent;
        @BindView(R.id.like_num)
        TextView mLikeNum;
        @BindView(R.id.like_icon)
        ImageView mLikeIcon;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public LifeContentCommentAdapter(List<LifeContentCommentItem> itemList, Context context) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_comment_life, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LifeContentCommentItem item = mItemList.get(position);
        Bitmap bitmap = BitmapFactory.decodeResource (mContext.getResources(),item.getHeadImg());
        holder.mHeadImg.setImageBitmap(bitmap);
        holder.mUsername.setText(item.getUsername());
        holder.mContent.setText(item.getContent());
        holder.mLikeNum.setText(String.valueOf(item.getLike()));

        holder.mLikeIcon.setOnClickListener(v->{
            if(item.getClickFlag() == 0){
                item.setClickFlag(1);
                int cur = item.getLike() + 1;
                holder.mLikeNum.setText(String.valueOf(cur));
                Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.like);
                holder.mLikeIcon.setImageBitmap(bm);
            }else {
                item.setClickFlag(0);
                holder.mLikeNum.setText(String.valueOf(item.getLike()));
                Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.notlike);
                holder.mLikeIcon.setImageBitmap(bm);
            }

        });
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}