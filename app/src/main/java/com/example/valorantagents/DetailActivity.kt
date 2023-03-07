package com.example.valorantagents

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.valorantagents.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val AGENT_DATA = "agent_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Agent Detail"

        val dataAgent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(AGENT_DATA, Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(AGENT_DATA)
        }

        if (dataAgent != null) {
            val voiceline = "\"${dataAgent.voiceline}\""
            Glide.with(this)
                .load(dataAgent.photo)
                .into(binding.imgAgentPhoto)
            binding.tvAgentName.text = dataAgent.name
            binding.tvAgentDescription.text = dataAgent.description
            binding.tvVoicelineItem.text = voiceline
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataAgent = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(AGENT_DATA, Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(AGENT_DATA)
        }

        val msg = "Yo! You Choose ${dataAgent?.name}. Great Choice!"

        when(item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Valorant")
                shareIntent.putExtra(Intent.EXTRA_TEXT, msg)

                startActivity(Intent.createChooser(shareIntent, "Share With"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}