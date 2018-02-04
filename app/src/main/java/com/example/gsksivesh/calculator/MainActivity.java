package com.example.gsksivesh.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean isOperationClicked = false;
    boolean evaluateOnClickOperation = false;
    boolean isEvaluated = false;
    float firstNumber = 0;
    float secondNumber = 0;
    char operationClicked = '+';
    TextView textView;

    protected ArrayList<Button> addNumberButtons() {
        ArrayList<Button> allButtonsText = new ArrayList<>();
        allButtonsText.add((Button) findViewById(R.id.no_0));
        allButtonsText.add((Button) findViewById(R.id.no_1));
        allButtonsText.add((Button) findViewById(R.id.no_2));
        allButtonsText.add((Button) findViewById(R.id.no_3));
        allButtonsText.add((Button) findViewById(R.id.no_4));
        allButtonsText.add((Button) findViewById(R.id.no_5));
        allButtonsText.add((Button) findViewById(R.id.no_6));
        allButtonsText.add((Button) findViewById(R.id.no_7));
        allButtonsText.add((Button) findViewById(R.id.no_8));
        allButtonsText.add((Button) findViewById(R.id.no_9));
        allButtonsText.add((Button) findViewById(R.id.dot));
        return allButtonsText;
    }

    protected ArrayList<Button> addOperationButtons() {
        ArrayList<Button> operationButtons = new ArrayList<>();
        operationButtons.add((Button) findViewById(R.id.addition));
        operationButtons.add((Button) findViewById(R.id.multiply));
        operationButtons.add((Button) findViewById(R.id.subtraction));
        operationButtons.add((Button) findViewById(R.id.division));
        return operationButtons;
    }

    protected float getValueTextView() {
        try {
            return Float.parseFloat(textView.getText().toString());
        } catch (NumberFormatException exception) {
            return 0.0f;
        }
    }

    protected float perform_operation(float number1, float number2, char operation) {
        switch (operation) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case 'X':
                return number1 * number2;
            case '/':
                return number1 / number2;
            default:
                return number1 + number2;
        }
    }

    protected void evaluate() {
        secondNumber = getValueTextView();
        float answer = perform_operation(firstNumber, secondNumber, operationClicked);
        if (answer % 1 == 0) {
            textView.setText(Integer.toString((int) answer));
        } else {
            textView.setText(Float.toString(answer));
        }
        firstNumber = secondNumber;
        isEvaluated = true;
        evaluateOnClickOperation = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_answer);
        ArrayList<Button> allButtonsText = addNumberButtons();
        ArrayList<Button> operationButtons = addOperationButtons();

        for (Button button : allButtonsText) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isEvaluated || isOperationClicked) {
                        textView.setText("");
                        isOperationClicked = false;
                        isEvaluated = false;
                    }
                    String clickedButton = ((Button) view).getText().toString();
                    String previousText = textView.getText().toString();
                    textView.setText(previousText + clickedButton);
                }
            });
        }

        for (Button button : operationButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isOperationClicked) {
                        if (evaluateOnClickOperation) {
                            evaluate();
                        }
                        isOperationClicked = true;
                        String clickedButtonText = ((Button) view).getText().toString();
                        operationClicked = clickedButtonText.charAt(0);
                        firstNumber = getValueTextView();
                        evaluateOnClickOperation = true;
                    }
                }
            });
        }


        Button evaluateButton = (Button) findViewById(R.id.equals);
        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEvaluated){
                    float temp = firstNumber;
                    firstNumber= secondNumber;
                    secondNumber=temp;
                }
                evaluate();
            }
        });

        Button toggleSign = (Button) findViewById(R.id.toggle_sign);
        toggleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                if (text.charAt(0) == '-') {
                    textView.setText(text.substring(1, text.length()));
                } else {
                    textView.setText("-" + text);
                }
            }
        });

        Button percentageButton = (Button) findViewById(R.id.percentage);
        percentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float number = Float.parseFloat(textView.getText().toString());
                number = number / 100;
                if (number % 1 == 0) {
                    textView.setText(Integer.toString((int) number));
                } else {
                    textView.setText(Float.toString(number));
                }
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                isOperationClicked = false;
                isEvaluated = false;
                firstNumber = 0;
                secondNumber = 0;
                evaluateOnClickOperation = false;
                operationClicked = '+';
            }
        });
    }
}
