package ru.indasan.semafor

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*
// Ver 1.0
class MainActivity : Activity() {
    var count:Int = 0;
    var imageArray:IntArray = intArrayOf(R.drawable.semafor_green,R.drawable.semafor_yellow,R.drawable.semafor_red)
    var timer:Timer? = null
    var isRun:Boolean = false

    var imgSemafor:ImageView? = null
    var imgButton: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgSemafor = findViewById(R.id.img_semafor)
        imgButton = findViewById(R.id.imageButton)


    }

    fun motionStartStop(view:View){
        view as ImageButton

        if(!isRun){
            //Прикрутили таймер
            startStop()

            view.setImageResource(R.drawable.button_stop)
            isRun = true
        }else{
            imgSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            isRun = false
            count = 0
        }

    }
    fun startStop(){
        timer = Timer()
        timer?.schedule(object:TimerTask(){
            override fun run() {
                runOnUiThread{
                    imgSemafor?.setImageResource(imageArray[count])
                    count++
                    if (count == 3) count = 0
                }
            }

        },0,1000)
    }
}