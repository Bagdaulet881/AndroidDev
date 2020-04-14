package com.example.calcbybakosta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

//import android.support.v7.app.AppCompatActivity;
class MainActivity : AppCompatActivity() {
    //    TextView resultField;
//    EditText inputField;
//
//    TextView operationField;//no need
//    Double operand = null;
//    String lastOperation = "=";
    var button0: Button? = null
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var button5: Button? = null
    var button6: Button? = null
    var button7: Button? = null
    var button8: Button? = null
    var button9: Button? = null
    var buttonAdd: Button? = null
    var buttonSub: Button? = null
    var buttonDivision: Button? = null
    var buttonMul: Button? = null
    var button10: Button? = null
    var buttonC: Button? = null
    var buttonEqual: Button? = null
    var buttonDel: Button? = null
    var buttonX2: Button? = null
    var buttonSqrt: Button? = null
    var buttonFact: Button? = null
    var buttonProc: Button? = null
    var buttonSin: Button? = null
    var buttonCos: Button? = null
    var buttonTan: Button? = null
    var buttonLn: Button? = null
    var buttonLog: Button? = null
    var buttonPi: Button? = null
    var buttonE: Button? = null
    var buttonXn: Button? = null
    var buttonSqrtN: Button? = null
    var inputField: EditText? = null
    var resultField: TextView? = null
    var sequence = Stack<Double>()
    var expression = StringBuffer()
    var mValueOne = 0.0
    var mValueTwo = 0.0
    var firsttest = false
    var dottest = true
    var optest = false
    var calc = Calculator()
    // boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button0 = findViewById<View>(R.id.button0) as Button
        button1 = findViewById<View>(R.id.button1) as Button
        button2 = findViewById<View>(R.id.button2) as Button
        button3 = findViewById<View>(R.id.button3) as Button
        button4 = findViewById<View>(R.id.button4) as Button
        button5 = findViewById<View>(R.id.button5) as Button
        button6 = findViewById<View>(R.id.button6) as Button
        button7 = findViewById<View>(R.id.button7) as Button
        button8 = findViewById<View>(R.id.button8) as Button
        button9 = findViewById<View>(R.id.button9) as Button
        button10 = findViewById<View>(R.id.button10) as Button
        buttonX2 = findViewById<View>(R.id.button2x) as Button
        buttonAdd = findViewById<View>(R.id.buttonadd) as Button
        buttonSub = findViewById<View>(R.id.buttonsub) as Button
        buttonMul = findViewById<View>(R.id.buttonmul) as Button
        buttonDivision = findViewById<View>(R.id.buttondiv) as Button
        buttonC = findViewById<View>(R.id.buttonC) as Button
        buttonEqual = findViewById<View>(R.id.buttoneql) as Button
        inputField = findViewById<View>(R.id.edt1) as EditText
        resultField = findViewById<View>(R.id.resultField) as TextView
        buttonDel = findViewById<View>(R.id.buttondel) as Button
        buttonSqrt = findViewById<View>(R.id.buttonSqrt) as Button
        buttonFact = findViewById<View>(R.id.buttonFact) as Button
        //Land new buttons
        buttonProc = findViewById<View>(R.id.buttonProc) as Button
        buttonSin = findViewById<View>(R.id.buttonSin) as Button
        buttonCos = findViewById<View>(R.id.buttonCos) as Button
        buttonTan = findViewById<View>(R.id.buttonTan) as Button
        buttonLn = findViewById<View>(R.id.buttonLn) as Button
        buttonLog = findViewById<View>(R.id.buttonLog) as Button
        buttonPi = findViewById<View>(R.id.buttonPi) as Button
        buttonE = findViewById<View>(R.id.buttonE) as Button
        buttonXn = findViewById<View>(R.id.buttonXn) as Button
        buttonSqrtN = findViewById<View>(R.id.buttonSqrtN) as Button
        //----------------------------LANDSCAPE NEW BUTTONS-------------------------------------------------
        buttonProc!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("") //ПРОЦЕНТ
            } else { //if(optest){
                expression.append("%")
                inputField!!.setText(inputField!!.text.toString() + "%")
                optest = false
                dottest = true
                firsttest = false
                //}
            }
        }
        buttonSin!!.setOnClickListener {
            expression.append("1")
            expression.append("s")
            inputField!!.setText(inputField!!.text.toString() + "sin(")
            optest = true
            dottest = true
            firsttest = false
        }
        buttonCos!!.setOnClickListener {
            expression.append("1")
            expression.append("c")
            inputField!!.setText(inputField!!.text.toString() + "cos(")
            optest = true
            dottest = true
            firsttest = false
        }
        buttonTan!!.setOnClickListener {
            expression.append("1")
            expression.append("t")
            inputField!!.setText(inputField!!.text.toString() + "tan(")
            optest = true
            dottest = true
            firsttest = false
        }
        buttonLn!!.setOnClickListener {
            expression.append("1")
            expression.append("l")
            inputField!!.setText(inputField!!.text.toString() + "ln(")
            optest = true
            dottest = true
            firsttest = false
        }
        buttonLog!!.setOnClickListener {
            expression.append("1")
            expression.append("g")
            inputField!!.setText(inputField!!.text.toString() + "log(")
            optest = true
            dottest = true
            firsttest = false
        }
        buttonPi!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "3.14")
            expression.append("3.14")
            firsttest = false
            optest = true
        }
        buttonE!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "e")
            expression.append("1")
            expression.append("e")
            expression.append("1")
            firsttest = false
            optest = true
        }
        buttonXn!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("")
            } else {
                if (optest) {
                    inputField!!.setText(inputField!!.text.toString() + "^")
                    expression.append("^")
                    optest = false
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonSqrtN!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("")
            } else {
                if (optest) {
                    inputField!!.setText(inputField!!.text.toString() + "(√")
                    expression.append("n")
                    optest = false
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonX2!!.setOnClickListener {
            if (inputField!!.length() == 0) { //inputField.setText("");
            } else {
                if (inputField!!.length() >= 1) {
                    expression.append("%")
                    expression.append("1")
                    inputField!!.setText(inputField!!.text.toString() + "²")
                    optest = true
                    dottest = true
                    firsttest = false
                }
            }
        }
        //--------------------------------------------------------------------------------------------------
        buttonDel!!.setOnClickListener {
            val str = inputField!!.text.toString()
            if (inputField!!.length() == 0) {
            } else {
                val result = str.substring(0, str.length - 1)
                inputField!!.setText(result)
                if (expression.length > 0) {
                    expression.delete(expression.length - 1, expression.length) //TODO After equal errors w/ expression CHECK
                }
                optest = true
            }
        }
        button1!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "1")
            expression.append("1")
            firsttest = false
            optest = true
        }
        button2!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "2")
            expression.append("2")
            firsttest = false
            optest = true
        }
        button3!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "3")
            expression.append("3")
            optest = true
            firsttest = false
        }
        button4!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "4")
            expression.append("4")
            optest = true
            firsttest = false
        }
        button5!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "5")
            expression.append("5")
            optest = true
            firsttest = false
        }
        button6!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "6")
            expression.append("6")
            firsttest = false
            optest = true
        }
        button7!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "7")
            expression.append("7")
            firsttest = false
            optest = true
        }
        button8!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "8")
            expression.append("8")
            firsttest = false
            optest = true
        }
        button9!!.setOnClickListener {
            inputField!!.setText(inputField!!.text.toString() + "9")
            expression.append("9")
            firsttest = false
            optest = true
        }
        button0!!.setOnClickListener {
            //inputField.length() == 1 &&   inputField.getText().toString() == "0"
            if (!firsttest && inputField!!.length() == 0) {
                inputField!!.setText(inputField!!.text.toString() + "0")
                expression.append("0")
                firsttest = true
                optest = true
            } else {
                val temp = inputField!!.text.toString()[0]
                if (inputField!!.length() >= 1 && !firsttest) {
                    inputField!!.setText(inputField!!.text.toString() + "0")
                    expression.append("0")
                    optest = true
                }
            }
        }
        //------------------------------------------------------------------------------------------------//
        buttonAdd!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("")
            } else {
                if (optest) { //active operator
                    expression.append("+")
                    inputField!!.setText(inputField!!.text.toString() + "+")
                    optest = false
                    dottest = true
                    firsttest = false ////////////////DOnt forget
                }
            }
        }
        buttonSub!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("")
            } else {
                if (optest) {
                    expression.append("-")
                    inputField!!.setText(inputField!!.text.toString() + "-")
                    optest = false
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonMul!!.setOnClickListener {
            if (inputField == null) {
                inputField!!.setText("")
            } else {
                if (optest) {
                    inputField!!.setText(inputField!!.text.toString() + "*")
                    expression.append("*")
                    optest = false
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonDivision!!.setOnClickListener {
            if (inputField!!.length() == 0) { //inputField.setText("");
            } else {
                if (optest) {
                    expression.append("/")
                    inputField!!.setText(inputField!!.text.toString() + "/")
                    optest = false
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonX2!!.setOnClickListener {
            if (inputField!!.length() == 0) { //inputField.setText("");
            } else {
                if (inputField!!.length() >= 1) {
                    expression.append("x")
                    expression.append("1")
                    inputField!!.setText(inputField!!.text.toString() + "²")
                    optest = true
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonSqrt!!.setOnClickListener {
            if (inputField!!.length() == 0) { //inputField.setText("");
            } else {
                if (inputField!!.length() >= 1) {
                    expression.append("√")
                    expression.append("1")
                    inputField!!.setText(inputField!!.text.toString() + "√")
                    optest = true
                    dottest = true
                    firsttest = false
                }
            }
        }
        buttonFact!!.setOnClickListener {
            if (inputField!!.length() == 0) { //inputField.setText("");
            } else {
                if (inputField!!.length() >= 1) {
                    expression.append("!")
                    expression.append("1")
                    inputField!!.setText(inputField!!.text.toString() + "!")
                    optest = true
                    dottest = true
                    firsttest = false
                }
            }
        }
        //------------------------------------------------------------------------------------------------//
        buttonEqual!!.setOnClickListener {
            val res = calc.compute(expression.toString())
            val copy = res.toInt()
            val copy1 = copy.toDouble()
            if (copy1 == res) {
                inputField!!.setText(copy.toString() + "")
                expression = StringBuffer()
                expression.append(copy)
            } else {
                inputField!!.setText(res.toString() + "")
                expression = StringBuffer()
                expression.append(res)
            }
            firsttest = false
            dottest = true
            optest = true
        }
        buttonC!!.setOnClickListener {
            inputField!!.setText("")
            firsttest = false
            dottest = true
            resultField!!.text = ""
            expression = StringBuffer()
            optest = false
        }
        button10!!.setOnClickListener {
            if (inputField!!.length() == 0) {
            } else {
                if (dottest) { //active dot
                    inputField!!.setText(inputField!!.text.toString() + ".")
                    expression.append(".")
                    firsttest = false
                    dottest = false
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("EXP", expression.toString()) //firstest, dottest,optest
        outState.putBoolean("firsttest", firsttest)
        outState.putBoolean("dottest", dottest)
        outState.putBoolean("optest", optest)
        //        if(operand!=null)
//            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState)
    }

    // получение ранее сохраненного состояния
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        expression = StringBuffer(savedInstanceState.getString("EXP")!!)
        dottest = savedInstanceState.getBoolean("dottest")
        firsttest = savedInstanceState.getBoolean("firsttest")
        optest = savedInstanceState.getBoolean("optest")
        inputField!!.setText(expression.toString())
    } ////////////////////////////////////////////////////////////////////////////////////
}