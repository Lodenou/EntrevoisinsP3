package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Get favorites neighbours' list
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Set favorites neighbours
     */

    void setFavoriteNeighbours(int position, boolean tfFavorite);

    Neighbour getNeighbourById(Integer id);
}


