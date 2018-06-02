package com.anjilibey.onpark;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_profil_worker extends Fragment implements View.OnClickListener {
    TextView mEmail, mName, mNumb;
    Button mEdit;
    BaseApiService mApiService;
    Context mContext = getContext();
    ProgressDialog loading;

    public static fragment_profil_worker newInstance() {
        fragment_profil_worker fragment = new fragment_profil_worker();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url =
                "http://10.203.241.42:8000/api/operator/profile";

        FetchData fetchData = new FetchData();
        fetchData.execute(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_profil_worker, container, false);

        mEmail = view.findViewById(R.id.etEmail);
        mName = view.findViewById(R.id.etNama);
        mNumb = view.findViewById(R.id.etNumb);
        mEdit = view.findViewById(R.id.btnSimpan);
        mEdit.setOnClickListener(this);
        mApiService = UtilsApi.getAPIService();

        return view;
    }
    public class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = null;

            try {
                URL url = new URL(strings[0]);

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer $2y$10$rIyUZRvhyzB2u5pd43bKpeHaYytciGz5tkQFYwgVzDgkyDd0oDJX6");
                connection.connect();

                int response = connection.getResponseCode();
                Log.d("DEBUG1", "RESPONSE CODE : " + response);

                //mendownload data yang berupa String
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                StringBuilder total = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();
                Log.d("DEBUG1", "RESULT :" + result);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String email, name, number;
            //Log.d("TesS", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                email = jsonObject.getJSONObject("data").getString("email");
                JSONObject operatorObject = jsonObject.getJSONObject("data").getJSONObject("operator");
                name = operatorObject.getJSONObject("data").getString("name");
                number = operatorObject.getJSONObject("data").getString("operator_number");

                Log.d("GetEmail", email);
                Log.d("GetName", name);
                Log.d("GetNIF", number);

                mEmail.setText(email);
                mName.setText(name);
                mNumb.setText(number);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                mApiService.operatorRequest(
                        mEmail.getText().toString(),
                        mName.getText().toString(),
                        mNumb.getText().toString())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getContext(), "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(getContext(), "Gagal Disimpan", Toast.LENGTH_SHORT).show();

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
}





