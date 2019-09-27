package br.com.moodle.analytics.servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AprioriDetails
 */
public class AprioriDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
	protected
    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        File arquivo = new File(request.getParameter("pathfile")+request.getParameter("module")+"_result_Apriori.txt");
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();

        response.setContentType("text/plain"); // tipo do conteúdo
        response.setContentLength(tamanho);  // opcional
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");

        OutputStream output;
		try {
			output = response.getOutputStream();
	        Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

}
