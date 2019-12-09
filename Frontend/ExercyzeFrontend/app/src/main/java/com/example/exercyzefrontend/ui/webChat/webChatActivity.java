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
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class webChatActivity extends AppCompatActivity {

    Button  sendBtn, backBtn;
    EditText message;
    TextView chatroom;

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_chat);
        sendBtn =(Button)findViewById(R.id.sendBtn);
        backBtn =(Button)findViewById(R.id.backBtn);
        message =(EditText)findViewById(R.id.messageinput);
        chatroom =(TextView)findViewById(R.id.chatroom);
        String userNameStr = getIntent().getStringExtra("user_name");
        Draft[] drafts = {new Draft_6455()};
        String w = "ws://coms-309-sb-7.misc.iastate.edu:8080/websocket/"+userNameStr;
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
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();

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
