package controllers;

import models.Constants;
import helpers.HashUtils;
import models.User;
import play.mvc.Controller;
import play.i18n.Messages;

public class PublicContentBase extends Controller {

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type, String code){
        
        if (type.equals(Constants.User.TEACHER) && !code.equals(Constants.User.TEACHER_KEY)){
            flash.put("error", Messages.get("Public.register.error.teacher_code"));
            register();
        }

        User u = new User(username, HashUtils.getMd5(password), type, -1);
        u.save();
        registerComplete();
    }

    public static void registerComplete(){
        render();
    }
}
