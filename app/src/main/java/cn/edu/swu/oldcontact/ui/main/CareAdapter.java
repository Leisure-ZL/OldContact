package cn.edu.swu.oldcontact.ui.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.User;

public class CareAdapter extends RecyclerView.Adapter<CareAdapter.ViewHolder> {
    private List<User> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }
    }

    public CareAdapter(List<User> itemList, Context context) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_care, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User item = mItemList.get(position);
        holder.img.setImageURI(Uri.parse(item.getHeadImg()));
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}