package com.openclassrooms.entrevoisins.ui.neighbour_list.Favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbour;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteNeighbourFragment extends Fragment {

    private List<Neighbour> mFavoriteNeighbours;
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private MyFavoriteNeighbourRecyclerViewAdapter mAdapter;
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
        View view = inflater.inflate(R.layout.favorite_neighbour_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.list_favorite_neighbours);
        initList();
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }




    // initialisation
    private void initList()
    {

        this.mFavoriteNeighbours = mApiService.getFavorites();

        mRecyclerView.setAdapter(new MyFavoriteNeighbourRecyclerViewAdapter(mFavoriteNeighbours));

    }
    // appelée lorsque l'event est posté
    @Subscribe
    public void onDeleteFavorite(DeleteFavoriteNeighbour event) {
        mApiService.deleteFavorite(event.favoriteNeighbour);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }


    }

