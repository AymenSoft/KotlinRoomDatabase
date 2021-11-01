package com.kotlin.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.kotlin.roomdatabase.adapters.UsersAdapter
import com.kotlin.roomdatabase.models.Users
import com.kotlin.roomdatabase.roomDataBase.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.*

class MainActivity : AppCompatActivity() {

    private var user: Users? = null

    private lateinit var usersAdapter: UsersAdapter

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        etSearch.addTextChangedListener {
            try {
                usersAdapter.filter.filter(it)
            }catch (error: Throwable){
                Log.e("searchError", error.message.toString())
            }
        }

        btnSave.setOnClickListener {
            if (user == null) {
                val user = Users(0, etName.text.toString())
                viewModel.insert(user)
                etName.setText("")
            } else {
                user!!.userName = etName.text.toString()
                viewModel.update(user!!)
                etName.setText("")
                user = null
            }
        }

        lvData.setOnItemClickListener { _, _, position, _ ->
            user = usersAdapter.getItem(position) as Users
            etName.setText(user!!.userName)
        }

        lvData.setOnItemLongClickListener { _, _, position, _ ->
            val user = usersAdapter.getItem(position) as Users
            viewModel.delete(user)
            return@setOnItemLongClickListener (true)
        }

        usersAdapter = UsersAdapter(this@MainActivity)
        lvData.adapter = usersAdapter

        viewModel.allUsers.observe(this, {
            usersAdapter.setUsers(it)
        })

    }


}