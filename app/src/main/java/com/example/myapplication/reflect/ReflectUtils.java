package com.example.myapplication.reflect;

import android.app.Activity;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;

import com.example.myapplication.annotate.AnnotateFindView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 反射工具类
 */
public class ReflectUtils {

    public static void injectView(Activity activity) {
        //获取class
        Class<? extends Activity> cla = activity.getClass();
        //获取对应class下所有属性
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            //判断是否有我们注解
            boolean isEsite = field.isAnnotationPresent(AnnotateFindView.class);
            if (isEsite) {
                AnnotateFindView annotation = field.getAnnotation(AnnotateFindView.class);
                int idResource = annotation.value();
                View view = activity.findViewById(idResource);
                field.setAccessible(true);
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void injectViewBackground(Activity activity, @ColorInt int colorResource) {
        //获取class
        Class<? extends Activity> cla = activity.getClass();
        //获取对应class下所有属性
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            //判断是否有我们注解
            boolean isEsite = field.isAnnotationPresent(AnnotateFindView.class);
            if (isEsite) {
                AnnotateFindView annotation = field.getAnnotation(AnnotateFindView.class);
                int idResource = annotation.value();
                View view = activity.findViewById(idResource);
                view.setBackgroundColor(colorResource);
                field.setAccessible(true);
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void injectViewBackgroundOnly(Activity activity, @ColorInt int colorResource, @IdRes int id) {
        //获取class
        Class<? extends Activity> cla = activity.getClass();
        //获取对应class下所有属性
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            //判断是否有我们注解
            boolean isEsite = field.isAnnotationPresent(AnnotateFindView.class);
            if (isEsite) {
                AnnotateFindView annotation = field.getAnnotation(AnnotateFindView.class);
                int idResource = annotation.value();
                View view = activity.findViewById(idResource);
                if (idResource == id) {
                    view.setBackgroundColor(colorResource);
                    field.setAccessible(true);
                    try {
                        field.set(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
