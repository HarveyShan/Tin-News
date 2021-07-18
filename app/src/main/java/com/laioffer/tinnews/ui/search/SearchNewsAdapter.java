package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.tinnews.R;
import com.squareup.picasso.Picasso;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {


    private List<Article> articles = new ArrayList<>();
    private ItemCallback itemCallback;


    interface ItemCallback {
        void onOpenDetails(Article article);
    }


    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 1. Supporting data:
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    // 2. Adapter overrides:
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        //speed optimization
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);

        // TODO: 4/5/2021 add switch
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border_24dp);
        holder.itemTitleTextView.setText(article.title);
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);

//      Use the itemCallback to inform the implementer the onOpenDetails event when an item is clicked.
//      In SearchNewsAdapter.onBindViewHolder we call onOpenDetails whenever the item is clicked.
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    // 3. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }

}