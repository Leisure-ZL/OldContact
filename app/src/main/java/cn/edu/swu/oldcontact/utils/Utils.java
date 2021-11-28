package cn.edu.swu.oldcontact.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.edu.swu.oldcontact.R;
import cn.edu.swu.oldcontact.javaBean.User;

public class Utils {

    public static Uri idToUri(Context context,int id){
        Resources r =context.getResources();
        Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(id) + "/"
                + r.getResourceTypeName(id) + "/"
                + r.getResourceEntryName(id));
        return uri;
    }

    public static void userListSort(List<User> list){
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User details1, User details2) {
                //排序规则，按照价格由大到小顺序排列("<"),按照价格由小到大顺序排列(">"),
                if(details1.getIntegral() < details2.getIntegral())
                    return 1;
                else {
                    return -1;
                }
            }
        };
        Collections.sort(list, comparator);
    }

}
