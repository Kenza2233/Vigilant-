package com.example.vinote.ui.projects

import com.example.vinote.data.Project
import com.example.vinote.data.ProjectsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProjectsViewModelTest {

    private lateinit var viewModel: ProjectsViewModel
    private lateinit var repository: ProjectsRepository
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mockk(relaxed = true)
        viewModel = ProjectsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `addProject calls repository's insertProject`() {
        // Given
        val projectName = "Test Project"

        // When
        viewModel.addProject(projectName)

        // Then
        coVerify { repository.insertProject(Project(name = projectName)) }
    }
}
