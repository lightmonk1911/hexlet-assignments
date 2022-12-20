package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        String query = request.getParameter("search");
        List<String> companies;
        if (query == null || query.isEmpty()) {
            companies = getCompanies();
        } else {
            companies = getCompanies().stream().filter(company -> company.contains(query)).collect(Collectors.toList());
        }

        String output = companies.isEmpty() ? "Companies not found" : String.join("\n", companies);

        PrintWriter out = response.getWriter();
        out.println(output);
        // END
    }
}
