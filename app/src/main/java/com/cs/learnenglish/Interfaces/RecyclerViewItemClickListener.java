package com.cs.learnenglish.Interfaces;

import android.view.View;

/**
 * Created by Qais Rasuli on 9/18/2019.
 */

public interface RecyclerViewItemClickListener {

    public void onClick(View view, int position);

    public void onLongClick(View view, int position);

}