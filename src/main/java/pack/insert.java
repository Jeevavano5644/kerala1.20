package pack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kerala.dbcode;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insert")
public class insert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public insert() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        dbcode d = null;

        try {
            String name = request.getParameter("name");
            String numberStr = request.getParameter("number");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (name == null || name.isEmpty() || 
                numberStr == null || numberStr.isEmpty() || 
                email == null || email.isEmpty() || 
                address == null || address.isEmpty()) {
                out.print("<h1>All fields are required!</h1>");
                return;
            }

            long number;
            try {
                number = Long.parseLong(numberStr);
            } catch (NumberFormatException e) {
                out.print("<h1>Invalid mobile number format!</h1>");
                return;
            }

            // Insert data
            d = new dbcode();
            int result = d.insert(name, number, email, address);

            if (result > 0) {
                out.print("<h1>Registration Successful</h1>");
            } else {
                out.print("<h1>Registration Failed</h1>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h1>Error occurred: " + e.getMessage() + "</h1>");
        } finally {
            if (d != null) {
                try {
                    d.closeConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to POST handler
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported.");
    }
}
