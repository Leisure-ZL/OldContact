package cn.edu.swu.oldcontact.ui.contact;

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

public class ContactCareAdapter extends RecyclerView.Adapter<ContactCareAdapter.ViewHolder> {
    private List<User> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView username;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
            username = view.findViewById(R.id.username);
        }
    }

    public ContactCareAdapter(List<User> itemList,Context context) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_group_contact, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User item = mItemList.get(position);
        holder.img.setImageURI(Uri.parse(item.getHeadImg()));
        holder.username.setText(item.getUsername());
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}