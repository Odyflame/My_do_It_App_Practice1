package com.myapp.mycontacts

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class MainActivity : AppCompatActivity() {

    private val REQUEST_READ_EXTERNAL_STORAGE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                //권한이 허용되지 않음
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){
                    //사전에 권한이 거부되었을 때 반응
                    alert("사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다", "권한이 필요한 이유"){
                        yesButton {
                            //권한 요청
                            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_EXTERNAL_STORAGE)
                        }
                        noButton {

                        }
                    }.show()
                }else{
                    //사전에 권한이 거부되지 않았을 때
                    //권한 요청
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_EXTERNAL_STORAGE)
                }
            }else{
                //권한 허용됨
                chooseContacts()
            }

        }
    }

    fun chooseContacts(){
        var contactPickedIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(contactPickedIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 101) {
            if(resultCode == Activity.RESULT_OK){
                try{
                    var contactsUri = data!!.data
                    var id = contactsUri!!.lastPathSegment

                    getContacts(id)
                }catch(ex :Exception){
                    ex.printStackTrace()
                }
            }
        }
    }

    fun getContacts(id : String?){
        var cursor :Cursor? = null
        var name = ""

        try {
            cursor = contentResolver.query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + "=?",
                arrayOf(id), null
            )

            if (cursor!!.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
                println("Name : $name")

                // 모든 칼럼 이름 확인
                val columns = cursor.getColumnNames()
                for (column in columns) {
                    val index = cursor.getColumnIndex(column)
                    val columnOutput = "#" + index + " -> [" + column + "] " + cursor.getString(index)
                    println(columnOutput)
                }

                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun println(data : String){
        textView.append(data + "\n")
    }

}
