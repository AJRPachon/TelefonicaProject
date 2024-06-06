package es.ajrpachon.ui.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import es.ajrpachon.TelefonicaProject.databinding.UserListRowUserBinding
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.util.utils.getSecureUrl
import java.util.Locale

class UserListAdapter(
    private val callback: (String) -> Unit
) : ListAdapter<UserBo, UserListAdapter.UserListViewHolder>(UserListCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserListViewHolder(UserListRowUserBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
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

                userListLblUserName.text = user.userName?.firstName+ " " + user.userName?.lastName

                userListLblGender.text = user.gender?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

                userListLblCityLocation.text = user.location?.city

                userListLblCountryLocation.text = user.location?.country

                this.userListRowContainer.setOnClickListener {
                    callback(user.login?.uuid ?: "")
                }
            }
        }
    }

    class UserListCallBack : DiffUtil.ItemCallback<UserBo>() {
        override fun areItemsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
            return oldItem.login?.uuid == newItem.login?.uuid
        }

        override fun areContentsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
            return oldItem == newItem
        }
    }

}