package cn.edu.swu.oldcontact.ui.life;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.swu.oldcontact.R;

public class LifePublishImgAdapter extends RecyclerView.Adapter<LifePublishImgAdapter.ViewHolder> {
    private List<String> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        public ViewHolder(View view) {
            super(view);
            mImg = view.findViewById(R.id.img);
        }
    }

    public LifePublishImgAdapter(List<String> itemList,Context context) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_img_pub, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mItemList.get(position);
        holder.mImg.setImageURI(Uri.parse(item));


    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}