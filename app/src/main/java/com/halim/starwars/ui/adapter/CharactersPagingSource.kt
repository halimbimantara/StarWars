package com.halim.starwars.ui.adapter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.halim.starwars.data.Constants.FIRST_PAGE_INDEX
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.data.services.ApiService

class CharactersPagingSource(private val apiService: ApiService, private val searchString: String) :
    PagingSource<Int,  PeopleCharResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PeopleCharResponse> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = apiService.getCharactersList(position)
            val characters = response.results as List<PeopleCharResponse>
            val filteredData = characters.filter { it.name!!.contains(searchString, true) }

            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PeopleCharResponse>): Int? {
        return state.anchorPosition
    }
}
