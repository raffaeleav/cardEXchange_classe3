package ricerca.service;

import acquisto.Offerta;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.FacadeDAO;

import java.io.IOException;
import java.util.List;

/**
 * La classe permette di mostrare la pagina di una carta
 * (con le relative offerte), dalla pagina dei risultati
 * di una ricerca, tramite una servlet che viene richiamata
 * dal comando della funzione 'mostra offerte'
 * @author Raffaele Aviello
 */

@WebServlet("/mostra-pagina-carta-servlet")
public class MostraPaginaCartaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardId = Integer.parseInt(request.getParameter("id-carta"));
        FacadeDAO facadeDAO = new FacadeDAO();

        List<Offerta> offersList = (List<Offerta>) facadeDAO.doRetrieveAllByIdCarta(Offerta.class, cardId);
        request.setAttribute("lista-offerte", offersList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/carta.jsp");
        requestDispatcher.forward(request, response);
    }
}
