package pl.maniak.appexample.section.rxjava.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

public class RxJavaSearchPeopleFragment extends Fragment {


    @BindView(R.id.rxjava_search_button)
    Button searchButton;

    @BindView(R.id.rxjava_search_et)
    EditText queryEditText;

    @BindView(R.id.rxjava_search_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.rxjava_search_recycler_view)
    RecyclerView list;

    private PeopleAdapter adapter;
    private PeopleSearchEngine peopleSearchEngine;
    private Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("RxJavaSearchPeopleFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_rxjava_search_people, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        ButterKnife.bind(this, root);

        setAdapter();

        return root;
    }

    private void setAdapter() {
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapter = new PeopleAdapter());

        List<String> people = Arrays.asList(getResources().getStringArray(R.array.people));
        peopleSearchEngine = new PeopleSearchEngine(people);
    }

    @Override
    public void onStart() {
        super.onStart();
        Observable<String> textChangeStream = createTextChangeObservable();
        Observable<String> buttonClickStream = createButtonClickObservable();

        Observable<String> searchTextObservable = Observable.merge(textChangeStream, buttonClickStream);

        disposable = searchTextObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext((str) -> showProgressBar())
                .observeOn(Schedulers.io())
                .map((query) -> peopleSearchEngine.search(query))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    hideProgressBar();
                    showResult(result);
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    protected void showResult(List<String> result) {
        if (result.isEmpty()) {
            Toast.makeText(getContext(), R.string.rxjava_search_nothing_found, Toast.LENGTH_SHORT).show();
            adapter.setPeople(Collections.<String>emptyList());
        } else {
            adapter.setPeople(result);
        }
    }

    private Observable<String> createButtonClickObservable() {
        return Observable.create((emitter) -> {
            searchButton.setOnClickListener((View) -> {
                emitter.onNext(queryEditText.getText().toString());
            });
            emitter.setCancellable(() -> searchButton.setOnClickListener(null));
        });
    }

    private Observable<String> createTextChangeObservable() {
        Observable<String> textChangeObservable = Observable.create((emitter) -> {
            final TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    emitter.onNext(s.toString());
                }
            };
            queryEditText.addTextChangedListener(watcher);
            emitter.setCancellable(() -> queryEditText.removeTextChangedListener(watcher));
        });
        return textChangeObservable
                .filter((query) -> query.length() >= 2)
                .debounce(1000, TimeUnit.MILLISECONDS);
    }
}
