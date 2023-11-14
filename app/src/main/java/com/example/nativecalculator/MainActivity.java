package com.example.nativecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("NativeClaculator");
    EditText Num1;
    EditText Num2;
    Button Add;
    Button Sub;
    Button Mul;
    Button Div;
    TextView Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Num1 = (EditText)findViewById(R.id.edtText1);
        Num2 = (EditText)findViewById(R.id.edtText2);
        Add = (Button)findViewById(R.id.buttonAdd);
        Sub = (Button)findViewById(R.id.buttonSub);
        Mul = (Button)findViewById(R.id.buttonMul);
        Div = (Button)findViewById(R.id.buttonDiv);
        Result = (TextView)findViewById(R.id.txtResult);

        Add.setOnClickListener(this);
        Sub.setOnClickListener(this);
        Mul.setOnClickListener(this);
        Div.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float num1 = 0;
        float num2 = 0;
        float result =0;
        String oper = "";

        if(TextUtils.isEmpty(Num1.getText().toString())||(TextUtils.isEmpty(Num2.getText().toString())))
            return;

        num1 = Float.parseFloat(Num1.getText().toString());
        num2 = Float.parseFloat(Num2.getText().toString());

        switch(v.getId())
        {
            case R.id.buttonAdd:
            {
                oper="+";
                result = num1+num2;
                break;
            }
            case R.id.buttonSub:
            {
                oper = "-";
                result = num1-num2;
                break;
            }
            case R.id.buttonMul:
            {
                oper = "*";
                result=num1*num2;
                break;
            }
            case R.id.buttonDiv:
            {
                oper = "/";
                result = num1/num2;
                break;
            }
            default:
                break;
        }
        Result.setText(num1+" "+oper+" "+num2+" "+ "="+" "+result);
        Toast imageToast = new Toast(MainActivity.this);
        LinearLayout layout = new LinearLayout(getBaseContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        ImageView img = new ImageView(getBaseContext());
        img.setImageResource(R.drawable.ic_launcher_background);
        layout.addView(img);
        imageToast.setView(layout);
        imageToast.setDuration(Toast.LENGTH_LONG);
        imageToast.show();
        Toast.makeText(MainActivity.this,Result.getText().toString(),Toast.LENGTH_SHORT).show();
table_user.child("Result").setValue(result);

    }



}
