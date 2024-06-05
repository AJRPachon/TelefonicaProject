package es.ajrpachon.ui.userdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.TelefonicaProject.R
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserDetailBinding
import es.ajrpachon.ui.base.BaseFragment
import es.ajrpachon.ui.base.BaseViewModel
import es.ajrpachon.ui.userdetail.viewmodel.UserDetailViewModel

@AndroidEntryPoint
class UserDetailFragment  : BaseFragment() {

    private val userListVM: UserDetailViewModel by viewModelBinder(R.id.nav_graph__user_detail)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getViewModel() = userListVM as BaseViewModel

}