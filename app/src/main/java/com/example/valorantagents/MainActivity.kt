package com.example.valorantagents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantagents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Agent>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Valorant Agents"

        binding.rvAgents.setHasFixedSize(true)

        list.addAll(getListAgents())
        showRecyclerList()
    }

    private fun getListAgents(): ArrayList<Agent>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataVoiceline = resources.getStringArray(R.array.data_voiceline)
        val dataRole = resources.getStringArray(R.array.data_role)
        val dataAbility = resources.getStringArray(R.array.data_ability)
        val dataUltimate = resources.getStringArray(R.array.data_ultimate)
        val listAgent = ArrayList<Agent>()

        for (i in dataName.indices) {
            val agent = Agent(dataName[i], dataDescription[i], dataPhoto[i], dataVoiceline[i], dataRole[i], dataAbility[i], dataUltimate[i])
            listAgent.add(agent)
        }

        return listAgent
    }

    private fun showRecyclerList() {
        binding.rvAgents.layoutManager = LinearLayoutManager(this)
        val listAgentAdapter = ListAgentAdapter(list)
        binding.rvAgents.adapter = listAgentAdapter

        listAgentAdapter.setOnItemClickCallback(object: ListAgentAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Agent) {
                showSelectedAgent(data)
            }
        })
    }

    private fun showSelectedAgent(agent: Agent) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra("agent_data", agent)
        startActivity(detailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvAgents.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvAgents.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val aboutIntent = Intent(binding.root.context, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}