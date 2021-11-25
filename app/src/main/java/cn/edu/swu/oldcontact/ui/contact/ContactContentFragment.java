package cn.edu.swu.oldcontact.ui.contact;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.edu.swu.oldcontact.IApp;
import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.ContactContentItem;
import cn.edu.swu.oldcontact.javaBean.User;
import cn.edu.swu.oldcontact.utils.DB;


public class ContactContentFragment extends Fragment {

    RecyclerView mContentRecycler;
    RecyclerView mCareRecycler;
    int mIndex;

    public ContactContentFragment(int index) {
        mIndex = index;
    }

    public ContactContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_content, container, false);
        mContentRecycler = view.findViewById(R.id.contact_content_recycler);
        mCareRecycler = view.findViewById(R.id.contact_care_recycler);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        IApp app = (IApp) getActivity().getApplication();
        List<ContactContentItem> itemList1 = app.db.contactContentDao().getListByIndex(0);
        Collections.reverse(itemList1);//倒叙
        List<ContactContentItem> itemList2 = app.db.contactContentDao().getListByIndex(1);
        Collections.reverse(itemList2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mContentRecycler.setLayoutManager(layoutManager);
        ContactContentAdapter adapter;
        switch (mIndex){
            case 0:
                adapter = new ContactContentAdapter(itemList1,getContext());
                break;
            case 1:
                adapter = new ContactContentAdapter(itemList2,getContext());
                break;
            default:
                adapter = new ContactContentAdapter(itemList1,getContext());
                break;

        }
        mContentRecycler.setAdapter(adapter);

        List<User> itemList = app.db.userDao().getAll();

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        mCareRecycler.setLayoutManager(layoutManager2);
        ContactCareAdapter adapter2 = new ContactCareAdapter(itemList,getContext());
        mCareRecycler.setAdapter(adapter2);

    }



}