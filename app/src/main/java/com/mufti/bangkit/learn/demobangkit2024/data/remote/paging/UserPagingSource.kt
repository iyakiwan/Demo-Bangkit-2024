package com.mufti.bangkit.learn.demobangkit2024.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mufti.bangkit.learn.demobangkit2024.data.remote.mapper.UserMapper
import com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.demobangkit2024.model.User

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getListUsers(position)
            val dataUser = UserMapper.mapListUserResponseToListUser(responseData)

            LoadResult.Page(
                data = dataUser,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (dataUser.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
