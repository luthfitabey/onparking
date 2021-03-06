package com.anjilibey.onpark;

import android.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class kendaraan extends Fragment implements View.OnClickListener {
    EditText metPlat;
    RadioButton mrdMotor;
    RadioButton mrdMobil;
    EditText metMerk;
    EditText metTipe;
    Button mbtnGetBarcode;
    ProgressDialog loading;
    ContentResolver contentResolver;
    RadioGroup metJK;
    RadioButton radioButton;
    Button mbtnSimpan;
    Bitmap QRCode;
ImageView mImageQRCode;

    Context mContext = getContext();
    BaseApiService mApiService;
    public static kendaraan newInstance() {
        kendaraan fragment = new kendaraan();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parkir, container, false);
        mApiService = UtilsApi.getAPIService();

        metPlat = view.findViewById(R.id.etPlat);
        metJK = view.findViewById(R.id.jk);
        metMerk = view.findViewById(R.id.etMerk);
        metTipe = view.findViewById(R.id.etTipe);
        mbtnSimpan = view.findViewById(R.id.btnSimpan);
        mbtnGetBarcode = view.findViewById(R.id.btnGetBarcode);
        mbtnSimpan.setOnClickListener(this);
        mImageQRCode = view.findViewById(R.id.imageViewQRCode);
        mbtnGetBarcode.setOnClickListener(this);
        metJK.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdMobil:
                        radioButton = metJK.findViewById(R.id.rdMobil);
                        Log.d("Selected: ", radioButton.getText().toString());
                        break;

                    case R.id.rdMotor:
                        radioButton = metJK.findViewById(R.id.rdMotor);
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
                mApiService.vehicleRequest(
                        metPlat.getText().toString(),
                        radioButton.getText().toString(),
                        metMerk.getText().toString(),
                        metTipe.getText().toString())
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
            case R.id.btnGetBarcode:
                String licenseNumber = metPlat.getText().toString();
                Log.d("Queued LicensePlate", licenseNumber);
                if (licenseNumber.isEmpty()){
                    Toast.makeText(getContext(),"Masukkan Plat Nomor Terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(licenseNumber, BarcodeFormat.QR_CODE, 300, 300);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        QRCode = bitmap;

                        mImageQRCode.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    break;
                }
        }
    }
}