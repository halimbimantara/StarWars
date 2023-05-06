package com.halim.starwars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.data.repository.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharPeopleViewModels @Inject constructor(private val starWarsRepository: StarWarsRepository) :
    ViewModel() {
    fun getCharacters(searchString: String): Flow<PagingData<PeopleCharResponse>> {
        return starWarsRepository.getSearchCharacters(searchString).cachedIn(viewModelScope)
    }
}