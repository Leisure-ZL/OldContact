package cn.edu.swu.oldcontact.utils;

import android.net.Uri;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.swu.oldcontact.javaBean.LifeItem;

public class ItemConverter {

    @TypeConverter
    public String LifeItemToString(List<String> list ){
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public List<String> StringToLifeItem(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, listType);
    }

}
