package pl.maniak.appexample.section.rxjava.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<String> people;

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        holder.title.setText(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people == null ? 0 : people.size();
    }

    public void setPeople(List<String> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
