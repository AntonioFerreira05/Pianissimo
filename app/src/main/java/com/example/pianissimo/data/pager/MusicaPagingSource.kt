package com.example.pianissimo.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pianissimo.data.Musica
import com.example.pianissimo.data.service.NewApiService

class MusicaPagingSource(private val newApiService: NewApiService) : PagingSource<Int, Musica>() {
    override fun getRefreshKey(state: PagingState<Int, Musica>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Musica> {
        return try {
            val page = params.key ?: 1
            val response = newApiService.getMusica(page = page)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}