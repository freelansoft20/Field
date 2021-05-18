package freelansoft.dynasoft.dto

import com.google.gson.annotations.SerializedName

data class Job(var genus:String, var species:String, var common:String, @SerializedName("Id") var jobId:Int = 0) {
    override fun toString(): String {
        return common
    }
}