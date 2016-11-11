package com.alorma.travis.view;

import com.alorma.travis.domain.MessagesRepository;
import com.alorma.travis.domain.TravisPayload;
import com.alorma.travis.domain.response.MessageResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessagesController {

    @Autowired
    @Qualifier("firebase")
    private MessagesRepository messagesRepository;

    @RequestMapping(value = "/notifications", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<MessageResponse> handleMessage(@RequestBody TravisPayload event) throws Exception {
        sendLog(event);
        return messagesRepository.sendMessage(event);
    }

    private void sendLog(TravisPayload event) {
        if (event != null) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("------------------------------------");
            System.out.println("Travis POST:");
            String json = new Gson().toJson(event);
            System.out.println(json);
            System.out.println("------------------------------------");
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

}
