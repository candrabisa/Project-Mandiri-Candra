package com.candra.candraprojectjualbelihp.adapter;

import java.util.ArrayList;
import java.util.List;
import com.candra.candraprojectjualbelihp.R;
import com.candra.candraprojectjualbelihp.model.Handphone;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ListAdapterHandphone extends BaseAdapter implements Filterable {
    private Context context;
    private List<Handphone>list, filterd;
    public ListAdapterHandphone(Context context, List<Handphone> list) {
        this.context = context;
        this.list = list;
        this.filterd = this.list;
    }
    @Override
    public int getCount() {
        return filterd.size();
    }
    @Override
    public Object getItem(int Position) {
        return filterd.get(position);
    }
    @Override
    public long getItem(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_row, null);
        }
        Handphone hp = filterd.get(position);
        TextView textNama = (TextView)
                convertView.findViewById(R.id.text_nama);
        textNama.setText(hp.getNama());
        TextView textHarga = (TextView)
                convertView.findViewById(R.id.text_harga);
        textHarga.setText(hp.getHarga());
        return convertView;
    }
    @Override
    public Filter getFilter() {
        HandphoneFilter filter = new HandphoneFilter();
        return filter;
    }

    private class HandphoneFilter extends filter {
        @Override
        protected FilterResults performFiltering(CharSequence constrain) {
            List<Handphone> filteredData = new ArrayList<Handphone>();
            FilterResults result = new FilterResutls();
            String filterString = constrain.toString().toLowerCase();
            for (Handphone hp : list) {
                if (hp.getNama().toLowerCase().contains(filterString)) {
                    filteredData.add(hp);
                }
            }
            result.count = filteredData.size();
            result.values = filteredData;
            return result;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterd = (List<Handphone>) results.values;
            notifyDataSetChanged();
        }
    }
}