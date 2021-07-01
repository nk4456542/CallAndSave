package com.example.phonecallsaveapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun number(v: View?){
        var e = findViewById<View>(R.id.pno) as EditText
        var b = v as Button
        e.setText(e.text.toString() + b.text)
    }

    fun save(v: View?)
    {
        val pno = findViewById(R.id.pno) as EditText
        var pname=findViewById<View>(R.id.pname) as EditText
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE)
        intent.putExtra(ContactsContract.Intents.Insert.NAME, pno.text.toString())
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, pname.text.toString())
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, responseCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, responseCode, data)

        if(requestCode==1)
        {
            if(responseCode== Activity.RESULT_OK)
            {
                Toast.makeText(this,"saved successfully",Toast.LENGTH_LONG).show()
            }
            if(responseCode== Activity.RESULT_CANCELED)
            {
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()
            }
        }

    }

    fun call(v: View?){
        var e = findViewById<View>(R.id.pno) as EditText
        var pno = e.text.toString()
        var i = Intent(Intent.ACTION_CALL)
        i.data = Uri.parse("tel:+91"+pno)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),0)
        startActivity(i);
    }

    fun del(v: View?){
        var e = findViewById<View>(R.id.pno) as EditText
        e.setText("")
    }


}