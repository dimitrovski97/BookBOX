package com.example.dimit.bookbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dimit.bookbox.Fragments.AuthorsFragment;
import com.example.dimit.bookbox.Fragments.BooksFragment;
import com.example.dimit.bookbox.Fragments.HomeFragment;
import com.example.dimit.bookbox.Fragments.LoginFragment;
import com.example.dimit.bookbox.Models.Book;
import com.example.dimit.bookbox.Models.BookApiClient;
import com.example.dimit.bookbox.Models.BookApiRequest;
import com.example.dimit.bookbox.Models.BookResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.navigation_books:
                    selectedFragment=new BooksFragment();
                    break;
                case R.id.navigation_authors:
                    selectedFragment=new AuthorsFragment();
                    break;
                case R.id.navigation_account:
                    selectedFragment=new LoginFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, selectedFragment).commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new HomeFragment()).commit();
        printItems();
    }
    public void printItems(){
        BookApiRequest service = BookApiClient.getRetrofit().create(BookApiRequest.class);
        service.getAllBooks("Da Vinci").enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if(response.isSuccessful() && response.body().getBooks()!=null){
                    for(Book item : response.body().getBooks()){
                        Toast.makeText(MainActivity.this, item.getId(), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {

            }
        });
    }

}
