package es.ajrpachon.ui.userdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.TelefonicaProject.R
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserDetailBinding
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserListBinding
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.util.AsyncResult
import es.ajrpachon.domain.common.util.utils.getSecureUrl
import es.ajrpachon.ui.base.BaseFragment
import es.ajrpachon.ui.base.BaseViewModel
import es.ajrpachon.ui.userdetail.viewmodel.UserDetailViewModel

@AndroidEntryPoint
class UserDetailFragment  : BaseFragment() {

    private val args: UserDetailFragmentArgs by navArgs()

    private val userDetailVM: UserDetailViewModel by viewModelBinder()

    private var binding: FragmentUserDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObservers()
    }

    private fun configureObservers() {
        userDetailVM.requestUserDetail(args.uuid)
        userDetailVM.getUserDetailLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is AsyncResult.Success -> {
                    bindResultData(result.data)
                    binding?.showLoading(false)
                }

                is AsyncResult.Error -> {
                    binding?.showLoading(false)
                }

                is AsyncResult.Loading -> {
                    binding?.showLoading(true)
                }

                null -> {
                    binding?.showLoading(false)
                }
            }
        }
    }

    private fun bindResultData(user: UserBo?) {
        binding?.let {
            with(it) {

                Glide.with(root)
                    .load(getSecureUrl("${user?.picture?.thumbnail}"))
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(userDetailImgUserProfilePicture)

                userDetailLblUserName.text = StringBuilder()
                    .append(user?.userName?.title ?: "")
                    .append(". ")
                    .append(user?.userName?.firstName ?: "" )
                    .append(" ").append(user?.userName?.lastName ?: "")

                with(userDetailCardLoginInfo) {
                    userDetailRowLblEmailValue.text = user?.email
                    userDetailRowLblUserNameValue.text = user?.login?.username
                    userDetailRowLblPasswordValue.text = user?.login?.password
                }

            }
        }
    }


    private fun FragmentUserDetailBinding.showLoading(show: Boolean) {
        if (show) {
            userDetailProgressBarLoading.visibility = View.VISIBLE
        } else {
            userDetailProgressBarLoading.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getViewModel() : BaseViewModel = userDetailVM

}