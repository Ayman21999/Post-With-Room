package com.example.postwithroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postwithroom.Database.PostDataBase;
import com.example.postwithroom.Pojo.Posts;
import com.example.postwithroom.Pojo.User;
import com.example.postwithroom.PostAdapter;
import com.example.postwithroom.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    Button insert,get;
    EditText title , body ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final PostDataBase postDataBase = PostDataBase.getInstance(this);
        insert = findViewById(R.id.insert);
        get = findViewById(R.id.get);
        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        recyclerView = findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PostAdapter adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titletxt = title.getText().toString() ;
                String bodytxt = body.getText().toString() ;

                postDataBase.postDao().insertPost(new Posts(titletxt,bodytxt,new User(1 ,"Ayman")))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe( Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("ttt","Add Success");
                            }

                            @Override
                            public void onError( Throwable e) {
                                Log.d("ttt",e.getMessage());
                            }
                        });
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataBase.postDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Posts>>() {
                            @Override
                            public void onSubscribe( Disposable d) {

                            }

                            @Override
                            public void onSuccess( List<Posts> posts) {
                            adapter.setList( posts);
                            adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError( Throwable e) {

                            }
                        });

            }
        });

    }
}