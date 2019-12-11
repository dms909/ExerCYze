package com.example.exercyzefrontend.ui.webChat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.ui.progress.UserProgressActivity;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class that hadles with web chat page on the app
 */
public class webChatActivity extends AppCompatActivity {

    private Button  sendBtn, backBtn;
    private EditText message;
    private TextView chatroom;
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_chat);
        sendBtn =(Button)findViewById(R.id.sendBtn);
        backBtn =(Button)findViewById(R.id.backBtn);
        message =(EditText)findViewById(R.id.messageinput);
        chatroom =(TextView)findViewById(R.id.chatroom);
        final String userNameStr = getIntent().getStringExtra("user_name");
        Draft[] drafts = {new Draft_6455()};
        // if back button is pressed then goes back to user profile page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(webChatActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("user_name", userNameStr);
                startActivity(userProfileIntent);
            }
        });

        String w = "ws://coms-309-sb-7.misc.iastate.edu:8080/websocket/"+userNameStr;
        // starting up connection
        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s=chatroom.getText().toString();
                    chatroom.setText(s+"\n"+message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        // catch errors
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();

        // send message in the message field to server then give response back with message with username next to them
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(message.getText().toString());
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });

    }

}
