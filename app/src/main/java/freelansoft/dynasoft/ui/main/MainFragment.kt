package freelansoft.dynasoft.ui.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import freelansoft.dynasoft.R
import java.util.*
import kotlin.math.log
import kotlinx.android.synthetic.main.main_fragment.*
import java.text.SimpleDateFormat

class MainFragment : Fragment(), DateSelected {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
//    private lateinit var btnDateField: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.jobs.observe(this, androidx.lifecycle.Observer {
            jobs -> fieldTextDate.setAdapter(ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, jobs))
        })


        btnTextDate.setOnClickListener {
            showDatePicker()
        }
    }

    class DatePickerFragment(val dateSelected: DateSelected): DialogFragment(), DatePickerDialog.OnDateSetListener{
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(context!!, this, year, month, dayOfMonth)
        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            dateSelected.receivedDate(year, month, dayOfMonth)
            Log.d(TAG,"Got the date")
        }

        companion object{
            fun newInstance() = MainFragment()
        }


    }

    private fun showDatePicker() {
        val datePickerFragment = DatePickerFragment(this)
        datePickerFragment.show(fragmentManager!!, "datePicker")
    }

    /**
     * This is the function that will be invoked in our fragment when a user picks a date
     */
    override fun receivedDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = GregorianCalendar()
        calendar.set(Calendar.DAY_OF_YEAR, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)

        val viewFormatter = SimpleDateFormat("dd-MMM-YYYY")
        var viewFormatterDate = viewFormatter.format(calendar.getTime())
        btnTextDate.setText(viewFormatterDate)
    }

}

interface DateSelected {
    fun receivedDate(year: Int, month: Int, dayOfMonth: Int)
    
}