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
import cn.edu.swu.oldcontact.javaBean.Group;

public class ContactGroupAdapter extends RecyclerView.Adapter<ContactGroupAdapter.ViewHolder> {
    private List<Group> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
      //  TextView username;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
         //   username = view.findViewById(R.id.username);
        }
    }

    public ContactGroupAdapter(List<Group> itemList, Context context) { //构造函数绑定数据源
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
        Group item = mItemList.get(position);
        holder.img.setImageURI(Uri.parse(item.getImg()));
    //    holder.username.setText(item.getName());
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}