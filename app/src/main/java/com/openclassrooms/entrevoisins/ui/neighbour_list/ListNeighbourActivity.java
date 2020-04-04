package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;


import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;



import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbarList)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.fab_add)
    FloatingActionButton mFabaAdd;

    ListNeighbourPagerAdapter mPagerAdapter;
    private NeighbourApiService mApiService= DI.getNeighbourApiService();
    private Neighbour mNeighbour;
    private List<Neighbour> mNeighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);


        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // Lorsque l'on clique sur le bouton add neighbour , ajoute un neighbour
        mFabaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mApiService.addNeighbour(mNeighbour);
                Snackbar.make(view, "Nouveau voisin ajouté", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mViewPager.getAdapter().notifyDataSetChanged();

                initList();



            }
        });
    }

        private void initList() {
            mNeighbours = mApiService.getNeighbours();
            mViewPager.setAdapter(mPagerAdapter);

    }
}
