/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import business.Checksum;
import business.GMS;
import business.MddpPersistentManager;
import business.User;
import business.UserAlreadyExistsException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import static web.Index.getSession;

/**
 *
 * @author joaomarques
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void responsePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersistentSession s = Index.getSession(request);
        GMS gms = GMS.getGMS();
        User user = new User();
        try {
            user.setEmail(request.getParameter("email"));
            user.setName(request.getParameter("name"));
            user.setPassword(Checksum.getFileChecksum(request.getParameter("password").getBytes(),MessageDigest.getInstance("sha-256")));
            gms.registerUser(user,s);
            request.setAttribute("name",user.getName());
            request.setAttribute("logged",true);
            request.getRequestDispatcher("/MyGames").forward(request,response);
        } catch(NoSuchAlgorithmException | PersistentException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error ocured");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
        } catch(UserAlreadyExistsException e) {
            e.printStackTrace();
            request.setAttribute("error", "User already registered");
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
        }
    }
    
    /**
     * Processes requests for HTTP <code>GET</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void responseGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersistentSession s = Index.getSession(request);
        GMS gms = GMS.getGMS();
        boolean logged = (boolean) request.getAttribute("logged");
        if(logged) {
            request.getRequestDispatcher("/MyGames").forward(request, response);
        } else {
            request.setAttribute("logged",false);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
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
        responseGet(request, response);
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
        responsePost(request, response);
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
