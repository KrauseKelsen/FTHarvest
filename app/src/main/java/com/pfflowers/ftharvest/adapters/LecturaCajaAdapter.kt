package com.pfflowers.ftharvest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pfflowers.ftharvest.R
import com.pfflowers.ftharvest.pojos.LecturaCaja
import kotlinx.android.synthetic.main.tarjeta_lectura_caja.view.*

class LecturaCajaAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LecturaCaja>() {

        override fun areItemsTheSame(oldItem: LecturaCaja, newItem: LecturaCaja): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LecturaCaja, newItem: LecturaCaja): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LecturaCajaItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tarjeta_lectura_caja,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LecturaCajaItemHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<LecturaCaja>) {
        differ.submitList(list)
    }

    class LecturaCajaItemHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: LecturaCaja) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            txt_codigo.text = item.codigo
            txt_comun.text = item.caja.comun
            txt_variedad.text = item.caja.variedad
            txt_grado.text = item.caja.botonaje
            txt_calidad.text = item.encabezado.calidad
            txt_tallos.text = item.caja.tallos.toString()
            txt_boleta.text = item.encabezado.boleta
            txt_color.text = item.caja.color
            txt_boleta.setBackgroundColor(
                ResourcesCompat.getColor(
                    resources, when {
                        item.estado -> R.color.colorPrimaryDark
                        else -> R.color.error
                    }, null
                )
            )
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: LecturaCaja)
    }
}
