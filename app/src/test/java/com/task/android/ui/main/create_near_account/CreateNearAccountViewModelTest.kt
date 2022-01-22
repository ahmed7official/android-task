package com.task.android.ui.main.create_near_account

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateNearAccountViewModelTest {

    private lateinit var viewModel: CreateNearAccountViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = CreateNearAccountViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `enableRegistration enterEmptyNameAndEmptyWallet returnFalse`() {
        viewModel.fullName.value = ""
        viewModel.walletId.value = ""
        assertEquals(viewModel.shouldEnableRegistration(), false)
    }

    @Test
    fun `enableRegistration enterEmptyNameAndCorrectWallet returnFalse`() {
        viewModel.fullName.value = ""
        viewModel.walletId.value = "ahmed.near"
        assertEquals(viewModel.shouldEnableRegistration(), false)
    }

    @Test
    fun `enableRegistration enterCorrectNameAndCorrectWallet returnTrue`() {
        viewModel.fullName.value = "ahmed mohamed"
        viewModel.walletId.value = "ahmed.near"
        assertEquals(viewModel.shouldEnableRegistration(), true)
    }

    @Test
    fun `enableRegistration enterCorrectNameAndEmptyWallet returnFalse`() {
        viewModel.fullName.value = "ahmed mohamed"
        viewModel.walletId.value = ""
        assertEquals(viewModel.shouldEnableRegistration(), false)
    }

}