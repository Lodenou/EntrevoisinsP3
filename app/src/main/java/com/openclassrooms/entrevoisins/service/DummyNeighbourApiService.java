package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoritesNeighbours = new ArrayList<>();



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
    public void setFavoriteNeighbours(int position, boolean tfFavorite) {
        neighbours.get(position).setFavorite(tfFavorite);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoritesNeighbours.add(neighbour);
            }
        }
        return favoritesNeighbours;
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
