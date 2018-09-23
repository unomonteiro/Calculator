package io.monteirodev.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var operation = "*"
    var oldNumber = ""
    var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btClick(view: View) {
        if (isNewOperation) {
            et_display.setText("")
        }
        isNewOperation = false

        var display:String = et_display.text.toString()
        when (view.id) {
            bt_0.id -> { display = addNumber(display, "0") }
            bt_1.id -> { display = addNumber(display, "1") }
            bt_2.id -> { display = addNumber(display, "2") }
            bt_3.id -> { display = addNumber(display, "3") }
            bt_4.id -> { display = addNumber(display, "4") }
            bt_5.id -> { display = addNumber(display, "5") }
            bt_6.id -> { display = addNumber(display, "6") }
            bt_7.id -> { display = addNumber(display, "7") }
            bt_8.id -> { display = addNumber(display, "8") }
            bt_9.id -> { display = addNumber(display, "9") }
            bt_dot.id -> {
                if (!display.contains(".")) display += "."
            }
        }
        et_display.setText(display)
    }

    fun btInvertClick(view: View) {
        var display:String = et_display.text.toString()
        if (display.startsWith("-")) {
            display = display.substring(1)
        } else {
            display = "-$display"
        }
        et_display.setText(display)
    }

    private fun addNumber(display: String, number: String): String {
        return if (display == "0") number else display + number
    }

    fun btOpClick(view: View) {
        when (view.id) {
            bt_multiply.id -> operation = "*"
            bt_div.id -> operation = "/"
            bt_minus.id -> operation = "-"
            bt_sum.id -> operation = "+"
        }
        oldNumber = et_display.text.toString()
        isNewOperation = true
    }

    fun btClearClick(view: View) {
        et_display.setText("0")
        isNewOperation = true
    }

    fun btPercentClick(view: View) {
        val result = et_display.text.toString().toDouble() / 100
        et_display.setText(result.toString())
        isNewOperation = true
    }

    fun btEqualClick(view: View) {
        if (isNewOperation || oldNumber.isEmpty()) return
        val newNumber = et_display.text.toString()
        var resultNumber:Double?=null
        when (operation) {
            "*" -> resultNumber = oldNumber.toDouble() * newNumber.toDouble()
            "/" -> resultNumber = oldNumber.toDouble() / newNumber.toDouble()
            "-" -> resultNumber = oldNumber.toDouble() - newNumber.toDouble()
            "+" -> resultNumber = oldNumber.toDouble() + newNumber.toDouble()
        }

        var resultStr = resultNumber.toString()
        if (resultStr.endsWith(".0")) {
            resultStr = resultStr.removeSuffix(".0")
        }
        et_display.setText(resultStr)
        oldNumber = ""
        isNewOperation = true

    }
}
