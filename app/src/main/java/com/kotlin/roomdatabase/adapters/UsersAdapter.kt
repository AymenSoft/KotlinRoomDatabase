package com.kotlin.roomdatabase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.kotlin.roomdatabase.R
import com.kotlin.roomdatabase.models.Users

class UsersAdapter(private val context: Context): BaseAdapter(), Filterable {

    private var defUsersList: ArrayList<Users>
    private var usersList: ArrayList<Users>

    init {
        defUsersList = ArrayList()
        usersList = ArrayList()
    }

    fun setUsers(users: List<Users>){
        defUsersList = ArrayList(users)
        usersList = defUsersList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return usersList.size
    }

    override fun getItem(position: Int): Any {
        return usersList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var holder = Holder()
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_user, parent, false)
            holder.tvUserName = view.findViewById(R.id.tvUserName)
            view!!.tag = holder
        } else {
            holder = view.tag as Holder
        }

        val user = usersList[position]

        holder.tvUserName!!.text = user.userName

        return view
    }

    class Holder{
        var tvUserName: TextView ?= null
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val result = ArrayList<Users>()
                if (constraint!!.isNotEmpty()){
                    for (user in defUsersList){
                        if (user.userName.lowercase().contains(constraint.toString().lowercase())){
                            result.add(user)
                        }
                    }
                } else {
                    result.addAll(defUsersList)
                }
                val filterResult = FilterResults()
                filterResult.values = result
                filterResult.count = result.size
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                usersList = results!!.values as ArrayList<Users>
                notifyDataSetChanged()
            }

        }
    }

}