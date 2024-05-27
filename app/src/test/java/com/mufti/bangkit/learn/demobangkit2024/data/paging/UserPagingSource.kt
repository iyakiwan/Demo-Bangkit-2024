package com.mufti.bangkit.learn.demobangkit2024.data.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mufti.bangkit.learn.demobangkit2024.model.User

class UserPagingSource : PagingSource<Int, LiveData<List<User>>>() {
    companion object {
        fun snapshot(items: List<User>): PagingData<User> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<User>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<User>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}