package cn.edu.swu.oldcontact.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.utils.Utils;

public class RankBottomSheet extends BottomSheetDialogFragment {

    RecyclerView mRankRecycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_sheet_contact, container, false);
        mRankRecycler = view.findViewById(R.id.rank_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        IApp app = (IApp) getActivity().getApplication();
        List<User> userList = app.db.userDao().getAll();
        Utils.userListSort(userList);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        mRankRecycler.setLayoutManager(layoutManager3);
        ContactRankAdapter adapter3 = new ContactRankAdapter(userList,getContext());
        mRankRecycler.setAdapter(adapter3);

    }
}
