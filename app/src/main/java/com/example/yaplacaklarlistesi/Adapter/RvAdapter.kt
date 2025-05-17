package com.example.yaplacaklarlistesi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yaplacaklarlistesi.Model.Plan
import com.example.yaplacaklarlistesi.R

class RvAdapter (private val plans:ArrayList<Plan>,
    private val onEdit: (Plan) -> Unit,
    private val onDelete:(Plan) -> Unit
):RecyclerView.Adapter<RvAdapter.PlanViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.PlanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card,parent,false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvAdapter.PlanViewHolder, position: Int) {
        holder.bind(plans[position])
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    inner class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title = itemView.findViewById<TextView>(R.id.text_title)
        private val description = itemView.findViewById<TextView>(R.id.text_description)
        private val buttonEdit = itemView.findViewById<Button>(R.id.button_edit)
        private val buttonDelete = itemView.findViewById<Button>(R.id.button_delete)

        fun bind(plan : Plan) {
            title.text = plan.title
            description.text = plan.description
            buttonEdit.setOnClickListener { onEdit(plan) }
            buttonDelete.setOnClickListener { onDelete(plan) }
        }
    }
}