package com.bangkit.bmapsubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMascots: RecyclerView
    private val listHeroes = ArrayList<Mascot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Maskot Piala Dunia"

        rvMascots = findViewById(R.id.rv_mascot)
        rvMascots.setHasFixedSize(true)

        listHeroes.addAll(getListMascots())
        showRecyclerList()

    }

    private fun getListMascots(): ArrayList<Mascot> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataWinner = resources.getStringArray(R.array.data_winner)
        val dataBestPlayer = resources.getStringArray(R.array.data_bestplayer)
        val dataTopScore = resources.getStringArray(R.array.data_topscore)
        val listMascot = ArrayList<Mascot>()

        for (i in dataName.indices) {
            val mascot = Mascot(dataName[i], dataDescription[i], dataPhoto[i], dataWinner[i], dataBestPlayer[i], dataTopScore[i])
            listMascot.add(mascot)
        }
        return listMascot
    }

    private fun showRecyclerList() {
        rvMascots.layoutManager = LinearLayoutManager(this)
        val listMascotAdapter = ListMascotAdapter(listHeroes)
        rvMascots.adapter = listMascotAdapter

        listMascotAdapter.setOnItemClickCallback(object : ListMascotAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Mascot) {
                showSelectedMascot(data)
            }
        })
    }

    private fun showSelectedMascot(mascot: Mascot) {
        Toast.makeText(this, "Kamu memilih " + mascot.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val moveAboutIntent = Intent(this@MainActivity, AboutMe::class.java)
                startActivity(moveAboutIntent)
            }
            R.id.action_share -> {
                // membuat Intent ketika diklik
                val shareIntent = Intent(Intent.ACTION_SEND)

                shareIntent.type = "text/plain"

                //Mengatur konten yang akan di share
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Jangan lewatkan aplikasi eksklusif 'World Cup Mascot' yang akan membawa Anda lebih dekat ke ikon maskot Piala Dunia. Unduh sekarang!")

                // pesan saat aplikasi chooser muncul
                val chooserTitle = "Bagikan info aplikasi ini melalui..."

                // membuat intent chooser untuk memilih aplikasi
                val chooserApp = Intent.createChooser(shareIntent, chooserTitle)

                // mengecek aplikasi yang ada untuk fitur share
                if (shareIntent.resolveActivity(packageManager) != null) {
                    startActivity(chooserApp)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}