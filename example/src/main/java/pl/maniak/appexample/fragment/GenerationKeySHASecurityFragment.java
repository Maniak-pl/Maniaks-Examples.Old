package pl.maniak.appexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by pliszka on 22.02.16.
 */
public class GenerationKeySHASecurityFragment extends Fragment {

    TextView mKeyTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_security_generation_key_sha, null);
        mKeyTv = (TextView) root.findViewById(R.id.fragmentSecuritySHAKeyTv);
        mKeyTv.setText("Klucz: "+buildKey());
        return root;
    }

    public String buildKey(){

        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        sb.append("encryptionSalt");
        String key = generationKeySHA512(sb.toString()).substring(10, 74);
        L.d("Key: " + key);
        //
        return key;

    }

    private String generationKeySHA512(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
