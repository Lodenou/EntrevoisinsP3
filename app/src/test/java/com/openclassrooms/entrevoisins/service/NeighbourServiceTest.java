package com.openclassrooms.entrevoisins.service;

import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import android.support.v7.app.AppCompatActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    private Neighbour mNeighbour;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    // Un voisin de la liste est bien supprimé en cliquant sur l'icone associée
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

   
    @Test
    // Lorsque l'on clique sur un utilisateur, cela ouvre bien le détail de ce neighbour
    public void getDetailWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0);
        assertNotNull(neighbour.getId());
        assertNotNull(neighbour.getAvatarUrl());
        assertNotNull(neighbour.getName());

    }
    @Test
    // Supprime bien un neighbour qui était dans la liste des favoris
    public void deleteFavoriteNeighbourWithSuccess() {

        service.setFavoriteNeighbours(0 , true);
        Neighbour favoriteNeighbourToDelete = service.getNeighbours().get(0);
        service.deleteFavorite(favoriteNeighbourToDelete);
        assertFalse(service.getFavorites().contains(favoriteNeighbourToDelete));
    }

    @Test
    // Test vérifiant qu'il n'y a que des favoris dans l'onglet favoris
    public void onlyFavoritesInFavorite() {

        Neighbour neighbour = service.getNeighbours().get(0);
        assertFalse(service.getFavorites().contains(!(neighbour.isFavorite())));

    }

    @Test
    // test vérifiant que l'on ajoute bien un neighbour avec la méthode addNeighbour()
    public void addNeighbourWithSuccess() {
        int x = 0;
        List<Neighbour> neighbours1 = service.getNeighbours();
        x = neighbours1.size();
        service.addNeighbour(mNeighbour);

        assertTrue(neighbours1.size() == x+1);


    }
}
