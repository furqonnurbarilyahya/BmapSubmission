package com.bangkit.bmapsubmission

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MascotDetail : AppCompatActivity() {

    companion object {
        const val KEY_MASCOT = "key_mascot"
        private const val STATE_RESULT = "state_result"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mascot_detail)
        supportActionBar?.title = "Halaman Detail Deskripsi"

        val dataMascot = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Mascot>(KEY_MASCOT, Mascot::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Mascot>(KEY_MASCOT)
        }

        val tvWinner: TextView = findViewById(R.id.dt_tv_cwinner)
        val tvBestPlayer: TextView = findViewById(R.id.dt_tv_cbestplayer)
        val tvTopScore: TextView = findViewById(R.id.dt_tv_ctopscore)
        val tvMascotName: TextView = findViewById(R.id.dt_tv_mascots_name)
        val tvDetailDescription: TextView = findViewById(R.id.dt_tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.dt_img_photo)

        tvWinner.text = dataMascot!!.winner
        tvBestPlayer.text = dataMascot!!.bestPlayer
        tvTopScore.text = dataMascot!!.topScore
        tvMascotName.text = dataMascot!!.name
        tvDetailDescription.text = dataMascot!!.description
        Glide.with(ivDetailPhoto.context)
            .load(dataMascot.photo)
            .into(ivDetailPhoto)

    }

}