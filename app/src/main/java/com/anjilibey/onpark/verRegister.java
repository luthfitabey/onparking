package com.anjilibey.onpark;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;


public class verRegister extends AppCompatActivity {
    @BindView(R.id.btnUser) Button btnUser;
    @BindView(R.id.btnPegawai) Button btnPegawai;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verregister);
        getSupportActionBar().hide();


    }

    public void btnUser(View view) {
        startActivity(new Intent(verRegister.this, Register.class));
    }

    public void btnPegawai(View view) {
        startActivity(new Intent(verRegister.this, RegisterOp.class));
    }

}
