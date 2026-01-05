package com.example.pianissimo.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pianissimo.data.Musica
import com.example.pianissimo.data.service.MusicaApiService

class MusicaPagingSource(
    private val musicaApiService: MusicaApiService
) : PagingSource<Int, Musica>() {

    override fun getRefreshKey(state: PagingState<Int, Musica>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Musica> {
        return try {
            val page = params.key ?: 0  // pagina inicial = 0
            val response = musicaApiService.getTrendingTracks(
                limit = params.loadSize,
                offset = page * params.loadSize
            )

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
