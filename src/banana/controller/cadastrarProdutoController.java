package banana.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banana.model.Produto;

/**
 * Servlet implementation class cadastrarProdutoController
 */
@WebServlet("/cadastrarProdutoController")
public class cadastrarProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cadastrarProdutoController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarProduto.jsp");
			String descricao = request.getParameter("descricao");
			boolean online = false;
			String mensagem = null;

			if (descricao != null && !descricao.isEmpty()) {
				if (request.getParameter("quantidade") != null && !request.getParameter("quantidade").isEmpty()) {
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));
					double preco = Double.parseDouble(request.getParameter("preco"));
					if (request.getParameter("online") != null && request.getParameter("online").equals("on")) {
						online = true;
					} else {
						online = false;
					}
					Produto produto = new Produto(descricao, quantidade, preco, online);
					produto.salvar(produto);

					mensagem = "Produto cadastrado com sucesso!";
				}
			} else {
				mensagem = "Os campos precisam ser preenchidos ";
			}
			request.setAttribute("mensagem", mensagem);
			dispatcher.forward(request, response);
		} catch (Exception se) {
			se.printStackTrace();
		}

	}

}
