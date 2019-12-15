package com.example.gymmembersapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymmembersapp.Members.Members;
import com.example.gymmembersapp.R;

public class MemberDisplayActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView planTextView;
    private TextView idTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_display);

        nameTextView = findViewById(R.id.nameTitle_Display);
        planTextView = findViewById(R.id.PlanTitle_Display);
        idTextView = findViewById(R.id.IdTitle_Display);

        nameTextView.setText("Name:\t\t" + ((Members)getIntent().getParcelableExtra("my_parcel")).getMemberName());
        planTextView.setText("ID:\t\t" + ((Members)getIntent().getParcelableExtra("my_parcel")).getPlan());
        idTextView.setText("Plan:\t\t" + ((Members)getIntent().getParcelableExtra("my_parcel")).getMemberId());

//        Toast.makeText(this, ((Members)getIntent().getParcelableExtra("my_parcel")).getMemberName(),
//                Toast.LENGTH_SHORT).show();
    }
}
