package com.example.dimit.bookbox.Models;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApiRequest {
    @GET("/books/v1/volumes")
    Call<BookResponse> getAllBooks(@Query("q") String q);

    @GET("/books/v1/volumes/{id}")
    Call<Book> getBookById(@Path("id") String id);
}
