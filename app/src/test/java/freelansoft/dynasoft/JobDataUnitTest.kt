package freelansoft.dynasoft

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import freelansoft.dynasoft.dto.Job
import freelansoft.dynasoft.service.JobService
import freelansoft.dynasoft.ui.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JobDataUnitTest {
    @get:Rule
    var rule:TestRule = InstantTaskExecutorRule()
    lateinit var mvm:MainViewModel

    var jobService = mockk<JobService>()

    @Test
    fun confirmEasternRedbud_outputsEasternRedbud (){
        var job: Job = Job("Cercis","Canadesis","Eastern Redbud")
        assertEquals("Eastern Redbud", job.toString());
    }

    @Test
    fun searchForRedbud_returnsRedbud(){
        givenAFeedOfMockedJobDataAreAvailable()
        whenSearchForRedbud()
        thenResultsContainsEasternRedbud()
    }
    private fun givenAFeedOfMockedJobDataAreAvailable() {
        mvm = MainViewModel()
        createMockData()
    }

    private fun whenSearchForRedbud() {
        mvm.fetchJobs("Redbud")
    }

    private fun thenResultsContainsEasternRedbud() {
        var redbudFound = false;
        mvm._jobs.observeForever{
            // here is where we do the observing
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach{
                if (it.genus == "Cercis" && it.species == "canadenis" && it.common.contains("Eastern Redbud")){
                    redbudFound = true
                }
            }
        }
        assertTrue(redbudFound)
    }

    private fun createMockData() {
        var allJobsLiveData = MutableLiveData<ArrayList<Job>>()
        var allJobs = ArrayList<Job>()
        //create and add jobs to our collection
        var redbud = Job("Cercis", "Canadensis", "Eastern Redbud")
        allJobs.add(redbud)
        var redoak = Job("Quercus", "Rubra", "Red oak")
        allJobs.add(redoak)
        var whiteoak = Job("Quercus", "alba", "white oak")
        allJobs.add(whiteoak)
        allJobsLiveData.postValue(allJobs)
        every { jobService.fetchJobs(or("Redbud", "Quercus")) } returns allJobsLiveData
        every { jobService.fetchJobs(not(or("Redbud", "Quercus")) )} returns MutableLiveData<ArrayList<Job>>()
        mvm.jobService = jobService
    }

    @Test
    fun SearchForGarbage_retutnsNothing(){
        givenAFeedOfMockedJobDataAreAvailable()
        whenISearchForGabarge()
        thenIGetZeroResult()
    }

    private fun thenIGetZeroResult() {
        mvm.fetchJobs("sklujapouetllkjsdau")
    }

    private fun whenISearchForGabarge() {
        mvm._jobs.observeForever{
            assertEquals(0, it.size)
        }
    }
}