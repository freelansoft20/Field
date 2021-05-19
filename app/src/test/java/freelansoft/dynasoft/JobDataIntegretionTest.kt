package freelansoft.dynasoft

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import freelansoft.dynasoft.dto.Job
import freelansoft.dynasoft.service.JobService
import freelansoft.dynasoft.ui.main.MainViewModel
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
class JobDataIntegretionTest {
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
        givenAFeedOfJobDataAreAvailable()
        whenSearchForRedbud()
        thenResultsContainsEasternRedbud()
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

    private fun givenAFeedOfJobDataAreAvailable() {
        mvm = MainViewModel()
    }


    @Test
    fun SearchForGarbage_retutnsNothing(){
        givenAFeedOfJobDataAreAvailable()
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