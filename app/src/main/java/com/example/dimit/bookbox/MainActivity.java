package com.example.dimit.bookbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dimit.bookbox.Fragments.BooksFragment;
import com.example.dimit.bookbox.Fragments.ForumFragment;
import com.example.dimit.bookbox.Fragments.HomeFragment;
import com.example.dimit.bookbox.Fragments.LoginFragment;
import com.example.dimit.bookbox.Models.Book;
import com.example.dimit.bookbox.Models.BookApiClient;
import com.example.dimit.bookbox.Models.BookApiRequest;
import com.example.dimit.bookbox.Models.BookResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.SearchView searchView= null;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


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
                case R.id.navigation_forum:
                    selectedFragment=new ForumFragment();
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
        
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        
        
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new HomeFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            Toast.makeText(this, "Ne sum najaven", Toast.LENGTH_SHORT).show();
        }else{
//        updateUI(currentUser);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Bundle bundle = new Bundle();
                bundle.putString("info", s);
                BooksFragment booksFragment = new BooksFragment();
                booksFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, booksFragment).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return true;
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

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
