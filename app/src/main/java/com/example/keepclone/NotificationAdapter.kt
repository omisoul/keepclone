package com.example.keepclone

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(
    var notifications: List<Notification>
    ):
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {


    inner class NotificationViewHolder(view: View): RecyclerView.ViewHolder(view){
        val type: TextView
        val time: TextView
        val info: TextView

        init{
            type= view.findViewById(R.id.notification_type)
            time = view.findViewById(R.id.notification_time)
            info = view.findViewById(R.id.notification_info)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_con, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.time.text = notifications[position].time
        holder.type.text = notifications[position].type
        holder.info.text = notifications[position].info

        if (notifications[position].type == "Upcoming"){
            holder.type.setTextColor(Color.parseColor("#64B57D"))
        }else{
            holder.type.setTextColor(Color.parseColor("#E04343"))
        }
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}