package pl.maniak.appexample.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.maniak.appexample.R;
import pl.maniak.appexample.model.NavDraItem;

/**
 * Created by maniak on 27.09.15.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context context;
    private List<NavDraItem> navDraItemList;

    public NavigationDrawerAdapter(Context context, List<NavDraItem> list) {
        this.context = context;
        this.navDraItemList = list;
    }

    @Override
    public int getCount() {
        return navDraItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return navDraItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_navigation_drawer, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.navDrawTextView);
        tv.setText(navDraItemList.get(position).getTitle());

        ImageView img = (ImageView) convertView.findViewById(R.id.navDrawImage);
        img.setImageResource(navDraItemList.get(position).getImage());

        return convertView;
    }
}
