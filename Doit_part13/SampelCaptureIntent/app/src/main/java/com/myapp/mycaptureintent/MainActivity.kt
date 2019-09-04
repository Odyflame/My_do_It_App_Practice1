package com.myapp.mycaptureintent

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.pedro.library.AutoPermissions
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.io.File


class MainActivity : AppCompatActivity() {

    var file : File? = null
    var REQUEST_READ_EXTERNAL_STORAGE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                //권한이 허용되지 않음

                //shouldShowRequestPermissionRationale 메서드는 사용자가 전에권한 요청을 거부했는지를 반환한다. true를 반환하면 거부한 적이 있는것
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                    //이전에 이미 권한이 거부되었을 때 설명
                    alert ("사진 정보를 얻으려면 외부 저장소 권한이 필요합니다.", "권한이 필요한 이유"){
                        yesButton {
                            //권한 요청
                            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                        }
                        noButton {

                        }
                    }.show()

                }else{

                    //권한 요청
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                }
            }else{

                //권한이 이미 허용됨
                takePicture()

            }

        }

        AutoPermissions.Companion.loadAllPermissions(this, 101)
    }

    fun takePicture(){
        if(file==null) file= createFile()

        var fileUri = FileProvider.getUriForFile(this, "com.myapp.mycaptureintent.fileprovider", file!!)
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

        intent.resolveActivity(packageManager). let{
            startActivityForResult(intent, 101)
        }
        
    }

    fun createFile() : File{
        var filename = "capture.jpg"

        var storageDir = Environment.getExternalStorageDirectory()
        var outfile = File(storageDir, filename)

        return outfile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 101 && resultCode == RESULT_OK){
            var options =  BitmapFactory.Options()
            options.inSampleSize = 8
            var bitmap = BitmapFactory.decodeFile(file!!.absolutePath, options)

            imageView.setImageBitmap(bitmap)
        }
    }


}
