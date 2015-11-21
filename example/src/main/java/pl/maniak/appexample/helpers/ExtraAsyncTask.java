package pl.maniak.appexample.helpers;

import android.os.AsyncTask;

/**
 * Created by Sony on 2015-11-21.
 */
public abstract class ExtraAsyncTask<Progress, Result> extends AsyncTask<Void, Progress, AsyncResult<Result>> {

    @Override
    protected final AsyncResult<Result> doInBackground(Void... params) {
        try {
            return new AsyncResult<Result>(doInAnotherThread());
        } catch (Exception e) {
            return new AsyncResult<Result>(e);
        }
    }

    @Override
    protected final void onPostExecute(AsyncResult<Result> resultAsyncResult) {
        if(resultAsyncResult.exceptionHasBeenThrown()) {
            onException(resultAsyncResult.getException());
        } else {
            onSuccess(resultAsyncResult.getResult());
        }
        onFinally();
    }

    protected abstract Result doInAnotherThread() throws Exception;

    protected void onSuccess(Result result) {}

    protected void onException(Exception e) {}

    protected void onFinally() {}
}
