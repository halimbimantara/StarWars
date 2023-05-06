package com.halim.starwars.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halim.starwars.data.models.FilmResponse
import com.halim.starwars.data.models.HomeWorldResponse
import com.halim.starwars.data.models.SpeciesResponse
import com.halim.starwars.data.models.StarShipResponse
import com.halim.starwars.data.models.VehicleResponse
import com.halim.starwars.data.repository.StarWarsRepository
import com.halim.starwars.data.services.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterPeopleViewModels @Inject constructor(private val starWarsRepository: StarWarsRepository) :
    ViewModel() {

    val responseHomeWorld: MutableLiveData<Resource<HomeWorldResponse>> =
        MutableLiveData()

    private val _filmDetails: MutableLiveData<Resource<List<FilmResponse>>> =
        MutableLiveData()

    val resultFilmDetails: LiveData<Resource<List<FilmResponse>>> =
        _filmDetails


    private val _starShipDetails: MutableLiveData<Resource<List<StarShipResponse>>> =
        MutableLiveData()

    val resultStartShipDetails: LiveData<Resource<List<StarShipResponse>>> =
        _starShipDetails


    private val _vehicleShipDetails: MutableLiveData<Resource<List<VehicleResponse>>> =
        MutableLiveData()

    val resultVehicleShipDetails: LiveData<Resource<List<VehicleResponse>>> =
        _vehicleShipDetails


    private val _SpeciesDetails: MutableLiveData<Resource<List<SpeciesResponse>>> =
        MutableLiveData()

    val resultSpeciesDetails: LiveData<Resource<List<SpeciesResponse>>> =
        _SpeciesDetails


    private val filmsList: ArrayList<FilmResponse> = ArrayList()
    private val vehicleList: ArrayList<VehicleResponse> = ArrayList()
    private val starShipList: ArrayList<StarShipResponse> = ArrayList()
    private val speciesList: ArrayList<SpeciesResponse> = ArrayList()

     fun getFilmData(listFilm: List<String>) {
        listFilm.forEach { film ->
            viewModelScope.launch(Dispatchers.Main) {
                _filmDetails.value = Resource.Loading(null)
                when (val characterDetailsResponse = starWarsRepository.getFilm(film)) {
                    is Resource.Failure -> {
                        _filmDetails.value =
                            Resource.Failure(characterDetailsResponse.message!!,null)
                    }

                    is Resource.Success -> {
                        if (characterDetailsResponse.data == null) {
                            _filmDetails.value = Resource.Failure("Empty Film Response List")
                        } else {
                            filmsList.add(characterDetailsResponse.data)
                            _filmDetails.value = Resource.Success(filmsList)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

     fun getVehicleData(listFilm: List<String>) {
        listFilm.forEach { film ->
            viewModelScope.launch(Dispatchers.Main) {
                _vehicleShipDetails.value = Resource.Loading(null)
                when (val responseItem = starWarsRepository.getVehicleResponse(film)) {
                    is Resource.Failure -> {
                        _vehicleShipDetails.value =
                            Resource.Failure(responseItem.message!!,null)
                    }

                    is Resource.Success -> {
                        if (responseItem.data == null) {
                            _vehicleShipDetails.value = Resource.Failure("Empty Vehicle Response List")
                        } else {
                            vehicleList.add(responseItem.data)
                            _vehicleShipDetails.value = Resource.Success(vehicleList)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
     fun getStarShipData(listFilm: List<String>) {
        listFilm.forEach { film ->
            viewModelScope.launch(Dispatchers.Main) {
                _starShipDetails.value = Resource.Loading(null)
                when (val responseItem = starWarsRepository.getStarShip(film)) {
                    is Resource.Failure -> {
                        _starShipDetails.value =
                            Resource.Failure(responseItem.message!!,null)
                    }

                    is Resource.Success -> {
                        if (responseItem.data == null) {
                            _starShipDetails.value = Resource.Failure("Empty Starship Response List")
                        } else {
                            starShipList.add(responseItem.data)
                            _starShipDetails.value = Resource.Success(starShipList)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
     fun getSpeciesData(listFilm: List<String>) {
        listFilm.forEach { film ->
            viewModelScope.launch(Dispatchers.Main) {
                _SpeciesDetails.value = Resource.Loading(null)

                when (val responseItem = starWarsRepository.getSpecies(film)) {
                    is Resource.Failure -> {
                        _SpeciesDetails.value =
                            Resource.Failure(responseItem.message!!,null)
                    }

                    is Resource.Success -> {
                        if (responseItem.data == null) {
                            _SpeciesDetails.value = Resource.Failure("Empty Species Response List")
                        } else {
                            speciesList.add(responseItem.data)
                            _SpeciesDetails.value = Resource.Success(speciesList)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

     fun getHomeWorld(url:String){
        viewModelScope.launch(Dispatchers.Main) {
            responseHomeWorld.value = Resource.Loading(null)
            val homeWorldRes = starWarsRepository.getHomeWorld(url)
            responseHomeWorld.value = homeWorldRes
        }
    }

}