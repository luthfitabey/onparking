package com.anjilibey.onpark;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_parkir_worker extends Fragment implements View.OnClickListener {
    EditText metPlat;
    RadioButton mrdGP;
    RadioButton mrdHY;
    RadioButton mrdSV;
    RadioGroup metJK;
    Button mbtnScanBarcode;
    Button mbtnSimpan;
    ProgressDialog loading;
    ContentResolver contentResolver;
    Context context = this.getContext();
    RadioButton radioButton;



    Context mContext = getContext();
    BaseApiService mApiService;
    public static fragment_parkir_worker newInstance() {
        fragment_parkir_worker fragment = new fragment_parkir_worker();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_parkir_worker, container, false);
        mApiService = UtilsApi.getAPIService();

        metPlat = view.findViewById(R.id.etPlat);
        metJK = view.findViewById(R.id.jk);
        mbtnSimpan = view.findViewById(R.id.btnSimpan);
        mbtnScanBarcode = view.findViewById(R.id.btnScanBarcode);
        mbtnSimpan.setOnClickListener(this);
        mbtnScanBarcode.setOnClickListener(this);

        metJK.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdGP:
                        radioButton = metJK.findViewById(R.id.rdGP);
                        Log.d("Selected: ", radioButton.getText().toString());
                        break;

                    case R.id.rdSV:
                        radioButton = metJK.findViewById(R.id.rdSV);
                        Log.d("Selected: ", radioButton.getText().toString());
                        break;

                    case R.id.rdHY:
                        radioButton = metJK.findViewById(R.id.rdHY);
                        Log.d("Selected: ", radioButton.getText().toString());
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                mApiService.parkingRequest(
                        metPlat.getText().toString(),
                        radioButton.getText().toString())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getContext(), "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getContext(), "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                Toast.makeText(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                            }
                        });

                break;
            case R.id.btnScanBarcode:
                IntentIntegrator.forSupportFragment(this).initiateScan();
                Log.d("Scan button", "Clicked");
            break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult.getContents() != null) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("Canceled, result code: ", ""+resultCode);
                Toast.makeText(context, "Scan gagal", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_OK) {
                String licensePlate = intentResult.getContents();
                Log.d("OK, result code: ", ""+resultCode);

                metPlat.setText(licensePlate);
            }

        }

    }
}