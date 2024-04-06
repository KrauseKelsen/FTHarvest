package com.pfflowers.ftharvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.pojos.ResumenBoleta
import kotlinx.android.synthetic.main.resumen_boleta_item.view.*

class ResumenBoletasAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResumenBoleta>() {

        override fun areItemsTheSame(oldItem: ResumenBoleta, newItem: ResumenBoleta): Boolean {
            TODO("not implemented")
        }

        override fun areContentsTheSame(oldItem: ResumenBoleta, newItem: ResumenBoleta): Boolean {
            TODO("not implemented")
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ResumenBoletaHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.resumen_boleta_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ResumenBoletaHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ResumenBoleta>) {
        differ.submitList(list)
    }

    class ResumenBoletaHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ResumenBoleta) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            txt_tallos.text = item.resumen.tallos.toString()
            txt_codigo.text = item.resumen.etiquetas.toString()
            txt_boleta.text = item.boleta
            txt_comun.text = item.resumen.comun
            txt_color.text = item.resumen.color
            txt_variedad.text = item.resumen.variedad
            txt_calidad.text = item.calidad
            txt_grado.text = item.resumen.botonaje

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ResumenBoleta)
    }
}
