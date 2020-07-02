package com.readapp.backend.controllers;

import com.readapp.backend.dto.ChatResponse;
import com.readapp.backend.models.Chat;
import com.readapp.backend.models.http.ChatForm;
import com.readapp.backend.models.http.HttpStatus;
import com.readapp.backend.models.http.MessageForm;
import com.readapp.backend.models.http.Response;
import com.readapp.backend.models.utils.ChatPreviewInfo;
import com.readapp.backend.modules.annotations.AutoRefreshToken;
import com.readapp.backend.services.ChatService;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ChatController {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response createDirectChat(
            @RequestParam("toUser") Long toUser,
            @RequestHeader("Authorization") String Authorization
    ){
        Long fromUser = Long.parseLong(JWTUtil.getUserId(Authorization));
        Chat chat;
        try {
            chat = chatService.createDirectChat(fromUser, toUser);
        } catch (Exception e){
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(new ChatResponse(chat));
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response sendMessage(@RequestBody MessageForm form,
                                @RequestHeader("Authorization") String Authorization){
        try {
            chatService.sendMessage(form);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(null);
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response getChat(@RequestParam("chatId") Long chatId,
                            @RequestHeader("Authorization") String Authorization){
        return Response.success(null);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response loadMessageEvents(@RequestHeader("Authorization") String Authorization) {
        Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
        Map<String, ChatPreviewInfo> map;
        try {
            map = chatService.fetchAllEvents(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        return Response.success(map);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @RequiresAuthentication
    @AutoRefreshToken
    public Response findMessagesByChat(@RequestParam("id") Long id,
                                       @RequestParam("page") int page,
                                       @RequestParam("size") int size,
                                       @RequestHeader("Authorization") String Authorization) {
        try {
            Long uid = Long.parseLong(JWTUtil.getUserId(Authorization));
            return Response.success(chatService.findMessagesByChat(id, uid, page, size));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.simple(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
