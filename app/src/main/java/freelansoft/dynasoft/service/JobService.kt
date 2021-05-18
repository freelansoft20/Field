package freelansoft.dynasoft.service

import androidx.lifecycle.MutableLiveData
import freelansoft.dynasoft.RetrofitClientInstance
import freelansoft.dynasoft.dao.IJobDAO
import freelansoft.dynasoft.dto.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class JobService {
    fun fetchJobs(jobName:String) : MutableLiveData<ArrayList<Job>>{
        var _jobs= MutableLiveData<ArrayList<Job>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(IJobDAO::class.java)
        val call = service?.getAllJobs()
        call?.enqueue(object: Callback<ArrayList<Job>>{
            override fun onResponse(p0: Call<ArrayList<Job>>, response: Response<ArrayList<Job>>) {
                _jobs.value = response.body()
            }

            override fun onFailure(p0: Call<ArrayList<Job>>, p1: Throwable) {
                val j = 1+1
                val i = 1+1

            }

        })
        return _jobs
    }
}