package com.example.myapplication.annotate;


import androidx.annotation.IntDef;

import com.example.myapplication.MainActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IntDef({MainActivity.RESOUCES_ONE,MainActivity.RESOUCES_TWO})
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotateValue {

}
