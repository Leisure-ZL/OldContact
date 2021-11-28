package cn.edu.swu.oldcontact.ui.life;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeItem;


public class LifeFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.life_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout mRefresh;
//    @BindView(R.id.life_classify_recycler)
//    RecyclerView mClassifyRecycler;

    List<LifeItem> items;
    public LifeItemAdapter adapter;
    StaggeredGridLayoutManager gridLayoutManager;

    public LifeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IApp app = (IApp) getActivity().getApplication();

//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
//        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
//        mClassifyRecycler.setLayoutManager(layoutManager1);
//        LifeClassifyAdapter classifyAdapter = new LifeClassifyAdapter(app.mClassifyList);
//        mClassifyRecycler.setAdapter(classifyAdapter);

        for(int i=0;i<app.mClassifyList.size();i++){
            if (app.mClassifyList.get(i).getFlag() == 1){
                items = app.db.lifeDao().getItemByIndex(i);
                break;
            }
        }
        adapter = new LifeItemAdapter(items,getContext(),getActivity());
        Collections.reverse(items);
        gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
//        classifyAdapter.setOnItemClickListener(new LifeClassifyAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                items = app.db.lifeDao().getItemByIndex(position);
//                Collections.reverse(items);
//                adapter.mItemList = items;
//                adapter.notifyDataSetChanged();
//            }
//        });


        mRefresh.setRefreshHeader(new ClassicsHeader(getContext()));
        mRefresh.setRefreshFooter(new ClassicsFooter(getContext()));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}