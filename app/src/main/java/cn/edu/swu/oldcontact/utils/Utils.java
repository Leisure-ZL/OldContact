package cn.edu.swu.oldcontact.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cn.edu.swu.oldcontact.R;

public class Utils {

    public static Uri idToUri(Context context,int id){
        Resources r =context.getResources();
        Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(id) + "/"
                + r.getResourceTypeName(id) + "/"
                + r.getResourceEntryName(id));
        return uri;
    }

}
