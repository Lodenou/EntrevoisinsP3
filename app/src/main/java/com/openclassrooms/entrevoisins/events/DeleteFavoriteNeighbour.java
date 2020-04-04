package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;


public class DeleteFavoriteNeighbour {


    public Neighbour favoriteNeighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteNeighbour(Neighbour neighbour) {
        this.favoriteNeighbour = neighbour;
    }

}
