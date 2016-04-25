package pl.maniak.appexample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.section.udacity.analytics.util.Utility;

/**
 * Created by Maniak on 2015-09-29.
 */
public class GoogleAnalyticsFragment extends Fragment {

    @Bind(R.id.analyticsCategory)
    AutoCompleteTextView analyticsCategory;
    @Bind(R.id.analyticsAction)
    AutoCompleteTextView analyticsAction;
    @Bind(R.id.analyticsLabel)
    AutoCompleteTextView analyticsLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("GoogleAnalyticsFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_google_analytics, null);

        ButterKnife.bind(this, root);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.sendEvnetBtn, R.id.sendScreenBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendEvnetBtn:
                if (validateFilds()) {
                    String category = analyticsCategory.getText().toString();
                    String action = analyticsAction.getText().toString();
                    String label = analyticsLabel.getText().toString();
                    App.getAnalytics().sendEvent(category, action, label);
                    clearAllViews();
                }
                break;
            case R.id.sendScreenBtn:
                App.getAnalytics().sendScreenView(getClass().getSimpleName());
                break;
        }
    }

    private boolean validateFilds() {
        if (isEmpty(analyticsAction) || isEmpty(analyticsCategory) || isEmpty(analyticsLabel)) {
            Toast.makeText(getActivity(), "All fields must be completed", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private boolean isEmpty(AutoCompleteTextView view) {
        return view.getText().toString().isEmpty();
    }

    private void clearAllViews() {
        analyticsCategory.setText("");
        analyticsAction.setText("");
        analyticsLabel.setText("");
    }

    public void sendProductHit() {

        Product product1 = new Product()
                .setName("Pen")
                .setPrice(1)
                .setId("8548565845")
                .setQuantity(3);

        Product product2 = new Product()
                .setName("notebook")
                .setPrice(2)
                .setId("654845612")
                .setQuantity(5);

        Product product3 = new Product()
                .setName("Technical block")
                .setPrice(3)
                .setId("354845684")
                .setQuantity(1);

        // Get a unique transaction ID
        String tID = Utility.getUniqueTransactionId("486518");
        ProductAction productAction =
                new ProductAction(ProductAction.ACTION_PURCHASE)
                        .setTransactionId(tID)
                        .setProductActionList("All the things");

        Tracker tracker = App.getAnalytics().getTracker();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Purchase")
                .setAction("Purchase cart")
                .setLabel("Paper")
                .addProduct(product1)
                .addProduct(product2)
                .addProduct(product3)
                .setProductAction(productAction)
                .build());

        Toast.makeText(getActivity(), "The products were shipped", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sendProductBtn)
    public void onClick() {
        sendProductHit();
    }
}
