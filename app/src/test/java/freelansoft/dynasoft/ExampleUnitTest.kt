package freelansoft.dynasoft

import freelansoft.dynasoft.dto.Job
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun confirmEasternRedbud_outputsEasternRedbud (){
        var job: Job = Job("Cercis","Canadesis","Eastern Redbud")
        assertEquals("Eastern Redbud", job.toString())
    }
}