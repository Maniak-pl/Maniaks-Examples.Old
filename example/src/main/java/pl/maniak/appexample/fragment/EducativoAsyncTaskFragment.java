package pl.maniak.appexample.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.helpers.ExtraAsyncTask;

/**
 * Created by Sony on 2015-11-21.
 */
public class EducativoAsyncTaskFragment extends Fragment {

    Button startTaskBtn, startTaskExceptionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainGitHubFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_educativo_asynctask, null);
        startTaskBtn = (Button) root.findViewById(R.id.educativoAsyncTaskStartTaskBtn);
        startTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExtremelySampleTask(false).execute();
            }
        });

        startTaskExceptionBtn = (Button) root.findViewById(R.id.educativoAsyncTaskStartTaskExceptionBtn);
        startTaskExceptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExtremelySampleTask(true).execute();
            }
        });

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }

    private class ExtremelySampleTask extends ExtraAsyncTask<Integer, String> {

        public static final int INTERVAL = 500;
        private boolean throwException;
        private ProgressDialog progressDialog;

        private ExtremelySampleTask(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    getActivity().getString(R.string.educativo_asynctask_progress_dialog_title),
                    getActivity().getString(R.string.educativo_asynctask_progress_dialog_message));
        }

        @Override
        protected String doInAnotherThread() throws Exception {
            for (int i = 0; i < 5; i++) {
                publishProgress(i);
                Thread.sleep(INTERVAL);
            }
            if (throwException) {
                throw new Exception(getActivity().getString(R.string.educativo_asynctask_exception_message));
            }
            return "Educativo";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            L.i("ExtremelySampleTask.onProgressUpdate() called with " + "values = [" + values[0] + "]");
            int i = values[0];
            progressDialog.setMessage(String.valueOf(i));
        }

        @Override
        protected void onSuccess(String result) {
            if(isResumed()) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.educativo_asynctask_result_success) + result, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onException(Exception e) {
            if(isResumed()){
                Toast.makeText(getActivity(), getActivity().getString(R.string.educativo_asynctask_result_error) + e, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onFinally() {
            if(isResumed()) {
                progressDialog.dismiss();
            }
        }
    }
}
