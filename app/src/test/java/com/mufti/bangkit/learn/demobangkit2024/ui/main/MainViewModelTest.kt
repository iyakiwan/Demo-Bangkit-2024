package com.mufti.bangkit.learn.demobangkit2024.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.data.paging.UserPagingSource
import com.mufti.bangkit.learn.demobangkit2024.model.User
import com.mufti.bangkit.learn.demobangkit2024.utils.DataDummy
import com.mufti.bangkit.learn.demobangkit2024.utils.MainDispatcherRule
import com.mufti.bangkit.learn.demobangkit2024.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Test
    fun `when Get List User Should Not Null and Return Success`() = runTest {
        val dummyUsers = DataDummy.generateDummyUsers()
        val data: PagingData<User> = UserPagingSource.snapshot(dummyUsers)
        val expectedUsers = MutableLiveData<PagingData<User>>()
        expectedUsers.value = data

        `when`(userRepository.getListUser()).thenReturn(expectedUsers)

        val userViewModel = MainViewModel(userRepository)
        val actualUsers = userViewModel.listUser.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = UserAdapter.diffCallback,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualUsers)

        assertNotNull(differ.snapshot())
        assertEquals(dummyUsers.size, differ.snapshot().size)
        assertEquals(dummyUsers[0], differ.snapshot()[0])
    }

    @Test
    fun `when Get List User Empty Should Return No Data`() = runTest {
        val data: PagingData<User> = PagingData.from(emptyList())
        val expectedUsers = MutableLiveData<PagingData<User>>()
        expectedUsers.value = data

        `when`(userRepository.getListUser()).thenReturn(expectedUsers)

        val userViewModel = MainViewModel(userRepository)
        val actualUsers: PagingData<User> = userViewModel.listUser.getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = UserAdapter.diffCallback,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualUsers)

        assertEquals(0, differ.snapshot().size)
    }

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
    
    /*@Test
    fun `when Get List User Should Not Null and Return Success`() = runTest {
        val dummyUsers = DataDummy.generateDummyUsers()
        val expectedUsers = MutableLiveData<Result<List<User>>>()
        expectedUsers.value = Result.Success(dummyUsers)

        `when`(userRepository.getListUser()).thenReturn(expectedUsers)

        val userViewModel = MainViewModel(userRepository)

        val actualUsers = userViewModel.listUser.getOrAwaitValue()

        Mockito.verify(userRepository).getListUser()
        assertNotNull(actualUsers)
        assertTrue(actualUsers is Result.Success)
        assertEquals(dummyUsers.size, (actualUsers as Result.Success).data.size)
        assertEquals(dummyUsers[0], actualUsers.data[0])
    }

    @Test
    fun `when Get List User Empty Should Return No Data`() = runTest {
        val dummyUsers = emptyList<User>()
        val expectedUsers = MutableLiveData<Result<List<User>>>()
        expectedUsers.value = Result.Success(dummyUsers)

        `when`(userRepository.getListUser()).thenReturn(expectedUsers)

        val userViewModel = MainViewModel(userRepository)

        val actualUsers = userViewModel.listUser.getOrAwaitValue()

        Mockito.verify(userRepository).getListUser()
        assertNotNull(actualUsers)
        assertTrue(actualUsers is Result.Success)
        assertEquals(0, (actualUsers as Result.Success).data.size)
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val expectedResult = MutableLiveData<Result<List<User>>>()
        expectedResult.value = Result.Error(error = "Error")

        `when`(userRepository.getListUser()).thenReturn(expectedResult)

        val userViewModel = MainViewModel(userRepository)

        val actualUsers = userViewModel.listUser.getOrAwaitValue()
        Mockito.verify(userRepository).getListUser()
        assertNotNull(actualUsers)
        assertTrue(actualUsers is Result.Error)
    }*/
}