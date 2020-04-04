package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.Glide.GlideApp;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private List<Neighbour> mFavoriteNeighbours;
    private Neighbour mNeighbour;
    private ImageView mImageView;
    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private RecyclerView mRecyclerView;

    ListNeighbourPagerAdapter mPagerAdapter;
    private final Handler handler = new Handler();


    /**
     * Override de la méthode pour revenir en arrière avec le back button
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail);

        mImageView = findViewById(R.id.toolbarImage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!mApiService.getFavorites().contains(mNeighbour)) {


                    mApiService.addFavorites(mNeighbour);
                    Snackbar.make(view, "Ajouté aux favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageResource(R.drawable.ic_star_yellow);

                } else {


                    mApiService.deleteFavorite(mNeighbour);
                    Snackbar.make(view, "Retiré des favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageResource(R.drawable.ic_star_borderblack);

                }
            }

        });

        int id = getIntent().getIntExtra("id", -1);
        mNeighbour = DI.getNeighbourApiService().

                getNeighbourById(id);
        if (mApiService.getFavorites().contains(mNeighbour))
         {
            fab.setImageResource(R.drawable.ic_star_yellow);
        }

        // ajout automatique du prénom de l'utilisateur dans son détail
        getSupportActionBar().setTitle(mNeighbour.getName());


        // ajout de l'image de l'utilisateur sur le détail
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(mImageView);


        // bouton Back en haut à gauche
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

    }


    @Override
    public void onBackPressed() {
        // Ne crée plus de nouvelle activité quand on press back, le retour sur ListNeighbourActivity me semble nécessaire pour refresh

        NavUtils.navigateUpTo(this, new Intent(this,
                ListNeighbourActivity.class));


    }

}