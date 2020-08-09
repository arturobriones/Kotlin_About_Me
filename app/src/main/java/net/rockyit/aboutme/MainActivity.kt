package net.rockyit.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//(2)
import androidx.databinding.DataBindingUtil
//(2)
import net.rockyit.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //(2) Create binding variable (used for DataBinding)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        //(1) ContentView (used in ViewBinding)
        //setContentView(R.layout.activity_main)

        //(2) Replace ContentView (used in DataBinding)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //(1) onClick Done Btn (used for ViewBindind)
        //findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)
        //}

        //(2) onClick Done Btn (used for DataBinding)
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View)
    {
        //(1) for ViewBinding
        /*val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE*/

        //(2) for DataBinding
        binding.apply {
            nicknameText.text = binding.nicknameEdit.text
            invalidateAll()     // to refresh UI with new data we need to invalidate all binding expressions so they can re create con valid data
            binding.nicknameEdit.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }

        // (2) or
        /*binding.nicknameText.text = binding.nicknameEdit.text
        binding.nicknameEdit.visibility = View.GONE
        binding.doneButton.visibility = View.GONE
        binding.nicknameText.visibility = View.VISIBLE*/

        // Hide the Keyboard
        val imm= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}