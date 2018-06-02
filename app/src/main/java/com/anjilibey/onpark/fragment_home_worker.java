package com.anjilibey.onpark;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;


public class fragment_home_worker extends Fragment  {
    TextView mTextPark;
    Button mBtnLogOut;
    SharedPrefManager sharepref;

    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String url =
//                "http://10.203.192.173:8000/api/student/parking";
//
//        FetchData fetchData = new FetchData();
//        fetchData.execute(url);

//       mBtnLogOut.setOnClickListener(new View.OnClickListener() {
//          @Override
//           public void onClick(View v) {
//              SharedPreferences settings = getContext().getSharedPreferences("SP_TOKEN", Context.MODE_PRIVATE);
//              settings.edit().remove("spToken").commit();
//              Intent intent = new Intent(getContext(), Login.class);
//              startActivity(intent);
//           }
//       });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_home_worker, container, false);
        return view;
//        mTextPark = view.findViewById(R.id.txtParkir);
//        mBtnLogOut = view.findViewById(R.id.btnLogOut);
//        mBtnLogOut.setOnClickListener(this);
//
//        return view;
    }

//    @Override
//    public void onClick(View v) {
//        SharedPreferences settings = getContext().getSharedPreferences("SP_TOKEN", Context.MODE_PRIVATE);
//        settings.edit().remove("spToken").commit();
//        Intent intent = new Intent(getContext(), Login.class);
//        startActivity(intent);
//    }
//
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
//            String parkir;
//            Log.d("Tes", s);
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONObject studentObject = jsonObject.getJSONObject("meta");
//                parkir = studentObject.getString("garage_name");
//
//
//                Log.d("getParkir", parkir);
//
//                mTextPark.setText(parkir);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}