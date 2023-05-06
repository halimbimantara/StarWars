package com.halim.starwars.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.halim.starwars.base.BaseFragment
import com.halim.starwars.databinding.FragmentHomeBinding
import com.halim.starwars.ui.adapter.CharactersPeopleAdapter
import com.halim.starwars.viewmodels.CharPeopleViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePeopleFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CharPeopleViewModels by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        initAdapter()
        setObserver("")
        actionUI()
        return view
    }

    private fun actionUI() {
        binding.searchView.setEndIconOnClickListener {
            setObserver(binding.searchView.editText!!.text.toString())
            binding.charactersProgressBar.isVisible = true
            hideKeyboard()
        }
    }

    private fun setObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launch(Main) {
            viewModel.getCharacters(searchString).collect {
                charactersAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun initAdapter() {
        binding.charactersRecyclerview.apply {
            adapter = charactersAdapter
        }

        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (charactersAdapter.snapshot().isEmpty()) {
                    binding.charactersProgressBar.isVisible = true
                }
                binding.textViewError.isVisible = false

            } else {
                binding.charactersProgressBar.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (charactersAdapter.snapshot().isEmpty()) {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }

    private val charactersAdapter: CharactersPeopleAdapter by lazy {
        CharactersPeopleAdapter(CharactersPeopleAdapter.OnClickListener { character ->
//            val action =
//                Hom.actionCharactersFragmentToCharactersDetailsFragment(
//                    character
//                )
//            findNavController().navigate(action)
            binding.searchView.editText!!.setText("")
        })

    }

}