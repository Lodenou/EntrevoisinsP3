package com.openclassrooms.entrevoisins.service;

import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    //TODO
    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();
    private Neighbour mNeighbour;
    private RecyclerView mRecyclerView;




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void addNeighbour(Neighbour neighbour) {
        Random rand = new Random();
        mNeighbour  = DUMMY_NEIGHBOURS.get(rand.nextInt(DUMMY_NEIGHBOURS.size()));
        neighbours.add(mNeighbour);


    }

    @Override
    public void setFavoriteNeighbours(int position, boolean tfFavorite) {
        neighbours.get(position).setFavorite(tfFavorite);
    }

    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    @Override
    public void addFavorites(Neighbour neighbour) {
        if (!favorites.contains(neighbour))
        {
            favorites.add(neighbour);
        }
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbour))
        {
            favorites.remove(neighbour);
        }
    }

    @Override
    public Neighbour getNeighbourById(Integer id) {
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == id) {
                return neighbour;
            }
        }
        return null;
    }
}
