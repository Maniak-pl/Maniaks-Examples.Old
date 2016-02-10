package pl.maniak.appexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by pliszka on 10.02.16.
 */
public class FloatingActionButtonGitHubFragment extends Fragment implements View.OnClickListener {

    TextView mInfoTv;
    View actionA, actionB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("FloatingActionButtonGitHubFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_github_floating_action_button, null);

        mInfoTv = (TextView) root.findViewById(R.id.floatingActionButtonInfoTv);
        actionA = root.findViewById(R.id.action_a);
        actionB = root.findViewById(R.id.action_b);

        actionA.setOnClickListener(this);
        actionB.setOnClickListener(this);

        setupFloatingActionButton(root);

        return root;
    }

    private void setupFloatingActionButton(ViewGroup root) {

        FloatingActionButton button = new FloatingActionButton(getActivity().getBaseContext());
        button.setTitle(getActivity().getString(R.string.floating_button_title_show));
        button.setIcon(R.drawable.ic_hide_and_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInfoTv.setVisibility(mInfoTv.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) root.findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(button);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_a:
                Toast.makeText(getActivity(), getActivity().getString(R.string.floating_button_toast_a), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_b:
                Toast.makeText(getActivity(), getActivity().getString(R.string.floating_button_toast_b), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}