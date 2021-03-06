package cn.edu.swu.oldcontact.ui.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactActItem;
import cn.edu.swu.oldcontact.utils.GlideRoundTransform;

public class ContactActAdapter extends RecyclerView.Adapter<ContactActAdapter.ViewHolder> {
    public List<ContactActItem> mItemList;
    Context context;
    Activity activity;
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView mImg;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.distance)
        TextView mDis;
        @BindView(R.id.allNum)
        TextView mAllNum;
        @BindView(R.id.alreadyNum)
        TextView mAlreadyNum;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public ContactActAdapter(List<ContactActItem> itemList, Context con,Activity act) { //构造函数绑定数据源
        mItemList = itemList;
        context = con;
        activity = act;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_content, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactActItem item = mItemList.get(position);

        Glide.with(context)
                .load(item.getBgImg())
                .transform(new CenterCrop(context), new GlideRoundTransform(context,10))
                .into(holder.mImg);
        holder.mTitle.setText(item.getTitle());
        holder.mAllNum.setText(String.valueOf(item.getAllNum()));
        holder.mAlreadyNum.setText(String.valueOf(item.getAlreadyNum()));
        holder.mDis.setText(item.getDist());


        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(activity,ContactActActivity.class);
            intent.putExtra("actId",String.valueOf(item.getId()));
            activity.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }






}
