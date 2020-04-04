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


    void addNeighbour (Neighbour neighbour);

    /**
     * Get favorites neighbours' list
     */
    List<Neighbour> getFavorites();

    /**
     * Set favorites neighbours
     */


    void setFavoriteNeighbours(int position, boolean tfFavorite);


    void addFavorites(Neighbour neighbour);

    void deleteFavorite(Neighbour neighbour);



    Neighbour getNeighbourById(Integer id);



}


