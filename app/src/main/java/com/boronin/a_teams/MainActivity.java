package com.boronin.a_teams;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.cards.CommentCard;
import com.boronin.a_teams.cards.PostCard;
import com.boronin.a_teams.cards.TodoCard;
import com.boronin.a_teams.cards.UsersCard;
import com.boronin.a_teams.models.Comments;
import com.boronin.a_teams.models.Posts;
import com.boronin.a_teams.models.Todo;
import com.boronin.a_teams.models.Users;
import com.boronin.a_teams.utilities.Constants;
import com.boronin.a_teams.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ProgressBar usersProgressBar;
    ProgressBar todoProgressBar;

    TextView postNumberError;
    TextView commentNumberError;

    EditText editPostNumber;
    EditText editCommentNumber;

    Button getPostButton;
    Button getCommentButton;
    ImageView usersUpdateButton;
    ImageView todoUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPostCard();
        initCommentCard();
        initUsers();
        initTodos();

    }

    private void initCommentCard() {
        commentNumberError = (TextView) findViewById(R.id.comments_number_error);
        getCommentButton = (Button) findViewById(R.id.getCommentButton);

        editCommentNumber = (EditText) findViewById(R.id.edit_comment_number);
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
                        editCommentNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
                    }else{
                        getCommentButton.setEnabled(true);
                        commentNumberError.setVisibility(View.INVISIBLE);
                        editCommentNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    }
                }

            }
        });
    }

    public void initPostCard() {
        postNumberError = (TextView) findViewById(R.id.post_number_error);
        getPostButton = (Button) findViewById(R.id.getPostButton);

        editPostNumber = (EditText) findViewById(R.id.edit_post_number);
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
                        editPostNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
                    }else{
                        getPostButton.setEnabled(true);
                        postNumberError.setVisibility(View.INVISIBLE);
                        editPostNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    }
                }

            }
        });
    }

    private void initUsers() {
        usersProgressBar = (ProgressBar) findViewById(R.id.usersProgressBar);
        usersUpdateButton = (ImageView) findViewById(R.id.usersUpdateButton);
    }

    private void initTodos() {
        todoProgressBar = (ProgressBar) findViewById(R.id.todoProgressBar);
        todoUpdateButton = (ImageView) findViewById(R.id.todoUpdateButton);
    }

    // Handlers for buttons

    public void onPostButtonClicked(View view) {
        String postNumber = editPostNumber.getText().toString();
        URL postUrl = NetworkUtils.buildPostsUrl(postNumber);
        PostCard post = new PostCard((CardView) findViewById(R.id.post_card));
        post.model = new Posts();
        new InternetRequestTask(post).execute(postUrl);
    }

    public void onCommentButtonClicked(View view) {
        String commentNumber = editCommentNumber.getText().toString();
        URL commentUrl = NetworkUtils.buildCommentsUrl(commentNumber);
        CommentCard comment = new CommentCard((CardView) findViewById(R.id.comment_card));
        comment.model = new Comments();
        new InternetRequestTask(comment).execute(commentUrl);
    }

    public void onUpdateUsers(View view) {
        usersUpdateButton.setVisibility(View.INVISIBLE);
        usersProgressBar.setVisibility(View.VISIBLE);
        URL userUrl;
        UsersCard users = new UsersCard((CardView) findViewById(R.id.users_card), Constants.USERS_LIMIT);
        users.model = new Users();

        for (int i = 0; i < Constants.USERS_LIMIT; i++) {
            userUrl = NetworkUtils.buildUsersUrl(String.valueOf(i + 1));
            new InternetRequestTask(users).execute(userUrl);
        }
    }

    public void onUpdateTodo(View view) {
        todoUpdateButton.setVisibility(View.INVISIBLE);
        todoProgressBar.setVisibility(View.VISIBLE);
        int radomNumber = (int) (Math.random() * Constants.TODO_LIMIT);
        Log.d("random index for todo", String.valueOf(radomNumber));
        URL todoUrl = NetworkUtils.buildTodosUrl(String.valueOf(radomNumber));
        TodoCard todos = new TodoCard((CardView) findViewById(R.id.todo_card));
        todos.model = new Todo();
        new InternetRequestTask(todos).execute(todoUrl);
    }

}
