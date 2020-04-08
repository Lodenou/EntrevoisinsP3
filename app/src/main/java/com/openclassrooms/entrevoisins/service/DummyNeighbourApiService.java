package com.openclassrooms.entrevoisins.service;



import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.List;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    //TODO
    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();






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
        Neighbour mNeighbour  = DUMMY_NEIGHBOURS.get(rand.nextInt(DUMMY_NEIGHBOURS.size()));
        neighbours.add(mNeighbour);


    }

//    @Override
//    public void setFavorite(Neighbour neighbour, boolean val) {
//        int idx = neighbours.indexOf(neighbour);
//        neighbours.get(idx).setFavorite(val);
//    }

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
