package enterprise.sample.com.search_with_arraylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context cont;
    LayoutInflater layinf;
    List<PeopleName> peoplelist;
    ArrayList<PeopleName> peoplearray;

    public ListViewAdapter(Context con, List<PeopleName> people){
        cont = con;
        peoplelist = people;
        this.layinf = LayoutInflater.from(cont);
        this.peoplearray = new ArrayList<PeopleName>();
        this.peoplearray.addAll(people);
    }

    public class ViewHolder {
        TextView textView;
    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public PeopleName getItem(int position) {
        return peoplelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layinf.inflate(R.layout.model_item, null);
            holder.textView = (TextView)convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(peoplelist.get(position).getName());

        return convertView;
    }

    public void myFilter(String name){
        name = name.toLowerCase(Locale.getDefault());
        peoplelist.clear();
        if (name.length() == 0){
            peoplelist.addAll(peoplearray);
        } else {
            for (PeopleName PL : peoplearray){
                if (PL.getName().toLowerCase(Locale.getDefault()).contains(name)){
                    peoplelist.add(PL);
                }
            }
        }
        notifyDataSetChanged();
    }
}

