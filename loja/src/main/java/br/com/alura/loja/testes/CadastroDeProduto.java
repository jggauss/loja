package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
	
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()+p.getDescricao()));
		
		List<Produto> porNome = produtoDao.buscarPorNome("Xiamo Redmi");
		todos.forEach(p2 -> System.out.println("Busca por nome "+p.getNome()+p.getDescricao()));
		
		List<Produto> porNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println("Busca por nome da categoria "+p.getNome()+p.getDescricao()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiamo Redmi");
		System.out.println("Preço do produto "+precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("Celulares");
		
		Produto celular = new Produto("Xiamo Redmi","Muito Legal",new BigDecimal("800"),celulares);
		
		
		EntityManager em = JPAUtil.getEntityManager();		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
