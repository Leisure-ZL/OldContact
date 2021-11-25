package cn.edu.swu.oldcontact.ui.main;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactClassifyItem;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {
    private List<ContactClassifyItem> mItemList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView mItemView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public ClassifyAdapter(List<ContactClassifyItem> itemList) {
        mItemList = itemList;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_classify, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactClassifyItem item = mItemList.get(position);
        holder.mItemView.setText(item.getTitle());

        if(item.getFlag() == 0){
            holder.mItemView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }else {
            holder.mItemView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

        holder.itemView.setOnClickListener(v->{

            if (listener != null) {
                listener.onClick(position);
            }

            for(int i=0;i<mItemList.size();i++){
                mItemList.get(i).setFlag(0);
            }
            mItemList.get(position).setFlag(1);
            notifyDataSetChanged();
        });


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }





}
