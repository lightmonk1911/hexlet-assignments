package exercise.controllers;

import io.javalin.core.validation.BodyValidator;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;

import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    }

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    }

    public void create(Context ctx) {

        // BEGIN

        User user = ctx.bodyAsClass(User.class);
        if (!isUserValid(user)) {
            ctx.status(422);
            String json = DB.json().toJson("errors");
            ctx.json(json);
            return;
        }

        user.save();

        ctx.json(DB.json().toJson(user));
        // END
    }

    public void update(Context ctx, String id) {
        // BEGIN
        User user = ctx.bodyAsClass(User.class);
        if (!isUserValid(user)) {
            ctx.status(422);
            String json = DB.json().toJson("errors");
            ctx.json(json);
            return;
        }

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        newUser.setId(id);
        newUser.update();

        ctx.json(DB.json().toJson(user));
        // END
    }

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne()
                .delete();

        ctx.json(id);
        // END
    }

    private boolean isUserValid(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();

        return !firstName.isEmpty()
                && !lastName.isEmpty()
                && EmailValidator.getInstance().isValid(email)
                && password.length() >= 4
                && password.matches("\\d+");
    }
}
