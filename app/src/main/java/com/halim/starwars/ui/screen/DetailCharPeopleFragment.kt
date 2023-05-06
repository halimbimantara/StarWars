package com.halim.starwars.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.halim.starwars.R
import com.halim.starwars.base.BaseFragment
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.data.services.Resource
import com.halim.starwars.databinding.FragmentDetailBinding
import com.halim.starwars.ui.adapter.FilmsAdapter
import com.halim.starwars.ui.adapter.SpeciesAdapter
import com.halim.starwars.ui.adapter.StarShipAdapter
import com.halim.starwars.ui.adapter.VehicleAdapter
import com.halim.starwars.viewmodels.DetailCharacterPeopleViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharPeopleFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailCharacterPeopleViewModels by viewModels()
    private val dataPeopleChar by lazy {
        arguments?.getString(EXTRA_DATA_PEOPLE)
    }

    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }

    private val starShipAdapter: StarShipAdapter by lazy {
        StarShipAdapter()
    }

    private val vehicleAdapter: VehicleAdapter by lazy {
        VehicleAdapter()
    }

    private val speciesAdapter: SpeciesAdapter by lazy {
        SpeciesAdapter()
    }

    companion object {
        const val EXTRA_DATA_PEOPLE = "extra_data_people"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        baseActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        setupData()
        setUpObserver()
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setUpObserver() {
        viewModel.responseHomeWorld.observe(requireActivity()) {
            when (it) {
                is Resource.Failure -> {
                    binding.progressBarHomeWord.isVisible = false
                    binding.textViewHomeWorldValue.text = it.message
                }

                is Resource.Success -> {
                    binding.progressBarHomeWord.isVisible = false
                    binding.textViewHomeWorldValue.text = it.data?.name
                    binding.textViewPlanetName.text = "Name " + it.data?.name
                    binding.textViewPlanetPopulation.text = "Population " + it.data?.population
                }

                is Resource.Loading -> {
                    binding.progressBarHomeWord.isVisible = true
                }

                else -> {}
            }
        }

        viewModel.resultSpeciesDetails.observe(requireActivity()) {
            when (it) {
                is Resource.Failure -> {
                    binding.starSpeciesProgressBar.isVisible = false
                    binding.textViewSpeciesError.text = it.message
                }

                is Resource.Success -> {
                    binding.starSpeciesProgressBar.isVisible = false
                    speciesAdapter.submitList(it.data)
                    binding.recyclerViewSpecies.adapter = speciesAdapter
                }

                is Resource.Loading -> {
                    binding.starSpeciesProgressBar.isVisible = true
                }

                else -> {}
            }
        }

        viewModel.resultVehicleShipDetails.observe(requireActivity()) {
            when (it) {
                is Resource.Failure -> {
                    binding.starVehicleProgressBar.isVisible = false
                    binding.textViewVehicleError.text = it.message
                }

                is Resource.Success -> {
                    binding.starVehicleProgressBar.isVisible = false
                    vehicleAdapter.submitList(it.data)
                    binding.recyclerViewVehicle.adapter = vehicleAdapter
                }

                is Resource.Loading -> {
                    binding.starVehicleProgressBar.isVisible = true
                }

                else -> {}
            }
        }
        viewModel.resultFilmDetails.observe(requireActivity()) {
            when (it) {
                is Resource.Failure -> {
                    binding.filmProgressBar.isVisible = false
                    binding.textViewFilmsError.text = it.message
                }

                is Resource.Success -> {
                    binding.filmProgressBar.isVisible = false
                    filmsAdapter.submitList(it.data)
                    binding.recyclerViewFilms.adapter = filmsAdapter
                }

                is Resource.Loading -> {
                    binding.filmProgressBar.isVisible = true
                }

                else -> {}
            }
        }

        viewModel.resultStartShipDetails.observe(requireActivity()) {
            when (it) {
                is Resource.Failure -> {
                    binding.starShipProgressBar.isVisible = false
                    binding.textViewHomeWorldValue.text = it.message
                }

                is Resource.Success -> {
                    binding.starShipProgressBar.isVisible = false
                    starShipAdapter.submitList(it.data)
                    binding.recyclerViewStarShip.adapter = starShipAdapter
                }

                is Resource.Loading -> {
                    binding.starShipProgressBar.isVisible = true
                }

                else -> {}
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        if (!dataPeopleChar.isNullOrBlank()) {
            val dataBodyConv = Gson().fromJson(
                dataPeopleChar.toString(),
                PeopleCharResponse::class.java
            )

            binding.tvNameCharacter.text = dataBodyConv.name
            binding.textViewFullNameValue.text = dataBodyConv.name
            binding.textViewBirthYearValue.text = dataBodyConv.birthYear
            binding.textViewHeightValue.text = dataBodyConv.height
            binding.textViewGenderValue.text = dataBodyConv.gender
            binding.textViewHairColorValue.text = dataBodyConv.hairColor
            binding.textViewEyeColorValue.text = dataBodyConv.eyeColor
            binding.textViewSkinColorValue.text = dataBodyConv.skinColor
            binding.textViewMassValue.text = dataBodyConv.mass

            if (dataBodyConv.films.isNotEmpty())
                viewModel.getFilmData(dataBodyConv.films)

            if (dataBodyConv.species.isNotEmpty())
                viewModel.getSpeciesData(dataBodyConv.species)

            viewModel.getHomeWorld(dataBodyConv.homeworld!!)
            if (dataBodyConv.starships.isNotEmpty())
                viewModel.getStarShipData(dataBodyConv.starships)

            if (dataBodyConv.vehicles.isNotEmpty())
                viewModel.getVehicleData(dataBodyConv.vehicles)
        }
    }
}