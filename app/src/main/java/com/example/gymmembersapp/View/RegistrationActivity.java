package com.example.gymmembersapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymmembersapp.R;

public class RegistrationActivity extends AppCompatActivity {

    public static String registration_key = "registration_key";
    private String memberId;
    private String[] planList = {"Basic- \t\t\t\t\t\t$15.99", "Regular- \t\t\t\t$25.99", "Premium- \t\t\t$35.99"};
    private String planSelected;

    private EditText memberName;
    private ListView planListView;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        memberName = findViewById(R.id.nameEdit_registration);
        planListView = findViewById(R.id.planListView);
        registerButton = findViewById(R.id.registerButton_registration);

        memberId = getIntent().getStringExtra(registration_key);

        readPlans();

        planListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView myTextView = view.findViewById(R.id.plan_title_textview);

                planSelected = myTextView.getText().toString();

                planSelected = planSelected.substring(0,1);

                switch (planSelected){
                    case "B":
                        planSelected = "Basic";
                        break;
                    case "R":
                        planSelected = "Regular";
                        break;
                    case "P":
                        planSelected = "Premium";
                        break;
                        default: planSelected = "";
                        break;
                }
                Toast.makeText(RegistrationActivity.this, "Selected: " + planSelected, Toast.LENGTH_SHORT).show();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (planSelected != null) {
                    String message = memberName.getText().toString() + "," + memberId + "," + planSelected;

                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    intent.putExtra(MainActivity.return_key, message);
                    setResult(AppCompatActivity.RESULT_OK, intent);

                    finish();

                } else
                    Toast.makeText(RegistrationActivity.this, "No Plan Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readPlans(){
        ArrayAdapter planTitleAdapter = new ArrayAdapter<String>(
                this,
                R.layout.plan_item_layout,
                R.id.plan_title_textview,
                planList);

        planListView.setAdapter(planTitleAdapter);
    }
}
