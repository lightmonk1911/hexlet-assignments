package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;

import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {
    private Map<String, User> users;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws IOException {
        // BEGIN
        if (users == null) {
            readDB();
        }

        return users.values().stream().toList();
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
        body.append(pageStart());

        String rows = getUsers().stream().map(user -> cell(user.getId())
                        + cell(link(
                        "users/" + user.getId(),
                        user.getFirstName() + " " + user.getLastName()
                        ))
                        + cell(user.getEmail()))
                .map(this::row)
                .collect(Collectors.joining());

        body.append(table(rows));

        body.append(pageEnd());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private String link(String href, String content) {
        return "<a href=\"" + href + "\">" + content + "</a>";
    }

    private String table(String content) {
        return "<table class=\"table\">" + content + "</table>";
    }

    private String row(String content) {
        return "<tr>" + content + "</tr>";
    }

    private String cell(String content) {
        return "<td>" + content + "</td>";
    }

    private String pageStart() {
        return """
                                <!DOCTYPE html>
                                <html lang=\"ru\">
                                    <head>
                                        <meta charset=\"UTF-8\">
                                        <title>Example application | Users</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">                    </head>
                                    <body>
                                """;
    }

    private String userProps(User user) {
        String id = row(cell("id") + cell(user.getId()));
        String firstname = row(cell("firstname") + cell(user.getFirstName()));
        String lastname = row(cell("lastname") + cell(user.getLastName()));
        String email = row(cell("email") + cell(user.getEmail()));

        return table(id + firstname + lastname + email);
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        if (users == null) {
            readDB();
        }

        if (!users.containsKey(id)) {
            response.sendError(404, "Not found");
            return;
        }

        User user = users.get(id);
        StringBuilder body = new StringBuilder();
        body.append(pageStart());

        body.append(userProps(user));

        body.append(pageEnd());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private String pageEnd() {
        return """
                    </body>
                </html>
                """;
    }

    private void readDB() throws JsonProcessingException {
        String content = readFile("src/main/resources/users.json");
        ObjectMapper mapper = new ObjectMapper();
        List<User> usersList = mapper.readValue(content, new TypeReference<>() {
        });
        users = new HashMap<>();
        for (User user : usersList) {
            users.put(user.getId(), user);
        }
    }

    private static String readFile(String path) {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content;
    }
}
