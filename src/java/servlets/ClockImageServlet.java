/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import static javax.swing.Spring.width;

/**
 *
 * @author agath
 */
@WebServlet(name = "ClockImageServlet", urlPatterns = {"/clock.png"})
public class ClockImageServlet extends HttpServlet {

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
        response.setContentType("image/png");
        
        /*Definindo tamanho do relogio: quadrado de 200 pixels*/
        int size =200;
        /*Escritas diminutivas vão automatico*/
        BufferedImage image = new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        /*Renderização suave*/
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /*Cor do meu canvas para desenhar*/
        g2d.setColor(Color.WHITE);
        /*Quadrado com a cor definida*/
        g2d.fillRect(0, 0, size, size);
        
        /*Pegando valor isolado pela hora*/
        int hour = java.time.LocalTime.now().getHour();
        /*pegando valor isolado pelo minuto*/
        int minute = java.time.LocalTime.now().getMinute();
       
        
        double hourAngle = (hour+minute / 60.0) * 30.0;
        double minuteAngle = minute * 6;
        
        
        /*Definindo ponteiro hora*/
        /*cor ponteiro*/
        g2d.setColor(Color.BLACK);
        /*comprimento e largura do traço ponteiro*/
        int hourHandLength = (int) (size * 0.4);
        int hourHandWidth = (int) (size * 0.05);
        /*Tem que começar no centro (eixo x e Y)*/
        int hourHandX = size / 2;
        int hourHandY = size / 2;
        /*Definindo para onde vai o traço*/
        int hourHandEndX = hourHandX + (int) (hourHandLength * Math.sin(Math.toRadians(hourAngle)));
        int hourHandEndY = hourHandY - (int) (hourHandLength * Math.cos(Math.toRadians(hourAngle)));
        /*Definindo qual traço*/
        g2d.setStroke(
                new java.awt.BasicStroke(
                        hourHandWidth,
                        java.awt.BasicStroke.CAP_ROUND,
                        java.awt.BasicStroke.JOIN_ROUND
                )
        );
        g2d.drawLine(hourHandX, hourHandY, hourHandEndX, hourHandEndY);
        
        /*Definindo ponteiro minuto*/
        g2d.setColor(Color.BLACK);
        int minuteHandLength = (int) (size * 0.6);
        int minuteHandWidth = (int) (size * 0.03);
        int minuteHandX = size / 2;
        int minuteHandY = size / 2;
        int minuteHandEndX = minuteHandX + (int) (minuteHandLength * Math.sin(Math.toRadians(minuteAngle)));
        int minuteHandEndY = minuteHandY - (int) (minuteHandLength * Math.cos(Math.toRadians(minuteAngle)));
        g2d.setStroke(
                new java.awt.BasicStroke(
                        minuteHandWidth,
                        java.awt.BasicStroke.CAP_ROUND,
                        java.awt.BasicStroke.JOIN_ROUND)
        );
        g2d.drawLine(minuteHandX, minuteHandY, minuteHandEndX, minuteHandEndY);
        /*Libera os recursos do objeto grafico*/
        g2d.dispose();
        javax.imageio.ImageIO.write(image, "png", response.getOutputStream());
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
