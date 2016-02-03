package co.mobiwise.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.mobiwise.sample.R;
import co.mobiwise.sample.model.Song;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExampleViewHolder> {

    private List<Song> songList;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        songList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        ExampleViewHolder holder = new ExampleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ExampleViewHolder holder, int position) {
        Song song = songList.get(position);

        holder.coverName.setText(song.songName);
        holder.singerName.setText(song.singerName);
        Picasso.with(context).load(song.songArt).into(holder.coverIamge);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        ImageView coverIamge;
        TextView coverName, singerName;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            coverIamge = (ImageView) itemView.findViewById(R.id.cover_photo);
            coverName = (TextView) itemView.findViewById(R.id.cover_name);
            singerName = (TextView) itemView.findViewById(R.id.singer_name);
        }
    }
}
