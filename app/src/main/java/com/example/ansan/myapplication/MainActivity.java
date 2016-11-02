package com.example.ansan.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) { //button onclick걸기
        //sd card사용 가능 여부 확인
        if(isStoragePermissionGranted()){
            Toast.makeText(getApplicationContext(),
                     "SD card 사용불가", Toast.LENGTH_SHORT).show();
            return;
        }

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        String folder = path + "/myLoveDir";
        String filename = folder + "/myFile.txt";

        File myfolder = new File(folder);

        switch (v.getId()) {
            //폴더 생성
            case R.id.button:
                //디렉토리 만든는 함수.
                myfolder.mkdir();
                Toast.makeText(getApplicationContext(),
                        "폴더 생성", Toast.LENGTH_SHORT).show();
                break;

            //폴더 삭제
            case R.id.button2:

                break;
            //파일 생성
            case R.id.button3:
                break;
            //파일 읽기;
            case R.id.button4:
                break;
            //파일 목록 가져오기
            case R.id.button5:
                break;
        }
    }


    String TAG = "TEST";
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

}
