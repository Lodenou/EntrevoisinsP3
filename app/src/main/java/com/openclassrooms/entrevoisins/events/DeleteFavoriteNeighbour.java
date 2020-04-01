package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

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
