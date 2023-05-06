package com.halim.starwars.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.halim.starwars.data.Constants.NETWORK_PAGE_SIZE
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.data.services.ApiService
import com.halim.starwars.data.services.SafeApiCall
import com.halim.starwars.ui.adapter.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall()  {
    fun getSearchCharacters(searchString: String): Flow<PagingData<PeopleCharResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService, searchString)
            }
        ).flow
    }
}