package yfkj.weatherstation.adapter;

import android.app.Activity;

import java.util.List;

import yfkj.weatherstation.R;


public class DlAdapter extends CommonAdapter<String> {
    private Activity act;

    public DlAdapter(Activity act, List<String> data, int layoutId) {
        super(act, data, layoutId);
        this.act = act;
    }

    @Override
    public void convert(ViewHolder holder, String data, int position) {
        holder.setText(R.id.name, data);

    }
}
