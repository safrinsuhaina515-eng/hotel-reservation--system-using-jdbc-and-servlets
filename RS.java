import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import java.sql.*;

public class RS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String room = request.getParameter("room");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO reservations(guest_name, room_type, check_in, check_out) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, room);
            ps.setString(3, checkin);
            ps.setString(4, checkout);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3>Reservation Successful!</h3>");
            } else {
                out.println("<h3>Reservation Failed!</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
