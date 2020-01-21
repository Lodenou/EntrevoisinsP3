package com.openclassrooms.entrevoisins.ui.neighbour_list.Favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class FavoriteNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    public static FavoriteNeighbourFragment newInstance() {

        FavoriteNeighbourFragment favfragment = new FavoriteNeighbourFragment();
        return favfragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        return view;
    }
}
