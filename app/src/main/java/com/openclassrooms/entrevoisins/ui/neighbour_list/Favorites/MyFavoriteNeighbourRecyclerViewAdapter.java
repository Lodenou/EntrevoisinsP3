package com.openclassrooms.entrevoisins.ui.neighbour_list.Favorites;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbour;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>{

    private List<Neighbour> mfavNeighbour;
    private Neighbour mfavoriteNeighbour;
    //private ImageButton mDeleteButton;
    private NeighbourApiService mApiService= DI.getNeighbourApiService();

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mfavNeighbour = items;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_neighbour, parent, false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyNeighbourRecyclerViewAdapter.ViewHolder holder, int position) {
        Neighbour neighbour = mfavNeighbour.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("id", neighbour.getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            // supprime un favoris lors du clic sur l'icone bin de l'onglet favorites
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteFavoriteNeighbour(neighbour));


            }


        });


    }

        @Override
        public int getItemCount () {
            return mfavNeighbour.size();
        }
    }





