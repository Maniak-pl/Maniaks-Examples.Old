package pl.maniak.appexample.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.helpers.AsyncResult;

/**
 * Created by Sony on 2015-11-21.
 */
public class AsyncTaskEducativoFragment extends Fragment {

    Button startTaskBtn, startTaskExceptionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainGitHubFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_educativo_asynctask, null);
        startTaskBtn = (Button) root.findViewById(R.id.educativoAsyncTaskStartTaskBtn);
        startTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExtremelySampleTask().execute(false);
            }
        });

        startTaskExceptionBtn = (Button) root.findViewById(R.id.educativoAsyncTaskStartTaskExceptionBtn);
        startTaskExceptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExtremelySampleTask().execute(true);
            }
        });


        return root;
    }

    private class ExtremelySampleTask extends AsyncTask<Boolean, Integer, AsyncResult<String>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),
                    getActivity().getString(R.string.educativo_asynctask_progress_dialog_title),
                    getActivity().getString(R.string.educativo_asynctask_progress_dialog_message));
        }

        @Override
        protected AsyncResult<String> doInBackground(Boolean... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    publishProgress(i);
                    Thread.sleep(1000);
                    if(i>=3 && params[0]==true) {
                        throw new Exception(getActivity().getString(R.string.educativo_asynctask_exception_message));
                    }
                }
                return new AsyncResult("Educativo");
            } catch (Exception e) {
                L.e("ExtremelySampleTask.doInBackground() ", e);

                return new AsyncResult(e);
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0];
            progressDialog.setMessage(String.valueOf(i));
        }

        @Override
        protected void onPostExecute(AsyncResult<String> result) {
            progressDialog.dismiss();
            String message;

            if (!result.exceptionHasBeenThrown()) {
                message = getActivity().getString(R.string.educativo_asynctask_result_message) + result.getResult();
            } else {
                message = getActivity().getString(R.string.educativo_asynctask_result_exception) + result.getException().getMessage();
            }

            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

}
