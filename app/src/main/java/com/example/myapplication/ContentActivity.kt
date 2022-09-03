package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Vo.UserData
import com.example.myapplication.databinding.ActivityContentBinding
import com.example.myapplication.reflect.ActivityReflectUtils
import com.example.myapplication.reflect.ActivityValue

class ContentActivity : AppCompatActivity() {
    @ActivityValue
    var name: String? = null;

    @ActivityValue
    var age: Int? = null;

    @ActivityValue(value = "vo")
    var dataVo: UserData? = null;
    lateinit var contentBinding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)
        setContent()
    }

    private fun setContent() {
        ActivityReflectUtils.injntActivity(this)
        contentBinding.tvContent.text = toString()

    }

    override fun toString(): String {
        return "ContentActivity(name=$name, age=$age, dataVo=$dataVo)"
    }

}