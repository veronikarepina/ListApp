package ru.veronikarepina.listapplication.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
//import ru.veronikarepina.data.utils.extensions.toPost
import ru.veronikarepina.listapplication.R
import ru.veronikarepina.listapplication.databinding.FragmentPostsBinding
import ru.veronikarepina.listapplication.adapter.PostsAdapter

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private val postsAdapter by lazy { PostsAdapter() }
    private val viewModel by viewModel<PostsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolBar()
        setupRecyclerView()


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.collectLatest {
                postsAdapter.submitData(it)
            }
        }

        postsAdapter.addLoadStateListener { state ->
            when (state.source.refresh){
                is LoadState.NotLoading -> {
                    binding.progressBar.isVisible = false
                }
                is LoadState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is LoadState.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun setupToolBar(){
        binding.toolbar.inflateMenu(R.menu.menu_posts)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_refresh -> {
                    Toast.makeText(requireContext(), "REFRESH", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = postsAdapter
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }
}