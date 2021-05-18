package freelansoft.dynasoft.dao

import freelansoft.dynasoft.dto.Job
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IJobDAO {

    @GET("/perl/mobile/viewplantsjson.pl?Combined_Name=Oak")
    fun getAllJobs(): Call<ArrayList<Job>>

    @GET("/perl/mobile/viewplantsjson.pl?Combined_Name=Oak")
    fun getJobs(@Query("Combined_Name")jobName:String) : Call<ArrayList<Job>>
}