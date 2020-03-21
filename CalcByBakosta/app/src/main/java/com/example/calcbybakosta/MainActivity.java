package com.example.calcbybakosta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {


//    TextView resultField;
//    EditText inputField;
//
//    TextView operationField;//no need
//    Double operand = null;
//    String lastOperation = "=";

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual, buttonDel, buttonX2, buttonSqrt, buttonFact,
            buttonProc ,buttonSin, buttonCos,buttonTan,buttonLn, buttonLog, buttonPi, buttonE, buttonXn, buttonSqrtN

    ;
    EditText inputField;
    TextView resultField;
    Stack<Double> sequence = new Stack<Double>();
    StringBuffer expression = new StringBuffer();

    double mValueOne, mValueTwo;
    boolean firsttest = false;
    boolean dottest = true;
    boolean optest = false;
    Calculator calc = new Calculator();
   // boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonX2 = (Button) findViewById(R.id.button2x);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        inputField = (EditText) findViewById(R.id.edt1);
        resultField = (TextView) findViewById(R.id.resultField);
        buttonDel = (Button) findViewById(R.id.buttondel);
        buttonSqrt = (Button) findViewById(R.id.buttonSqrt);
        buttonFact = (Button) findViewById(R.id.buttonFact);
//Land new buttons
        buttonProc = (Button) findViewById(R.id.buttonProc);
        buttonSin = (Button) findViewById(R.id.buttonSin);
        buttonCos = (Button) findViewById(R.id.buttonCos);
        buttonTan = (Button) findViewById(R.id.buttonTan);
        buttonLn = (Button) findViewById(R.id.buttonLn);
        buttonLog = (Button) findViewById(R.id.buttonLog);
        buttonPi = (Button) findViewById(R.id.buttonPi);
        buttonE = (Button) findViewById(R.id.buttonE);
        buttonXn = (Button) findViewById(R.id.buttonXn);
        buttonSqrtN = (Button) findViewById(R.id.buttonSqrtN);
//----------------------------LANDSCAPE NEW BUTTONS-------------------------------------------------

        buttonProc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputField == null){
                    inputField.setText("");//ПРОЦЕНТ
                }else{
                    //if(optest){
                        expression.append("%");
                        inputField.setText(inputField.getText() + "%");

                        optest = false;
                        dottest = true;
                        firsttest = false;

                    //}

                }
            }
        });
        buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("1");

                expression.append("s");

                        inputField.setText(inputField.getText() + "sin(");

                        optest = true;
                        dottest = true;
                        firsttest = false;

                    }
        });
        buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("1");

                expression.append("c");

                inputField.setText(inputField.getText() + "cos(");

                optest = true;
                dottest = true;
                firsttest = false;

            }
        });
        buttonTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("1");

                expression.append("t");

                inputField.setText(inputField.getText() + "tan(");

                optest = true;
                dottest = true;
                firsttest = false;

            }
        });

        buttonLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("1");

                expression.append("l");

                inputField.setText(inputField.getText() + "ln(");

                optest = true;
                dottest = true;
                firsttest = false;

            }
        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression.append("1");

                expression.append("g");

                inputField.setText(inputField.getText() + "log(");

                optest = true;
                dottest = true;
                firsttest = false;

            }
        });
        buttonPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "3.14");
                expression.append("3.14");
                firsttest = false;
                optest = true;


            }
        });
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "e");
                expression.append("1");
                expression.append("e");
                expression.append("1");

                firsttest = false;
                optest = true;


            }
        });
        buttonXn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField == null){
                    inputField.setText("");
                }else{
                    if(optest){
                        inputField.setText(inputField.getText() + "^");
                        expression.append("^");

                        optest = false;
                        dottest = true;
                        firsttest = false;
                    }
                }
            }
        });

        buttonSqrtN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField == null){
                    inputField.setText("");
                }else{
                    if(optest){
                        inputField.setText(inputField.getText() + "(√");
                        expression.append("n");

                        optest = false;
                        dottest = true;
                        firsttest = false;
                    }
                }
            }
        });


        buttonX2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){
                    //inputField.setText("");
                }else{
                    if(inputField.length()>=1) {
                        expression.append("%");
                        expression.append("1");

                        inputField.setText(inputField.getText() + "²");

                        optest = true;
                        dottest = true;
                        firsttest = false;

                    }
                }

            }
        });





//--------------------------------------------------------------------------------------------------

        buttonDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str = inputField.getText().toString();
                if(inputField.length()==0){

                }else{
                    String result = str.substring(0, str.length() - 1);

                    inputField.setText(result);
                    if(expression.length()>0){
                        expression.delete(expression.length()-1, expression.length());//TODO After equal errors w/ expression CHECK
                    }
                    optest =true;
                }


            }
                                     });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "1");
                expression.append("1");
                firsttest = false;
                optest = true;


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "2");
                expression.append("2");
                firsttest = false;
                optest = true;


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "3");
                expression.append("3");
                optest = true;
                firsttest = false;

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "4");
                expression.append("4");
                optest = true;
                firsttest = false;

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "5");
                expression.append("5");
                optest = true;
                firsttest = false;

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "6");
                expression.append("6");
                firsttest = false;
                optest = true;

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "7");
                expression.append("7");
                firsttest = false;
                optest = true;


            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "8");
                expression.append("8");
                firsttest = false;
                optest = true;


            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "9");
                expression.append("9");
                firsttest = false;
                optest = true;


            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//inputField.length() == 1 &&   inputField.getText().toString() == "0"
                if (!firsttest && inputField.length()==0) {
                    inputField.setText(inputField.getText() + "0");
                    expression.append("0");
                    firsttest = true;
                    optest = true;
                }else{
                    char temp = inputField.getText().toString().charAt(0);
                    if(inputField.length()>=1 && !firsttest){
                        inputField.setText(inputField.getText() + "0");
                        expression.append("0");
                        optest = true;

                    }
                }
            }
        });
//------------------------------------------------------------------------------------------------//
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputField == null) {
                    inputField.setText("");
                } else {
                    if(optest){//active operator
                        expression.append("+");
                        inputField.setText(inputField.getText() + "+");

                        optest = false;
                        dottest = true;
                        firsttest = false;////////////////DOnt forget
                    }
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputField == null){
                    inputField.setText("");
                }else{
                    if(optest){
                        expression.append("-");
                        inputField.setText(inputField.getText() + "-");

                        optest = false;
                        dottest = true;
                        firsttest = false;

                    }

                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField == null){
                    inputField.setText("");
                }else{
                    if(optest){
                        inputField.setText(inputField.getText() + "*");
                        expression.append("*");

                        optest = false;
                        dottest = true;
                        firsttest = false;
                    }
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){
                    //inputField.setText("");
                }else{
                    if(optest) {
                        expression.append("/");
                        inputField.setText(inputField.getText() + "/");

                        optest = false;
                        dottest = true;
                        firsttest = false;

                    }
                }

            }
        });
        buttonX2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){
                    //inputField.setText("");
                }else{
                    if(inputField.length()>=1) {
                        expression.append("x");
                        expression.append("1");

                        inputField.setText(inputField.getText() + "²");

                        optest = true;
                        dottest = true;
                        firsttest = false;

                    }
                }

            }
        });
        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){
                    //inputField.setText("");
                }else{
                    if(inputField.length()>=1) {
                        expression.append("√");
                        expression.append("1");

                        inputField.setText(inputField.getText() + "√");

                        optest = true;
                        dottest = true;
                        firsttest = false;

                    }
                }

            }
        });
        buttonFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){
                    //inputField.setText("");
                }else{
                    if(inputField.length()>=1) {
                        expression.append("!");
                        expression.append("1");

                        inputField.setText(inputField.getText() + "!");

                        optest = true;
                        dottest = true;
                        firsttest = false;

                    }
                }

            }
        });
//------------------------------------------------------------------------------------------------//
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double res = calc.compute(expression.toString());
                int copy = (int)res;
                double copy1 = copy;
                if(copy1==res){
                    inputField.setText(copy + "");
                    expression = new StringBuffer();
                    expression.append(copy);

                }else{
                    inputField.setText(res + "");
                    expression = new StringBuffer();
                    expression.append(res);
                }

                    firsttest = false;
                    dottest = true;
                    optest = true;



            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText("");
                firsttest = false;
                dottest = true;
                resultField.setText("");
                expression = new StringBuffer();
                optest = false;
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputField.length()==0){

                }else{
                    if(dottest){ //active dot
                        inputField.setText(inputField.getText() + ".");
                        expression.append(".");
                        firsttest = false;
                        dottest = false;
                    }
                }
            }
        });
    }


////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("EXP", expression.toString());//firstest, dottest,optest
        outState.putBoolean("firsttest", firsttest);
        outState.putBoolean("dottest", dottest);
        outState.putBoolean("optest", optest);
//        if(operand!=null)
//            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
}
    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expression = new StringBuffer(savedInstanceState.getString("EXP"));
        dottest = savedInstanceState.getBoolean("dottest");
        firsttest = savedInstanceState.getBoolean("firsttest");
        optest = savedInstanceState.getBoolean("optest");

        inputField.setText(expression.toString());
    }
////////////////////////////////////////////////////////////////////////////////////


}
