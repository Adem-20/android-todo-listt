package com.example.yaplacaklarlistesi.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yaplacaklarlistesi.Adapter.RvAdapter
import com.example.yaplacaklarlistesi.Model.Plan
import com.example.yaplacaklarlistesi.Model.PlanDao
import com.example.yaplacaklarlistesi.Model.PlanDatabase
import com.example.yaplacaklarlistesi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val plans = ArrayList<Plan>()
    private lateinit var adapter: RvAdapter
    private lateinit var dao: PlanDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //dao al
        dao = PlanDatabase.getDataBase(this).planDao()

        // rv hazır
        adapter = RvAdapter(
            plans,
            onEdit = { plan ->
                // Düzenleme için bottom shett açılacak
                NewPlanBottomSheet().apply {
                    defaultTitle       = plan.title
                    defaultDescription = plan.description
                    onSave = { newTitle, newDesc ->
                        lifecycleScope.launch {
                            val updatedPlan = plan.copy(
                                title       = newTitle,
                                description = newDesc
                            )
                            dao.update(updatedPlan)
                            val all = dao.getAll()
                            plans.clear()
                            plans.addAll(all)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }.show(supportFragmentManager, "EditPlanSheet")
            },
            onDelete = { plan ->
                // Silme işlemi
                lifecycleScope.launch {
                    dao.delete(plan)
                    plans.remove(plan)
                    adapter.notifyDataSetChanged()
                }
            }
        )
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        // kayıtlı planları getir
        lifecycleScope.launch {
            val saved = dao.getAll()
            plans.addAll(saved)
            adapter.notifyDataSetChanged()
        }

        // faba tıklandığında aç
        binding.fabEkle.setOnClickListener {
            NewPlanBottomSheet().apply {
                onSave = { title, description ->
                    lifecycleScope.launch {
                        dao.insert(Plan(title = title, description = description))
                        val updated = dao.getAll()
                        plans.clear()
                        plans.addAll(updated)
                        adapter.notifyDataSetChanged()
                        binding.rvList.scrollToPosition(plans.size - 1)
                    }
                }
            }.show(supportFragmentManager, "NewPlanBottomSheet")
        }
    }
}
