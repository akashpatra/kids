package com.ankush.kids

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ankush.kids.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            if (name.isBlank()) {
                showSnackBar(btnSubmit, "Please Enter Name")
            } else {
                // Intent
                val bundle = Bundle()
                bundle.putString(Constants.NAME_PARAM, name)
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun showSnackBar(view: View, msg: String) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        snackBar.view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        snackBar.show()
    }
}
