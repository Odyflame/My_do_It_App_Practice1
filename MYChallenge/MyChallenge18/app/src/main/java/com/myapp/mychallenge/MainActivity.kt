package com.myapp.mychallenge

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.picture_item.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //시스템상 필요한 프로퍼티
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000
    lateinit var task : BackgroundTask
    var isCheck = false
    var isRunning = false
    var selected :Int = 0
    var pictureCount = 0

    //카드뷰 포함
    lateinit var pannel1 : PictureItemView
    var dateFormet = SimpleDateFormat("yyyy-MM-dd HH:mm")

    //애니메이션
    lateinit var translateLeft : Animation
    lateinit var translateRight : Animation

    lateinit var pictures :ArrayList<ImageInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //권한이 허용되지 않음

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                alert("사진 정보를 얻으려면 외부 저장소 권한이 필요합니다", "권한이  필요한 이유"){
                    yesButton {
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                    }
                    noButton {  }
                }.show()
            }
            else{
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
            }
        }else{
            isCheck = true

            pannel1 = PictureItemView(this)
            pannel1.textView.text = "사진이름"
            pannel1.textView2.text = "일시"
            container.addView(pannel1)

            translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left)
        }

        button.setOnClickListener {
            if(isCheck){
                isRunning=true
                pictures = getAllPhotos()
                task = BackgroundTask()
                task.execute()
            }
        }
        button2.setOnClickListener {
            if(isCheck){
                task.cancel(true)
            }
        }
    }

    inner class BackgroundTask : AsyncTask<Int, Int, Int>() {

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg p0: Int?): Int {
            while(!isCancelled){
                var info :ImageInfo = pictures.get(selected)

                pannel1.textView.text = info.displayName
                pannel1.textView2.text = info.dateAdded
                pannel1.imageView.setImageResource(info.path as Int)

                pannel1.startAnimation(translateLeft)

                selected++
                if(selected > pictureCount){
                    selected = 0
                }
                textView.text = "$selected" + " / " + pictureCount + "개"
            }

            try{
                Thread.sleep(3000)
            }catch(ex : Exception){}
            return 1
        }

    }

    fun getAllPhotos() : ArrayList<ImageInfo> {

        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.Media.DATE_TAKEN + " DESC")

        //var uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val result = ArrayList<ImageInfo>()

        cursor?.let{
            while(cursor.moveToNext()){
                val uri = cursor.getString(cursor.getColumnIndexOrThrow((MediaStore.Images.Media.DATA)))
                Log.d("MainActivity", uri)

                var columDateIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)
                var addedDate = dateFormet.format(Date( cursor.getString((columDateIndex))))

                if(!TextUtils.isEmpty(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)))){
                    var info = ImageInfo(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)),
                        addedDate)
                    result.add(info)
                }
                pictureCount++
            }
        }

        textView.text = "$selected" + " / " + pictureCount + "개"

        return result
    }
}
