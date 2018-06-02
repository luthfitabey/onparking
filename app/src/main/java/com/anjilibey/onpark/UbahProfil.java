//package com.anjilibey.onpark;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class UbahProfil extends AppCompatActivity {
//
//    @BindView(R.id.txtNama)
//    TextView mtxtNama;
//    @BindView(R.id.txtEmail) TextView mtxtEmail;
//    @BindView(R.id.txtNif) TextView mtxtNif;
//    @BindView(R.id.txtProdi) TextView mtxtProdi;
//
//    ProgressDialog loading;
//
//    Context mContext;
//    BaseApiService mApiService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profil);
//        getSupportActionBar().hide();
//
//        ButterKnife.bind(this);
//        mContext = this;
//
//        String url =
//                "http://10.203.253.33:8000/api/student/profile";
//
//        profil.FetchData fetchData = new profil.FetchData();
//        fetchData.execute(url);
//
//        btnSimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
//
//            }
//        });
//    }
//    public class FetchData extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... strings) {
//            String result = null;
//
//            try {
//                URL url = new URL(strings[0]);
//
//                HttpURLConnection connection =
//                        (HttpURLConnection) url.openConnection();
//
//                connection.setRequestMethod("GET");
//                connection.setRequestProperty("Accept", "application/json");
//                connection.setRequestProperty("Content-type", "application/json");
//                connection.setRequestProperty("Authorization", "Bearer $2y$10$N2pDmA.R1yUPOJzE50GmbuJMWb1/6OYZgXLw4jui4jAcFQyV.zdC.");
//                connection.connect();
//
//                int response = connection.getResponseCode();
//                Log.d("DEBUG1", "RESPONSE CODE : " + response);
//
//                //mendownload data yang berupa String
//                BufferedReader r = new BufferedReader(
//                        new InputStreamReader(connection.getInputStream())
//                );
//
//                StringBuilder total = new StringBuilder();
//                String line;
//
//                while ((line = r.readLine()) != null) {
//                    total.append(line);
//                }
//                result = total.toString();
//                Log.d("DEBUG1", "RESULT :" + result);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            String email, name, nif, majors;
//            //Log.d("TesS", s);
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                email = jsonObject.getJSONObject("data").getString("email");
//                JSONObject studentObject = jsonObject.getJSONObject("data").getJSONObject("student");
//                name = studentObject.getJSONObject("data").getString("name");
//                nif = studentObject.getJSONObject("data").getString("nif");
//                majors = studentObject.getJSONObject("data").getString("majors");
//
//                Log.d("GetEmail", email);
//                Log.d("GetName", name);
//                Log.d("GetNIF", nif);
//                Log.d("GetMajors", majors);
//
//                mEmail.setText(email);
//                mName.setText(name);
//                mNIF.setText(nif);
//                mMajors.setText(majors);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//}
