package es.ajrpachon.userlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import es.ajrpachon.TelefonicaProject.databinding.UserListRowUserBinding
import es.ajrpachon.common.util.utils.getSecureUrl
import es.ajrpachon.model.UserBo

class UserListAdapter(
    private val callback: (String) -> Unit
) : ListAdapter<UserBo, UserListAdapter.UserListViewHolder>(UserListCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserListViewHolder(UserListRowUserBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class UserListViewHolder(private val binding: UserListRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserBo) {
            with(binding) {
                Glide.with(binding.root)
                    .load(getSecureUrl("${user.picture?.thumbnail}"))
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(userListImgUserPicture)

                this.userListRowContainer.setOnClickListener {
                    callback(user.id ?: "")
                }
            }
        }
    }

    class UserListCallBack : DiffUtil.ItemCallback<UserBo>() {
        override fun areItemsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
            return oldItem == newItem
        }
    }

}