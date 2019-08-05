package com.myapp.mysampelreciever

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {

    var REQUESTCODE = 1000
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //모든 위험 권한을 자동 부여하도록 하는 메서드 호출하기
        AutoPermissions.loadAllPermissions(this, 101)

        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=PackageManager.PERMISSION_GRANTED){
            //권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                alert {
                    yesButton {
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.RECEIVE_SMS), 1002)

                        toast("yes")
                    }
                    noButton {

                        toast("no ")
                    }
                }.show()
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1001)
            }
        }*/

        

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        AutoPermissions.parsePermissions(this, requestCode, permissions, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {

        Toast.makeText(this, "permission denide " + permissions.size, Toast.LENGTH_LONG).show()
        Log.i("TAG", "onDenied 메서드 호출됨" + requestCode)
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {

        Toast.makeText(this, "permission Granted " + permissions.size, Toast.LENGTH_LONG).show()
        Log.i("TAG", "onGranted 메서드 호출됨" + requestCode)
    }
}
