package com.example.firesafety;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    String[] localities;
    AutoCompleteTextView localitySpinner;

    TextInputEditText Username, Email, PhoneNumber, Address, Password1, Password2;
    AutoCompleteTextView Area;
    MaterialButton BtnSignIn;

    private FirebaseAuth firebaseAuth;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Username = findViewById(R.id.signin_username);
        Email = findViewById(R.id.signin_email);
        Password1 = findViewById(R.id.signin_password);
        Password2 = findViewById(R.id.signin_confirm_password);
        PhoneNumber = findViewById(R.id.signin_phone);
        Area = findViewById(R.id.sign_area);
        Address = findViewById(R.id.signin_address);

        BtnSignIn = findViewById(R.id.sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        localities = new String[]{
                "Gulabnagar", "Patel colony", "Sat rasta", "Digvijay Plot", "Townhall",
                "Lakhota Talav", "Khodiyar colony"
        };
        localitySpinner = findViewById(R.id.sign_area);

        ArrayAdapter<String> localityAdapter = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1, localities);
        localitySpinner.setAdapter(localityAdapter);

        BtnSignIn.setOnClickListener(view -> {
            String username = Objects.requireNonNull(Username.getText()).toString();
            String email = Objects.requireNonNull(Email.getText()).toString();
            String password1 = Objects.requireNonNull(Password1.getText()).toString();
            String password2 = Objects.requireNonNull(Password2.getText()).toString();
            String phone = Objects.requireNonNull(PhoneNumber.getText()).toString();
            String area = Area.getText().toString();
            String address = Objects.requireNonNull(Address.getText()).toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2) ||
                    TextUtils.isEmpty(address)) {
                Toast.makeText(this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                if (isValidValidEmail(email)) {
                    if (password1.equals(password2)) {
                        if (isValidPassword(password1)) {
                            registerUser(username, email, password1, area, address, phone);
                        } else {
                            Toast.makeText(this, "Make a Strong Password...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Password is not matching...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Invalid Email...", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @SuppressLint("LongLogTag")
    private void registerUser(String username, String email, String password1, String area, String address, String phone) {

        firebaseAuth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(SignUp.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(username).child("email").setValue(email);
                        FirebaseDatabase.getInstance().getReference().child("users").child(username).child("phone").setValue(phone);
                        FirebaseDatabase.getInstance().getReference().child("users").child(username).child("area").setValue(area);
                        FirebaseDatabase.getInstance().getReference().child("users").child(username).child("address").setValue(address);
                        FirebaseDatabase.getInstance().getReference().child("users").child(username).child("isAdmin").setValue(false);
                        Toast.makeText(SignUp.this, "Registration Done.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, LogIn.class));

                    }
                }).addOnFailureListener(e -> {
                            Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            Log.d("key---------------------:", e.toString());
                        }
                );
    }

    public void logIn(View view) {
        startActivity(new Intent(SignUp.this, LogIn.class));
        finish();
    }

    public static boolean isValidValidEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(EMAIL_PATTERN);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +           // represents starting char
                        "(?=.*[0-9])" +         // represents a digit must occur at least once.
                        "(?=.*[a-z])" +         // represents a lower case alphabet must occur at least once.
                        "(?=.*[A-Z])" +         // represents an upper case alphabet that must occur at least once.
                        "(?=.*[@#$%&-+=()])" +     // represents a special character that must occur at least once.
                        "(?=\\S+$)" +           // white spaces don’t allowed in the entire string.
                        ".{8,20}" +             //  represents at least 8 characters and at most 20 characters.
                        "$");                   // represents the end of the string.


        // password is empty
        if (password == null || password.isEmpty()) {
            return false;
        }

        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        return matcher.matches();
    }
}