package com.anjilibey.onpark;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hp on 06/05/2018.
 */

public class UtilsApi {
    private String vnama, vemail, vpassword, vnif, vprodi;

    public static final String BASE_URL_API = "http://10.203.253.33:8000/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
//    public void parsingarray(String datajson){
//        try {
//
//            JSONObject jsonObj = new JSONObject(datajson); //ambil object json
//            JSONObject jsonpandawa = jsonObj.getJSONObject("pandawa"); //ambil obj pandawa
//            JSONArray anggota = jsonpandawa.getJSONArray("anggota"); //ambil array anggota
//            for (int i = 0; i < anggota.length(); i++) {
//                JSONObject jsonobject = anggota.getJSONObject(i);
//                vnama = jsonobject.getString("nama");
//                vemail = jsonobject.getString("email");
//                vpassword = jsonobject.getString("password");
//                vnif = jsonobject.getString("nif");
//                vprodi = jsonobject.getString("prodi");
//                JSONObject vnama = jsonobject.getJSONObject("nama"); //ambil array tlp
//                JSONObject vemail = jsonobject.getJSONObject("email");
//                JSONObject vpassword = jsonobject.getJSONObject("password");
//                JSONObject vnif = jsonobject.getJSONObject("nif");
//                JSONObject vprodi = jsonobject.getJSONObject("prodi");
////                vhp=kontak.getString("hp");
////                vrmh=kontak.getString("rumah");
//            }
////            tnama.setText(vnama);
////            talamat.setText(valamat);
////            tfoto.setText(vfoto);
////            thp.setText(vhp);
////            trumah.setText(vrmh);
//        } catch (Throwable t) {
//            Log.e("My App", "Could not parse malformed JSON: \"" + datajson + "\"");
//        }
//    }
}