package com.example.cxc.playandroidkotlin.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cxc.playandroidkotlin.R
import com.example.cxc.playandroidkotlin.toast
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_sign.*
import java.io.File
import java.io.FileOutputStream
import java.util.function.Consumer
import java.util.jar.Manifest

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        RxPermissions(this).request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe{
                    if (it) {
                        toast("permission ok")
                    }
                }

        btn_save.setOnClickListener {

            var bitmap = signature_pad.signatureBitmap
            var dir = File(Environment.getExternalStorageDirectory(), "apngdir")
            if (!dir.exists()) {
                dir.mkdir()
            }
            var f = File(dir, "a.png")

            var fos = FileOutputStream(f)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.flush()
            fos.close()

            Glide.with(this.applicationContext).load(f).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(iv)
        }

        btn_clear.setOnClickListener{
            signature_pad.clear()
        }
    }
}