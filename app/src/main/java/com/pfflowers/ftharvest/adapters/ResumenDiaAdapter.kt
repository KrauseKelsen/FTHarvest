package com.pfflowers.ftharvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.pojos.ResumenDia
import kotlinx.android.synthetic.main.resumen_dia_item.view.*

class ResumenDiaAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResumenDia>() {

        override fun areItemsTheSame(oldItem: ResumenDia, newItem: ResumenDia): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResumenDia, newItem: ResumenDia): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ResumenDiaItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.resumen_dia_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ResumenDiaItemHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ResumenDia>) {
        differ.submitList(list)
    }

    class ResumenDiaItemHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ResumenDia) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            txt_comun.text = item.comun
            txt_grado.text = item.botonaje
            txt_color.text = item.color
            txt_variedad.text = item.variedad
            txt_tallos.text = item.tallos.toString()
            txt_cajas.text = item.etiquetas.toString()
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ResumenDia)
    }
}