package cn.edu.swu.oldcontact.ui.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ServiceItem;


public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.ViewHolder> {

    private List<ServiceItem> mItemList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView headImg,leftImg,rightImg;
        TextView leftChat,rightChat,leftTime,rightTime;
        RelativeLayout leftLayout,rightLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headImg = itemView.findViewById(R.id.headImg);
            leftChat = itemView.findViewById(R.id.left_chat_data);
            rightChat = itemView.findViewById(R.id.right_chat_data);
            leftLayout = itemView.findViewById(R.id.left_msg);
            rightLayout = itemView.findViewById(R.id.right_msg);
            leftTime = itemView.findViewById(R.id.left_chat_time);
            rightTime = itemView.findViewById(R.id.right_chat_time);
            leftImg = itemView.findViewById(R.id.left_chat_img);
            rightImg = itemView.findViewById(R.id.right_chat_img);

        }
    }

    public ServiceItemAdapter(List<ServiceItem> itemList) { //构造函数绑定数据源
        mItemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_service, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceItem item = mItemList.get(position);

        if(item.getFlag() == 1){//用户,右边显示
            if(item.getBitmap() != null){
                holder.rightImg.setImageBitmap(item.getBitmap());
                holder.rightChat.setVisibility(View.GONE);
                holder.rightImg.setVisibility(View.VISIBLE);
            }else {
                holder.rightChat.setText(item.getContent());
                holder.rightImg.setVisibility(View.GONE);
                holder.rightChat.setVisibility(View.VISIBLE);
            }
            holder.rightTime.setText(item.getTime());
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
        }else{
            holder.leftChat.setText(item.getContent());
            holder.leftTime.setText(item.getTime());
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }





}
