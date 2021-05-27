package freelansoft.dynasoft.dto

data class Work(var jobName:String? ="", var location:String? ="", var description:String? ="done", var user:String? ="", var dateJob:String? ="", var workId:Int? = 0, var jobId:Int? = 0) {
}
//class Work{
//    var jobName:String? =""
//    var location:String? =""
//    var description:String? =""
//    var user:String? =""
//    var dateJob:String? =""
//    var workId:Int? = 0
//    var jobId:Int? = 0
//
//    constructor()
//    constructor(jobName:String?,location: String?,description:String?, dateJob:String?, workId:Int?,jobId:Int?,user: String?) {
//        this.location = location
//        this.jobName = jobName
//        this.dateJob = dateJob
//        this.description = "done"
//        this.workId = 0
//        this.jobId = 0
//        this.user = user
//    }
//}

