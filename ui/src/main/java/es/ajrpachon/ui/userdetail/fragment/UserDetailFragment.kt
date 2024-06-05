package es.ajrpachon.ui.userdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.TelefonicaProject.R
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserDetailBinding
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserListBinding
import es.ajrpachon.domain.common.util.AsyncResult
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
        userDetailVM.requestUserDetail(args.id)
        userDetailVM.getUserDetailLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is AsyncResult.Success -> {
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

    override fun getViewModel() = userDetailVM as BaseViewModel

}