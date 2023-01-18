package exercise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        // BEGIN
        Connection connection = (Connection) context.getAttribute("dbConnection");
        try {
            Statement statement = connection.createStatement();
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));

            ResultSet resultSet = statement.executeQuery("SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET " + 10 * (page - 1) + ";");
            List<Map<String, String>> articles = new ArrayList<>();

            while (resultSet.next()) {
                articles.add(Map.of(
                                // Так можно получить значение нужного поля в текущей строке
                                "id", resultSet.getString("id"),
                                "title", resultSet.getString("title")
                        )
                );
            }

            request.setAttribute("articles", articles);
            request.setAttribute("nextPage", page + 1);
            request.setAttribute("prevPage", page - 1);

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        int id = Integer.parseInt(Optional.ofNullable(getId(request)).orElse("0"));
        // BEGIN
        String query = "SELECT title, body FROM articles WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Map<String, String> article = Map.of(
                    "title", resultSet.getString("title"),
                    "body", resultSet.getString("body")
            );

            request.setAttribute("article", article);
        } catch (SQLException e) {
            System.out.println(e);
            if (e.getErrorCode() == 2000) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
