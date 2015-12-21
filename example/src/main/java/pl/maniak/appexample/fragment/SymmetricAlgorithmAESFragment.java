package pl.maniak.appexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by pliszka on 21.12.15.
 */
public class SymmetricAlgorithmAESFragment extends Fragment {

    private TextView mOrginTv;
    private TextView mEncodedTv;
    private TextView mDecodedTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_security_symmetric_encryption_aes, null);
        mOrginTv = (TextView)root.findViewById(R.id.fragmentSecurityAESOrginTv);
        mEncodedTv = (TextView) root.findViewById(R.id.fragmentSecurityAESEncodedTv);
        mDecodedTv = (TextView) root.findViewById(R.id.fragmentSecurityAESDecodedTv);

        setupAlgorithmAES();

        return root;
    }

    public void setupAlgorithmAES() {
        // Original text
        String theTestText = "This is just a simple test";

        mOrginTv.setText("\n[ORIGINAL]:\n" + theTestText + "\n");

        // Set up secret key spec for 128-bit AES encryption and decryption
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            L.e("AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(theTestText.getBytes());
        } catch (Exception e) {
            L.e("AES encryption error");
        }

        mEncodedTv.setText("[ENCODED]:\n" +
                Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

        // Decode the encoded data with AES
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            L.e("AES decryption error");
        }
        mDecodedTv.setText("[DECODED]:\n" + new String(decodedBytes) + "\n");

    }
}
