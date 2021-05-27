package freelansoft.dynasoft.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import freelansoft.dynasoft.dto.Job
import freelansoft.dynasoft.dto.Work
import freelansoft.dynasoft.service.JobService

class MainViewModel : ViewModel() {
    internal var _jobs: MutableLiveData<ArrayList<Job>> = MutableLiveData<ArrayList<Job>>()
    internal var jobService : JobService = JobService()
    private lateinit var firestore : FirebaseFirestore
//    private var firestoret:FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        fetchJobs("e")
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun fetchJobs(jobName: String) {
        _jobs = jobService.fetchJobs(jobName)
    }

    fun save(work: Work) {
        firestore.collection("works")
            .document()
            .set(work)
            .addOnSuccessListener {
                Log.d("Firebase", "document Saved")
            }
            .addOnFailureListener {
                Log.d("Firebase", "Save failed")
            }
    }

    internal var job:MutableLiveData<ArrayList<Job>>
        get() {return _jobs}
        set(value) {_jobs=value}
}