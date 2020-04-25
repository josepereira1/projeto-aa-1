/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import business.GMS;
import business.Game;
import business.GameAlreadyExistsException;
import business.GameDontExistException;
import business.MddpPersistentManager;
import business.Platform;
import business.PlatformDontExistException;
import business.UserDontExistException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

/**
 *
 * @author joaomarques
 */
@WebServlet(name = "AddGame", urlPatterns = {"/AddGame"})
public class AddGame extends HttpServlet {

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
        try {
            List<Platform> platforms = (List) gms.allPlatforms(s);
            request.setAttribute("platforms", platforms);
            request.getRequestDispatcher("/WEB-INF/addgame.jsp").forward(request, response);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
    
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
        try {
            Game game = new Game();
            game.setName(request.getParameter("name"));
            game.setYear(Integer.parseInt(request.getParameter("year")));
            game.setPrice(Float.parseFloat(request.getParameter("price")));
            game.setDescription(request.getParameter("description"));
            Platform platform = gms.getPlatformByName(request.getParameter("platform"),s);
            game.setPlatform(platform);
            gms.registerGame(game,s);
            gms.addGameToUser(game.getName(), (String) request.getAttribute("name"),s);
            request.getRequestDispatcher("/MyGames").forward(request, response);
        } catch(NumberFormatException e) {
            //TODO
            e.printStackTrace();
        } catch(GameAlreadyExistsException e) {
            //TODO
            e.printStackTrace();
        } catch(PersistentException | PlatformDontExistException | GameDontExistException | UserDontExistException e) {
            e.printStackTrace();
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
