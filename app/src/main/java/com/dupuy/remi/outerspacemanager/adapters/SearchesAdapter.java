package com.dupuy.remi.outerspacemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dupuy.remi.outerspacemanager.models.Search;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.databinding.SearchesAdapterBinding;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class SearchesAdapter extends ArrayAdapter<Search> {
    private final Context context;
    private final List<Search> values;
    public SearchesAdapter(Context context, List<Search> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SearchesAdapterBinding binding = SearchesAdapterBinding.inflate(inflater, parent, false);

        binding.setSearch(values.get(position));
        return binding.getRoot();
    }
}