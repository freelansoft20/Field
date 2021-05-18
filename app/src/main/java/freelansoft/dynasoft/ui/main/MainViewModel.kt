package freelansoft.dynasoft.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import freelansoft.dynasoft.dto.Job
import freelansoft.dynasoft.service.JobService

class MainViewModel : ViewModel() {
    var jobs: MutableLiveData<ArrayList<Job>> = MutableLiveData<ArrayList<Job>>()
    var jobService : JobService = JobService()

    fun fetchJobs(jobName: String) {
        jobs = jobService.fetchJobs(jobName)
    }
    // TODO: Implement the ViewModel
}