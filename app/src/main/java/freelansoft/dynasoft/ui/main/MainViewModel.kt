package freelansoft.dynasoft.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import freelansoft.dynasoft.dto.Job
import freelansoft.dynasoft.service.JobService

class MainViewModel : ViewModel() {
    var _jobs: MutableLiveData<ArrayList<Job>> = MutableLiveData<ArrayList<Job>>()
    var jobService : JobService = JobService()

    init {
        fetchJobs("e")
    }

    fun fetchJobs(jobName: String) {
        _jobs = jobService.fetchJobs(jobName)
    }
    internal var job:MutableLiveData<ArrayList<Job>>
        get() {return _jobs}
        set(value) {_jobs=value}
}