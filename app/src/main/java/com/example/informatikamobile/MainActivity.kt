package com.example.informatikamobile

import android.content.Intent
import android.net.Uri // Tidak digunakan di kode ini, bisa dihapus jika tidak perlu
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.informatikamobile.databinding.ActivityMainBinding // Pastikan ini benar setelah file XML diverifikasi
// import androidx.core.net.toUri // Tidak digunakan di kode ini, bisa dihapus jika tidak perlu


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Pastikan ActivityMainBinding ada

    // PERBAIKAN: override yang benar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil initNavigation di sini setelah layout di-set
        initNavigation()
    }

    private fun initNavigation(){
        // Pastikan ada Button dengan android:id="@+id/btn_to_page2" di activity_main.xml
        binding.btnToPage2.setOnClickListener{
            startActivity(Intent(this, Halaman2Activity::class.java))
        }
    }
}