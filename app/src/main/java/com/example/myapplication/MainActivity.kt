package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Vo.UserData
import com.example.myapplication.annotate.AnnotateFindView
import com.example.myapplication.annotate.AnnotateValue
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.reflect.ReflectUtils

class MainActivity : AppCompatActivity() {
    companion object {
        const val RESOUCES_ONE = 1;
        const val RESOUCES_TWO = 2;
    }

    @AnnotateFindView(value = R.id.btn_one)
    var btn: Button? = null

    @AnnotateFindView(value = R.id.btn_two)
    var btnOne: Button? = null

    @AnnotateFindView(value = R.id.btn_three)
    var btnTwo: Button? = null

    lateinit var mainActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        setResouce()
        setAnnotate()
        setListener()
    }

    private fun setListener() {
        mainActivity.btnOne.setOnClickListener {
            ReflectUtils.injectViewBackground(this, Color.RED)
        }
        mainActivity.btnTwo.setOnClickListener {
            ReflectUtils.injectViewBackground(this, Color.BLUE)
        }
        mainActivity.btnThree.setOnClickListener {
            ReflectUtils.injectViewBackgroundOnly(this,Color.GREEN,R.id.btn_three)
        }
        mainActivity.btnStartIntent.setOnClickListener {
            val vo = UserData().apply {
                age = 2
                name = "小马哥"
                cla = "一年级二班"
            }
            val intent = Intent(this, ContentActivity::class.java).apply {
                putExtra("name", "小明")
                putExtra("age", 1)
                putExtra("vo", vo)
            }
            startActivity(intent)
        }
    }

    private fun setAnnotate() {
        ReflectUtils.injectView(this)
    }

    private fun setResouce() {
        setResourceValue(RESOUCES_ONE)
    }

    //标志注解
    fun setResourceValue(@AnnotateValue id: Int) {

    }


}