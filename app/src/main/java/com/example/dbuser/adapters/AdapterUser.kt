package com.example.dbuser.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dbuser.R
import com.example.dbuser.dtoUser.UserDto

class AdapterUser(private val datos: ArrayList<UserDto>,private val clickListener: (UserDto) -> Unit)
    : RecyclerView.Adapter<AdapterUser.UsersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UsersViewHolder(item)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val users = datos[position]
        holder.bindUser(users)
        holder.item.setOnClickListener{clickListener(users)}
    }

    override fun getItemCount() = datos.size

    class UsersViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        val lblNombre = item.findViewById(R.id.lblNombre) as TextView
        val lblApellidos = item.findViewById(R.id.lblApellidos) as TextView
        val lblTelefono = item.findViewById(R.id.lblTelefono) as TextView
        val lblEmail = item.findViewById(R.id.lblEmail) as TextView

        fun bindUser(user: UserDto){
            lblNombre.text = user.name
            lblApellidos.text = user.lastName
            lblTelefono.text = user.phoneNumber
            lblEmail.text = user.userEmail
        }
    }
}