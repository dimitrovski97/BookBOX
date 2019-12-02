package com.example.dimit.bookbox.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dimit.bookbox.BookActivity;
import com.example.dimit.bookbox.Models.Book;
import com.example.dimit.bookbox.Models.BookApiClient;
import com.example.dimit.bookbox.Models.BookApiRequest;
import com.example.dimit.bookbox.Models.BookResponse;
import com.example.dimit.bookbox.R;
import com.example.dimit.bookbox.ViewHolders.BookViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BooksFragment extends Fragment {
    String info;
    List<Book> data;
    RecyclerView rv;
    RecyclerAdapter adapter;
    private android.support.v7.widget.SearchView searchView= null;

    public BooksFragment() {
        data = new ArrayList<>();
        adapter = new RecyclerAdapter(data);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        try{
            info = getArguments().getString("info");
        }catch (Exception e){

        }
        if(info!=null)
            initList(info);
        return rv;
    }




    private void initList(String s) {
        final List<Book> downloadedList = new ArrayList<>();
        BookApiRequest service = BookApiClient.getRetrofit().create(BookApiRequest.class);
        service.getAllBooks(s).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if(response.isSuccessful() && response.body().getBooks()!=null){
                    for(Book item : response.body().getBooks()){
                        downloadedList.add(item);
                    }
                    adapter.setBookList(downloadedList);

                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    /**
     * A Simple Adapter for the RecyclerView
     */
    public class RecyclerAdapter extends RecyclerView.Adapter<BookViewHolder> {
        public RecyclerAdapter(List<Book> bookList) {
            this.bookList = bookList;
        }

        public List<Book> getBookList() {
            return bookList;
        }

        public void setBookList(List<Book> bookList) {
            this.bookList = bookList;
            notifyDataSetChanged();
        }

        private List<Book> bookList;


        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
            BookViewHolder viewHolder = new BookViewHolder(view);
            return viewHolder;
        }



        @Override
        public void onBindViewHolder(BookViewHolder holder, int position) {
            final Book item = bookList.get(position);
            if(bookList!=null) {
                holder.bind(item);
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), BookActivity.class);
                        intent.putExtra("Title", item.getVolumeInfo().getTitle());
                        intent.putExtra("Authors", item.getVolumeInfo().getAuthors().toString());
                        intent.putExtra("Date", item.getVolumeInfo().getPublishedDate());
                        intent.putExtra("description", item.getVolumeInfo().getDescription());
                        intent.putExtra("imageUrl", item.getVolumeInfo().getImageLinks().getThumbnail());

                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if(bookList != null){
                return bookList.size();
            }
            return 0;
        }
    }
}