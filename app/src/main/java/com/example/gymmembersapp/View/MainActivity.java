package com.example.gymmembersapp.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymmembersapp.Adapter.MemberRVAdapter;
import com.example.gymmembersapp.Members.Members;
import com.example.gymmembersapp.R;
import com.example.gymmembersapp.Util.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MemberRVAdapter.MemberDelegate {

    private Button registrationButton_main;
    private RecyclerView memberRecyclerView;

    private int idCounter = 0;

    private File external;

    private List<Members> memberList;

    private String fileName = "MembersDB.txt";
    private String directoryPath = "MyAppFolder";

    private int REQUEST_REGISTRATION = 800;
    public static String return_key = "return_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrationButton_main = findViewById(R.id.registerButton_main);
        memberRecyclerView = findViewById(R.id.memberRecyclerView);

        external = new File(getExternalFilesDir(directoryPath), fileName);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        memberRecyclerView.addItemDecoration(itemDecoration);

        readFromExternalStorage();

        registrationButton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                intent.putExtra(RegistrationActivity.registration_key, idCounter+"");

                startActivityForResult(intent, REQUEST_REGISTRATION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == REQUEST_REGISTRATION) {
            //Toast.makeText(this, data.getStringExtra(return_key), Toast.LENGTH_SHORT).show();
            idCounter++;
            writeToExternalStorage(data.getStringExtra(return_key));
        }
    }

    private void writeToExternalStorage(String memberItem){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(external, true);

            String input = memberItem + "\n";
            fileOutputStream.write(input.getBytes());
            fileOutputStream.close();

            //Toast.makeText(this, input + " added to members", Toast.LENGTH_SHORT).show();
            readFromExternalStorage();

        } catch (IOException e){
            Logger.logError(new Throwable(e));
        }
    }

    private void readFromExternalStorage(){

        String delimiter = ",";
        try{
            memberList = new ArrayList();
            FileInputStream fileInputStream = new FileInputStream(external);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String input = null;

            while((input = bufferedReader.readLine()) != null){
                Logger.logDebug(input);
                Logger.logDebug("\n");
                memberList.add(new Members(input.split(delimiter)[0], input.split(delimiter)[1], input.split(delimiter)[2]));
            }
            bufferedReader.close();

            idCounter = memberList.size();

            LinearLayoutManager layoutMgr = new LinearLayoutManager(this);
            //layoutMgr.setOrientation(RecyclerView.HORIZONTAL);
            memberRecyclerView.setLayoutManager(layoutMgr);
            memberRecyclerView.setAdapter(new MemberRVAdapter(memberList, this));


        }catch (IOException e){
            Logger.logError(new Throwable(e));
        }
    }

    @Override
    public void memberSelected(Members selectedMember) {
        Intent intent = new Intent(MainActivity.this, MemberDisplayActivity.class);
        intent.putExtra("my_parcel", selectedMember);

        startActivity(intent);
    }
}