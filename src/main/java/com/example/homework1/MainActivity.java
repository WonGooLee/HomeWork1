package com.example.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

    SecondActivity secondActivity = new SecondActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText edtID, edtPw;
        edtID = (EditText) findViewById(R.id.registerID);
        edtPw = (EditText) findViewById(R.id.registerPW);


        final String strID = edtID.getText().toString();
        String strPw = edtPw.getText().toString();


        Button btnNewSecond = (Button) findViewById(R.id.btnNew);
        btnNewSecond.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//           메인 액티비티에서 SecondActivity를 호출하는 코드
                Intent intentNew = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intentNew);
            }
        });


        Button btnLoginThird = (Button) findViewById(R.id.btnLogin);
        btnLoginThird.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//              아이디 중복 체크
                idCheck(strID);

//           메인 액티비티에서 ThirdActivity를 호출하는 코드
                Intent intentLogin = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    private void idCheck(String idCorrect){
        for(int i=0; i < secondActivity.idArray.size(); i++){
            if (idCorrect  == secondActivity.idArray.get(i)){
                Toast.makeText(MainActivity.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

}