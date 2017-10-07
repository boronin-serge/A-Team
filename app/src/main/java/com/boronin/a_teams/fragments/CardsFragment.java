package com.boronin.a_teams.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.InternetRequestTask;
import com.boronin.a_teams.R;
import com.boronin.a_teams.cards.CommentCard;
import com.boronin.a_teams.cards.PhotoCard;
import com.boronin.a_teams.cards.PostCard;
import com.boronin.a_teams.cards.TodoCard;
import com.boronin.a_teams.cards.UsersCard;
import com.boronin.a_teams.utilities.Constants;
import com.boronin.a_teams.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by boronin on 07.10.17.
 */

public class CardsFragment extends AbstractTabFragment{

    public static final int LAYOUT = R.layout.cards_fragment;

    PostCard postCard;
    CommentCard commentCard;

    ProgressBar usersProgressBar;
    ProgressBar photoProgressBar;
    ProgressBar todoProgressBar;

    TextView postNumberError;
    TextView commentNumberError;

    EditText editPostNumber;
    EditText editCommentNumber;

    Button getPostButton;
    Button getCommentButton;
    ImageView usersUpdateButton;
    ImageView photoUpdateButton;
    ImageView todoUpdateButton;

    public static CardsFragment getInstance(Context context) {
        CardsFragment fragment = new CardsFragment();
        fragment.setTitle(context.getString(R.string.cards_tab_label));
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPostCard();
        initCommentCard();
        initUserCard();
        initPhotoCard();
        initTodoCard();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }


    public void initPostCard() {
        postCard = new PostCard((CardView) view.findViewById(R.id.post_card));

        postNumberError = view.findViewById(R.id.post_number_error);
        getPostButton = view.findViewById(R.id.getPostButton);
        getPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPostButtonClicked();
            }
        });

        editPostNumber = view.findViewById(R.id.edit_post_number);
        editPostNumber.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){
                String strEnteredVal = editPostNumber.getText().toString();

                if(!strEnteredVal.equals("")){
                    int n=Integer.parseInt(strEnteredVal);
                    if(n > Constants.POST_LIMIT){
                        getPostButton.setEnabled(false);
                        postNumberError.setVisibility(View.VISIBLE);
                        editPostNumber.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorError));
                    }else{
                        getPostButton.setEnabled(true);
                        postNumberError.setVisibility(View.INVISIBLE);
                        editPostNumber.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
                    }
                }

            }
        });
    }

    private void initCommentCard() {
        commentCard = new CommentCard((CardView) view.findViewById(R.id.comment_card));

        commentNumberError = view.findViewById(R.id.comments_number_error);
        getCommentButton = view.findViewById(R.id.getCommentButton);
        getCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCommentButtonClicked();
            }
        });

        editCommentNumber = view.findViewById(R.id.edit_comment_number);
        editCommentNumber.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){
                String strEnteredVal = editCommentNumber.getText().toString();

                if(!strEnteredVal.equals("")){
                    int n=Integer.parseInt(strEnteredVal);
                    if(n > Constants.COMMENT_LIMIT){
                        getCommentButton.setEnabled(false);
                        commentNumberError.setVisibility(View.VISIBLE);
                        editCommentNumber.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorError));
                    }else{
                        getCommentButton.setEnabled(true);
                        commentNumberError.setVisibility(View.INVISIBLE);
                        editCommentNumber.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
                    }
                }

            }
        });
    }

    private void initUserCard() {
        usersProgressBar = view.findViewById(R.id.usersProgressBar);
        usersUpdateButton = view.findViewById(R.id.usersUpdateButton);
        usersUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdateUsersButtonClicked();
            }
        });
        usersRequest();
    }

    private void initPhotoCard() {
        photoProgressBar = view.findViewById(R.id.photoProgressBar);
        photoUpdateButton = view.findViewById(R.id.photoUpdateButton);
        photoUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdatePhotoButtonClicked();
            }
        });
        photoRequest();
    }

    private void initTodoCard() {
        todoProgressBar = view.findViewById(R.id.todoProgressBar);
        todoUpdateButton = view.findViewById(R.id.todoUpdateButton);
        todoUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdateTodoButtonClicked();
            }
        });
        todoRequest();
    }

    // Handlers for buttons

    public void onPostButtonClicked() {
        hideKeyboard(editPostNumber);
        postRequest();
    }

    public void onCommentButtonClicked() {
        hideKeyboard(editCommentNumber);
        commentsRequest();
    }

    public void onUpdateUsersButtonClicked() {
        usersRequest();
    }

    public void onUpdatePhotoButtonClicked() {
        photoRequest();
    }

    public void onUpdateTodoButtonClicked() {
        todoRequest();
    }

    // Request functions

    private void postRequest() {
        String postNumber = editPostNumber.getText().toString();
        URL postUrl = NetworkUtils.buildPostsUrl(postNumber);
        new InternetRequestTask(postCard).execute(postUrl);
    }

    private void commentsRequest() {
        String commentNumber = editCommentNumber.getText().toString();
        URL commentUrl = NetworkUtils.buildCommentsUrl(commentNumber);
        new InternetRequestTask(commentCard).execute(commentUrl);
    }

    private void usersRequest() {
        usersUpdateButton.setVisibility(View.INVISIBLE);
        usersProgressBar.setVisibility(View.VISIBLE);
        URL userUrl;
        UsersCard users = new UsersCard((CardView) view.findViewById(R.id.users_card), Constants.USERS_LIMIT);

        for (int i = 0; i < Constants.USERS_LIMIT; i++) {
            userUrl = NetworkUtils.buildUsersUrl(String.valueOf(i + 1));
            new InternetRequestTask(users).execute(userUrl);
        }
    }

    private void photoRequest() {
        photoUpdateButton.setVisibility(View.INVISIBLE);
        photoProgressBar.setVisibility(View.VISIBLE);
        URL photoUrl = NetworkUtils.buildPhotosUrl(Constants.PHOTO_NUMBER.toString());
        PhotoCard photo = new PhotoCard((CardView) view.findViewById(R.id.photo_card));
        new InternetRequestTask(photo).execute(photoUrl);
    }

    private void todoRequest() {
        todoUpdateButton.setVisibility(View.INVISIBLE);
        todoProgressBar.setVisibility(View.VISIBLE);
        int randomNumber = (int) (Math.random() * Constants.TODO_LIMIT);
        URL todoUrl = NetworkUtils.buildTodosUrl(String.valueOf(randomNumber));
        TodoCard todos = new TodoCard((CardView) view.findViewById(R.id.todo_card));
        new InternetRequestTask(todos).execute(todoUrl);
    }

    // Utils functions

    private void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
