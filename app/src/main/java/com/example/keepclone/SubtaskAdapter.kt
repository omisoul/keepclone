import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keepclone.R
import com.example.keepclone.SubtaskViewModel

class SubtaskAdapter(private val mList: MutableList<SubtaskViewModel>) : RecyclerView.Adapter<SubtaskAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subtask_card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]



        holder.textView.text = ItemsViewModel.text
        holder.delete.setOnClickListener {
            removeItem(position)
            notifyDataSetChanged()

        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val delete:ImageButton = itemView.findViewById(R.id.subtask_delete)

    }


    private fun removeItem(position: Int){
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

}