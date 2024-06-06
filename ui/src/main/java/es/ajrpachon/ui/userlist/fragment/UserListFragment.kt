package es.ajrpachon.ui.userlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.telefonicaproject.databinding.FragmentUserListBinding
import es.ajrpachon.domain.common.util.AsyncResult
import es.ajrpachon.ui.base.BaseFragment
import es.ajrpachon.ui.base.BaseViewModel
import es.ajrpachon.ui.userlist.adapter.UserListAdapter
import es.ajrpachon.ui.userlist.viewmodel.UserListViewModel
import es.ajrpachon.ui.userlist.viewmodel.UserListViewModel.Companion.PAGE_THRESHOLD

@AndroidEntryPoint
class UserListFragment : BaseFragment() {

    private val userListVM: UserListViewModel by viewModelBinder()

    private var binding: FragmentUserListBinding? = null

    private val userListAdapter by lazy {
        UserListAdapter {
            userListVM.goToUserDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.userListFragmentList?.adapter = userListAdapter
        configureObservers()
        configureListeners()
    }


    private fun configureObservers() {
        userListVM.requestUserList()
        userListVM.getUserListLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is AsyncResult.Success -> {
                    userListAdapter.submitList(result.data)
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

    private fun configureListeners() {
        binding?.let {
            with(it) {
                userListFragmentList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (!recyclerView.canScrollVertically(PAGE_THRESHOLD)) {
                            userListVM.requestUserList()
                        }
                    }
                })
            }
        }
    }


private fun FragmentUserListBinding.showLoading(show: Boolean) {
    if (show) {
        userListProgressBarLoading.visibility = View.VISIBLE
    } else {
        userListProgressBarLoading.visibility = View.GONE
    }
}


override fun onDestroyView() {
    super.onDestroyView()
    binding = null
}

override fun getViewModel() : BaseViewModel = userListVM

}