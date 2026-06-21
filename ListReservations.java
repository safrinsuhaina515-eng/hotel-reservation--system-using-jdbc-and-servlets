import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListReservations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Registered Guests</title></head><body>");
        out.println("<h2>Registered Guests</h2>");

        String sql = "SELECT guest_name, room_type, check_in, check_out "
                   + "FROM reservations ORDER BY check_in DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            out.println("<table border='1' cellpadding='6'>");
            out.println("<tr><th>Name</th><th>Room Type</th><th>Check-In</th><th>Check-Out</th></tr>");

            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("guest_name") + "</td>");
                out.println("<td>" + rs.getString("room_type") + "</td>");
                out.println("<td>" + rs.getString("check_in") + "</td>");
                out.println("<td>" + rs.getString("check_out") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            if (!hasRows) {
                out.println("<p>No reservations found.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("<p><a href='index.html'>Back to reservation form</a></p>");
        out.println("</body></html>");
    }
}
