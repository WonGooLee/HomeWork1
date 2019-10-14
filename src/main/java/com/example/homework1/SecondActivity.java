package com.example.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// SecondActivity가 RegisterActivity 역할함

public class SecondActivity extends Activity {

    private EditText newEdtId, newEdtPw, newEdtName, newEdtPhone, newEdtAddress;

    ArrayList idArray = new ArrayList();


    // File 생성자를 통해 file을 저장할 directory 생성, file생성
    final static String foldername = Environment.getExternalStorageDirectory().getAbsolutePath()+"/TestLog";
    final static String filename = "logfile.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        newEdtId = (EditText) findViewById(R.id.idText);
        newEdtPw = (EditText) findViewById(R.id.passwordText);
        newEdtName = (EditText) findViewById(R.id.nameText);
        newEdtPhone = (EditText) findViewById(R.id.phoneText);
        newEdtAddress = (EditText) findViewById(R.id.addressText);


        TextView tv1;
        tv1 = (TextView) findViewById(R.id.privacyConsent);
        tv1.setText("개인정보 동의 간략 약관");


        Button registerButton = (Button) findViewById(R.id.registerBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = newEdtId.getText().toString();
                String userPassword = newEdtPw.getText().toString();
                String userName = newEdtName.getText().toString();
                int userPhone = Integer.parseInt(newEdtPhone.getText().toString());
                String userAddress = newEdtAddress.getText().toString();

//               아이디 중복 확인
                idCheck(userID);

//                아이디 중복 아니면 추가
                idArray.add(userID);


                // 아이디 입력 확인
                if( newEdtId.getText().toString().length() == 0 ) {
                    Toast.makeText(SecondActivity.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    newEdtId.requestFocus();
                    return;
                }else{
                        idCheck(userID);
                }


                // 비밀번호 입력 확인
                if( newEdtPw.getText().toString().length() == 0 ) {
                    Toast.makeText(SecondActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    newEdtPw.requestFocus();
                    return;
                }

//                이름 입력 확인
                if( newEdtName.getText().toString().length() == 0 ) {
                    Toast.makeText(SecondActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    newEdtName.requestFocus();
                    return;
                }

//                번호 입력 확인
                if( newEdtPhone.getText().toString().length() == 0 ) {
                    Toast.makeText(SecondActivity.this, "전화번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    newEdtPhone.requestFocus();
                    return;
                }

//                주소 입력 확인
                if( newEdtAddress.getText().toString().length() == 0 ) {
                    Toast.makeText(SecondActivity.this, "주소를 입력하세요!", Toast.LENGTH_SHORT).show();
                    newEdtAddress.requestFocus();
                    return;
                }

//              비밀번호 (자릿수/특수키 등 규칙 체크)
                if (isValidPassword(newEdtPw.getText().toString().trim())) {
                    Toast.makeText(SecondActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondActivity.this, "비밀번호에 숫자 최소 1개, 특수문자 최소 1개, 최소 6개를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                String str = null;
                str = newEdtId.getText().toString()+"|";
                str += newEdtPw.getText().toString()+"|";
                str += newEdtName.getText().toString()+"|";
                str += newEdtPhone.getText().toString()+"|";
                str += newEdtAddress.getText().toString();


                //  file생성
//                File file = new File(str);
                mOnFileWrite(v, str);


//         넘어가기 메인화면으로
                Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMain);

            }

            private void idCheck(String idCorrect){
            for(int i=0; i < idArray.size(); i++){
             if (idCorrect  == idArray.get(i)){
                 Toast.makeText(SecondActivity.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                 return;
             }
            }
    }


            //버튼클릭
            public void mOnFileWrite(View v, String strData){
                String contents = strData;

                WriteTextFile(foldername, filename, contents);
            }


            //텍스트내용을 경로의 텍스트 파일에 쓰기
            public void WriteTextFile(String foldername, String filename, String contents){
                try{
                    File dir = new File (foldername);
                    //디렉토리 폴더가 없으면 생성함
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    //파일 output stream 생성
                    FileOutputStream fos = new FileOutputStream(foldername+"/"+filename, true);
                    //파일쓰기
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                    writer.write(contents);
                    writer.flush();

                    writer.close();
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
//      숫자 최소 1개, 특수문자 최소 1개, 최소 4자리~10자리 입력
        final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[!@#$%^&+-=]).{4,10}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
});
    }
}