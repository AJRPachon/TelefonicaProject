package es.ajrpachon.userlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.TelefonicaProject.R
import es.ajrpachon.TelefonicaProject.databinding.FragmentUserListBinding
import es.ajrpachon.common.ui.BaseFragment
import es.ajrpachon.common.ui.BaseViewModel
import es.ajrpachon.repository.util.AsyncResult
import es.ajrpachon.userlist.ui.adapter.UserListAdapter
import es.ajrpachon.userlist.ui.viewmodel.UserListViewModel


@AndroidEntryPoint
class UserListFragment : BaseFragment() {

    private val userListVM: UserListViewModel by viewModelBinder(R.id.nav_graph__user_list)

    private var binding: FragmentUserListBinding? = null

    private val userListAdapter by lazy {
        UserListAdapter{
            userListVM.goToUserDetail(it )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObservers()
    }

    private fun configureObservers() {
        userListVM.requestUserList()
        userListVM.getUserListLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is AsyncResult.Success -> {
                    userListAdapter.submitList(result.data)
                }

                is AsyncResult.Error -> {

                }

                is AsyncResult.Loading -> {

                }

                null -> TODO()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getViewModel() = userListVM as BaseViewModel

}