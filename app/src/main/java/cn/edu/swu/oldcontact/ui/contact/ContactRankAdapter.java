package cn.edu.swu.oldcontact.ui.contact;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.User;

public class ContactRankAdapter extends RecyclerView.Adapter<ContactRankAdapter.ViewHolder> {
    private List<User> mItemList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView integral;
        TextView rank;
        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            integral = view.findViewById(R.id.integral);
            rank = view.findViewById(R.id.rank);
        }
    }

    public ContactRankAdapter(List<User> itemList, Context context) { //构造函数绑定数据源
        mItemList = itemList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rank_contact, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User item = mItemList.get(position);
        switch (position){
            case 0:
                holder.rank.setTextColor(mContext.getResources().getColor(R.color.first));
                break;
            case 1:
                holder.rank.setTextColor(mContext.getResources().getColor(R.color.second));
                break;
            case 2:
                holder.rank.setTextColor(mContext.getResources().getColor(R.color.third));
                break;
            default:
                holder.rank.setTextColor(mContext.getResources().getColor(R.color.black));
                break;
        }
        holder.rank.setText(String.valueOf(position+1));
        holder.username.setText(item.getUsername());
        holder.integral.setText(String.valueOf(item.getIntegral()));
    }
    @Override
    public int getItemCount() {
        return mItemList.size();
    }


}
