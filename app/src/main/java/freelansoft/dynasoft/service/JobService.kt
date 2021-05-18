package freelansoft.dynasoft.service

import androidx.lifecycle.MutableLiveData
import freelansoft.dynasoft.dto.Job

class JobService {
    fun fetchJobs(jobName:String) : MutableLiveData<ArrayList<Job>>{
        return MutableLiveData<ArrayList<Job>>()
    }
}