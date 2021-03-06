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


public class profil extends Fragment implements View.OnClickListener {
    TextView mEmail, mName, mNIF, mMajors;
    Button mEdit;
    BaseApiService mApiService;
    Context mContext = getContext();
    ProgressDialog loading;
    public Integer identify;

    public static profil newInstance() {
        profil fragment = new profil();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url =
                "http://10.203.241.42:8000/api/student/profile";

        FetchData fetchData = new FetchData();
        fetchData.execute(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        mEmail = view.findViewById(R.id.etEmail);
        mName = view.findViewById(R.id.etNama);
        mNIF = view.findViewById(R.id.etNif);
        mMajors = view.findViewById(R.id.etProdi);
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
                connection.setRequestProperty("Authorization", "Bearer $2y$10$N2pDmA.R1yUPOJzE50GmbuJMWb1/6OYZgXLw4jui4jAcFQyV.zdC.");
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
            String email, name, nif, majors;
            int id;
            //Log.d("TesS", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                email = jsonObject.getJSONObject("data").getString("email");
                JSONObject studentObject = jsonObject.getJSONObject("data").getJSONObject("student");
                name = studentObject.getJSONObject("data").getString("name");
                nif = studentObject.getJSONObject("data").getString("nif");
                majors = studentObject.getJSONObject("data").getString("majors");
                id = studentObject.getJSONObject("data").getInt("id");

                Log.d("GetEmail", email);
                Log.d("GetName", name);
                Log.d("GetNIF", nif);
                Log.d("GetMajors", majors);
                Log.d("getId", id+"");

                mEmail.setText(email);
                mName.setText(name);
                mNIF.setText(nif);
                mMajors.setText(majors);
                identify = id;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                mApiService.profileRequest(
                        mName.getText().toString(),
                        mNIF.getText().toString(),
                        mMajors.getText().toString())
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
