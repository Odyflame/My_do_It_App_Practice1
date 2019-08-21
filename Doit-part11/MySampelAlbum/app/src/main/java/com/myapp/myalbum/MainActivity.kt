package com.myapp.myalbum

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    private val REQUEST_READ_EXTERNAL_STORAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                //사전에 권한이 거부되었을 때 반응
                alert("사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다", "권한이 필요한 이유"){
                    yesButton {
                        //권한 요청
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                    }
                    noButton {

                    }
                }.show()
            }else{
                //사전에 권한이 거부되지 않았을 때
                //권한 요청
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
            }
        }else{
            //권한 허용됨
                openGalelry()
            }

        }

    }

    fun openGalelry(){
        var intent = Intent()
        intent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        //setType이 mimeㅏ타입이 image로 시작하는 데이터를 가져오라는 의미가 된다.
        //startActivityforResult메서드를 호출하면서 이 인텐트 객체를 파라미터로 전달하면 앨범에서 사진을 선택할 수 있는 화면을 띄어주게 된다.
        startActivityForResult( intent, REQUEST_READ_EXTERNAL_STORAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode==1000){
            if(resultCode == Activity.RESULT_OK){
                var fildUri = data!!.data

                var resolver = contentResolver

                try{
                    var instream = resolver.openInputStream(fildUri!!)
                    var imgBitmap = BitmapFactory.decodeStream(instream)
                    imageView.setImageBitmap(imgBitmap)
                    instream!!.close()
                }catch(ex : Exception){
                    ex.printStackTrace()
                }
            }
        }
    }
}
