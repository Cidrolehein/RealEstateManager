package com.openclassrooms.realestatemanager.adapters

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.activities.MainPlaceActivity
import com.openclassrooms.realestatemanager.fragments.DetailListFragment
import java.text.FieldPosition

class RecyclerAdapter constructor(val mItemClickListener:ItemClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val titles = arrayOf("House",
            "Flat", "House", "Duplex",
            "House", "Penthouse", "House",
            "Duplex")

    private val localisation = arrayOf("Manhatten", "Montauk",
            "Brooklin", "Southempton",
            "Upper Est Side", "Hempton Bays",
            "Manhatten", "Upper Est Side")

    private val price = arrayOf("$17,870,000", "$17,870,000",
            "$17,870,000", "$17,870,000",
            "$17,870,000", "$17,870,000",
            "$17,870,000", "$17,870,000")

    private val images = intArrayOf(R.drawable.android_image_1,
            R.drawable.android_image_1, R.drawable.android_image_1,
            R.drawable.android_image_1, R.drawable.android_image_1,
            R.drawable.android_image_1, R.drawable.android_image_1,
            R.drawable.android_image_1)

    var cardPosition: Int = 0

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val context = viewHolder.itemView.context // get context for bg color

        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemLocalisation.text = localisation[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemPrice.text = price[i]
        // on item selected
        viewHolder.itemView.setOnClickListener { v: View ->
            val position: Int = viewHolder.adapterPosition
            cardPosition = viewHolder.adapterPosition
            notifyDataSetChanged()

            Snackbar.make(v, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()

            mItemClickListener.onItemClick(position)

        }

        // change bg color of card selected and adapt text color
        if (cardPosition == i) {
            viewHolder.card.setCardBackgroundColor(ContextCompat.getColor(context,
                    R.color.colorPrimary))
            viewHolder.itemTitle.setTextColor(Color.WHITE)
        } else {
            viewHolder.card.setCardBackgroundColor(Color.WHITE)
            viewHolder.itemTitle.setTextColor(ContextCompat.getColor(context,
                    R.color.colorPrimary))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemLocalisation: TextView = itemView.findViewById(R.id.item_localisation)
        var itemPrice: TextView = itemView.findViewById(R.id.item_price)
        var card: CardView = itemView.findViewById(R.id.card_view)

    }

}

