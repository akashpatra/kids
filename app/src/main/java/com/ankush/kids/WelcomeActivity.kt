package com.ankush.kids

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ankush.kids.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Bundle
        val bundle = intent.extras
        if (null != bundle) {
            val name = bundle.get(Constants.NAME_PARAM)
            tvName.text = "Welcome $name"
        }

        // Listeners
        btnYoutube.setOnClickListener {
            openYoutube()
        }
        btnNetflix.setOnClickListener {
            openNetflix()
        }
    }

    private fun openYoutube() {
        val webIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6l0kwK-C9Y8"))
        try {
            this.startActivity(webIntent)
        } catch (ex: ActivityNotFoundException) {
            showSnackBar(btnYoutube, "Youtube App Not Found")
        }
    }

    private fun openNetflix() {
        val netflixId = "70045269"
        val watchUrl = "http://www.netflix.com/watch/$netflixId"
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setClassName(
                "com.netflix.mediaclient",
                "com.netflix.mediaclient.ui.launch.UIWebViewActivity"
            )
            intent.data = Uri.parse(watchUrl)
            startActivity(intent)
        } catch (e: Exception) {
            // netflix app isn't installed, send to website.
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(watchUrl))
            startActivity(intent)
        }
    }

    private fun showSnackBar(view: View, msg: String) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        snackBar.view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        snackBar.show()
    }
}
