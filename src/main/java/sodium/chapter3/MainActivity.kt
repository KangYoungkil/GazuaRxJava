package bross.com.sodium_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import bross.com.sodium_study.widget.SButton
import bross.com.sodium_study.widget.SEditText
import bross.com.sodium_study.widget.STextView
import kotlinx.android.synthetic.main.activity_main.*
import nz.sodium.StreamLoop
import nz.sodium.Transaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layout = findViewById<LinearLayout>(R.id.layout_main)

        colorExample(layout)

        spinnerExample(layout)

    }

    fun spinnerExample(layout: LinearLayout) {
        Transaction.runVoid({
            val sSetValue = StreamLoop<Int>()
            val sEditText = SEditText(this, sSetValue.map({ it.toString() }), "0")

            val value = sEditText.text.map({ it.toInt() })

            val plus = SButton(this, "+")
            val minus = SButton(this, "-")

            val sPlusDelta = plus.sClicked.map({ 1 })
            val sMiunsDelta = minus.sClicked.map({ -1 })
            val sDelta = sPlusDelta.orElse(sMiunsDelta)

            sSetValue.loop(
                    sDelta.snapshot(value, { v: Int, i: Int -> v + i })
            )

            layout.addView(plus)
            layout.addView(minus)
            layout.addView(sEditText)
        })

    }


    fun colorExample(layout: LinearLayout) {

        val red = SButton(this, "red")
        val green = SButton(this, "green")

        val sRed = red.sClicked.map({ "red" })
        val sGreen = green.sClicked.map({ "green" })

        val sColor = sRed.orElse(sGreen)

        val color = sColor.hold("")

        val sLabel = STextView(this, color)

        layout.addView(red)
        layout.addView(green)
        layout.addView(sLabel)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
