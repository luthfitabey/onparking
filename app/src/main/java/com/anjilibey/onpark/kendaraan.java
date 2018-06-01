package com.anjilibey.onpark;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

    RadioGroup metJK;
    RadioButton radioButton;
    Button mbtnSimpan;


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
        mbtnSimpan.setOnClickListener(this);

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
                                if (response.isSuccessful()){
                                    Log.i("debug", "onResponse: BERHASIL");
                                    loading.dismiss();
                                    try {
                                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                        if (jsonRESULTS.getString("error").equals("false")){
                                            Toast.makeText(mContext, "TERSIMPAN", Toast.LENGTH_SHORT).show();
                                        } else {
                                            String error_message = jsonRESULTS.getString("error_msg");
                                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.i("debug", "onResponse: GA BERHASIL");
                                    loading.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                Toast.makeText(getContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }


    }

//    public ActionBar getSupportActionBar() {
//        return getDelegate().getSupportActionBar();
//    }
//
//    public AppCompatActivity getDelegate() {
//        Object mDelegate;
//        if (mDelegate == null) {
//            mDelegate = AppCompatDelegate.create(this, this);
//        }
//        return mDelegate;
//    }
}