package pl.maniak.appexample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by maniak on 23.02.16.
 */
public class SoldiersOfMobileExitModalFragment extends Fragment {

    ExitCallback exitCallback;

    Button mExitBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainSecurityFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_soldiers_of_mobile_exit_modals, null);

        mExitBtn = (Button) root.findViewById(R.id.modalsExitButton);
        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitCallback.exit();
            }
        });

        setHasOptionsMenu(true);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        exitCallback = (ExitCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        exitCallback = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.eixt_modal_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_exit_modal:
                exitCallback.exit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface ExitCallback {
        void exit();
    }
}
