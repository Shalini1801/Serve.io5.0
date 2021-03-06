package com.spoton.serveio.ui.general.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spoton.serveio.Common;
import com.spoton.serveio.R;
import com.spoton.serveio.model.Ngo;
import com.spoton.serveio.ui.NgoUser.activity.NgoHomeActivity;

import io.paperdb.Paper;

public class NgoRegisterActivity extends AppCompatActivity {
    EditText et_name_ngo_register, et_location_ngo_register,et_regno_ngo_register,et_email_ngo_register,et_phno_ngo_register,
            et_password_ngo_register, et_description_ngo_register;


    Button btn_login_ngo_register,btn_ngo_register;


    FirebaseDatabase rootNode;
    DatabaseReference reference, mRef;
    ProgressBar pb_ngo_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_register);

        Paper.init(this);


        et_name_ngo_register = findViewById(R.id.et_name_ngo_register);
        et_location_ngo_register = findViewById(R.id.et_location_ngo_register);
        et_regno_ngo_register = findViewById(R.id.et_regno_ngo_register);
        et_email_ngo_register = findViewById(R.id.et_email_ngo_register);
        et_phno_ngo_register = findViewById(R.id.et_phno_ngo_register);
        et_password_ngo_register = findViewById(R.id.et_password_ngo_register);
        et_description_ngo_register = findViewById(R.id.et_description_ngo_register);
        btn_login_ngo_register = findViewById(R.id.btn_login_ngo_register) ;

        btn_ngo_register = findViewById(R.id.btn_ngo_register) ;
        pb_ngo_register = findViewById(R.id.pb_ngo_register);
        btn_ngo_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode=FirebaseDatabase.getInstance();
                mRef = rootNode.getReference("Ngos").child("no");
                reference=rootNode.getReference("Ngos").child("users");


                String name=et_name_ngo_register.getText().toString().trim();
                String location=et_location_ngo_register.getText().toString().trim();
                String regno=et_regno_ngo_register.getText().toString().trim();
                String email=et_email_ngo_register.getText().toString().trim();
                String phno=et_phno_ngo_register.getText().toString().trim();
                String password=et_password_ngo_register.getText().toString().trim();
                String description=et_description_ngo_register.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phno)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(getApplicationContext(), "Enter location!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(regno)) {
                    Toast.makeText(getApplicationContext(), "Enter certified registration number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Enter description!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                btn_login_ngo_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(NgoRegisterActivity.this, NgoLoginActivity.class) );
                        pb_ngo_register.setVisibility(View.VISIBLE);
                    }
                });

                addNgo( name, location, regno, email, phno, password, description);

                        }
            });

    }

    private void addNgo(final String name, final String location, final String regno, final String email, final String phno, final String password, final String description) {

        final DatabaseReference mRef = rootNode.getReference("Ngos").child("no");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double no = dataSnapshot.getValue(Double.class);
                no = no+1;
                String id = "Ngo"+no.intValue();
                Ngo ngo= new Ngo(id,name,location,regno,email,password,description,phno,"",false);
                reference.child(id).setValue(ngo);
                mRef.setValue(no.intValue());

                Paper.book().write(Common.User_Key,id);
                Paper.book().write(Common.userType,"Ngos");

                pb_ngo_register.setVisibility(View.VISIBLE);
                Toast.makeText(NgoRegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NgoRegisterActivity.this, NgoHomeActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
