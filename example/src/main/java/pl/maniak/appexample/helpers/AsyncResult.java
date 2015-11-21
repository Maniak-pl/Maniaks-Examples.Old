package pl.maniak.appexample.helpers;

/**
 * Created by Sony on 2015-11-21.
 */
public class AsyncResult<T> {

    private Exception exception;
    private T result;

    public AsyncResult(Exception exception) {
        this.exception = exception;
    }

    public AsyncResult(T result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean exceptionHasBeenThrown() {
        return exception != null;
    }
}
