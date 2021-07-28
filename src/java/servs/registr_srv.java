package servs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "registr_srv", urlPatterns = {"/registr_srv"})
public class registr_srv extends HttpServlet {

    @Resource(mappedName = "jdbc/VolgoTransEnergo")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PreparedStatement stmt;
        String text = "Не верные имя пользователя/пароль";
        String login = request.getParameter("e_mail");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String pwd = request.getParameter("pass");
        try (PrintWriter out = response.getWriter()) {
            Connection conn = ds.getConnection();
            String query = "INSERT INTO ANDREI.REGISTER(E_MAIL, NAME, LAST_NAME, PASSWORD)"
                    + " VALUES ('" + login + "', '" + firstName + "',"
                    + " '" + lastName + "', '" + pwd + "')";
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegData</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align='center'>Пользователь успешно создан</h1>");
            out.println("<form align='center' action='index.html'>");
            out.println("<input type='submit' value='Продолжить' style='background: #4169E1; color:#FFF'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            System.out.println("-----" + ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
