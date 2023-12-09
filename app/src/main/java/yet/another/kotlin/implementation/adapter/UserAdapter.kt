package yet.another.kotlin.implementation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yet.another.kotlin.implementation.databinding.UserItemBinding
import yet.another.kotlin.implementation.room.User


class UserAdapter(private val listener:OnItemAction): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.namaUser.text = item.nama
            binding.asalUser.text = item.asal

            binding.cardUser.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    private val userList = ArrayList<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items:List<User>) {
        userList.clear()
        userList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    interface OnItemAction {
        fun onItemClick(data:User)
    }
}