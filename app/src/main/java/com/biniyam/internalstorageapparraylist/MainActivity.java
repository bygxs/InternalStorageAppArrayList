package com.biniyam.internalstorageapparraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.biniyam.internalstorageapparraylist.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String MY_FILE_NAME = "My_first_file.txt";
    private ActivityMainBinding myBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(myBinding.getRoot());

        myBinding.btnWrite.setOnClickListener(v -> {
            //Write in internal storage

            // whatever the user writes is first stored in message variable
            String message = myBinding.etMessage.getText().toString();
            try {
                FileOutputStream fos = openFileOutput(MY_FILE_NAME,MODE_PRIVATE);
                fos.write(message.getBytes());
                fos.close();
                Toast.makeText(MainActivity.this, "message saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        myBinding.btnRead.setOnClickListener(v -> {
            try {
                FileInputStream  fileInputStream = openFileInput(MY_FILE_NAME);
                byte[] b = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                fileInputStream.close();
                String message= new String(b);
                myBinding.etMessage.setText(message);// this line displays  the stored file in the same EditText View etMessage

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}