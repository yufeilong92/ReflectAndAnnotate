package com.example.myapplication.reflect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ActivityReflectUtils {

    public static void injntActivity(Activity activity) {
        Class<? extends Activity> cla = activity.getClass();

        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();

        if (extras == null) {
            return;
        }
        Field[] fields = cla.getDeclaredFields();

        for (Field field : fields) {
            boolean isExsit = field.isAnnotationPresent(ActivityValue.class);
            if (isExsit) {
                ActivityValue annotation = field.getAnnotation(ActivityValue.class);
                String key = TextUtils.isEmpty(annotation.value()) ? field.getName() : annotation.value();
                Object obj = extras.get(key);
                Class<?> type = field.getType().getComponentType();
                if (field.getType().isArray() &&null!=type&& Parcelable.class.isAssignableFrom(type)) {
                    Object[] objs = (Object[]) obj;
                    Class<? extends Object[]> clas = (Class<? extends Object[]>) field.getType();
                    obj = Arrays.copyOf(objs, objs.length, clas);
                }

                field.setAccessible(true);
                try {
                    field.set(activity, obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }

        }


    }


}
