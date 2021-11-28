package cn.edu.swu.oldcontact.ui.life;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;
import cn.edu.swu.oldcontact.utils.PagerLayoutManager;


public class LifeContentFragment extends Fragment {
    Unbinder unbinder;
    int mLifeId;

    @BindView(R.id.comment_num)
    TextView mCommentNum;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.time)
    TextView mTime;

    public LifeContentFragment() {
        // Required empty public constructor
    }

    public LifeContentFragment(int lifeId) {
        mLifeId = lifeId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life_content, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IApp app = (IApp) getActivity().getApplication();


        LifeItem lifeItem = app.db.lifeDao().getItemById(mLifeId);
        List<String> imgList = lifeItem.getImgIdList();
        RecyclerView imgRecyclerView = (RecyclerView) view.findViewById(R.id.img_recycler);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        PagerLayoutManager layoutManager = new PagerLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        imgRecyclerView.setLayoutManager(layoutManager);
        LifeContentImgAdapter adapter = new LifeContentImgAdapter(imgList,getContext());
        imgRecyclerView.setAdapter(adapter);

        List<LifeContentCommentItem> lifeCommentList = app.db.lifeCommentDao().getItemById(mLifeId);
        RecyclerView commentRecyclerView = (RecyclerView) view.findViewById(R.id.comment_recycler);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
        //解决数据加载不完的问题
        commentRecyclerView.setNestedScrollingEnabled(false);
        commentRecyclerView.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        commentRecyclerView.setFocusable(false);
        LifeContentCommentAdapter adapter2 = new LifeContentCommentAdapter(lifeCommentList,getContext());
        commentRecyclerView.setAdapter(adapter2);

        //Update Ui
        mCommentNum.setText("共"+String.valueOf(lifeCommentList.size())+"条评论");
        mTitle.setText(lifeItem.getTitle());
        mContent.setText(lifeItem.getContent());
        mTime.setText(lifeItem.getTime());




    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}