package io.github.cursodsousa.produtosapi.controller;

import io.github.cursodsousa.produtosapi.model.Produto;
import io.github.cursodsousa.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")

public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @PostMapping
    public Produto salvar(@RequestBody Produto produto){

        var id = UUID.randomUUID().toString();
        produto.setId(id);
        System.out.println("Produto recebido: " + produto);
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/todos") // Mapeia a rota /produtos
    /*
    O List<Produto>
Tipo de Retorno: A parte List<Produto> especifica que o méctodo vai retornar uma lista (List) de objetos do tipo Produto.
List: List é uma interface do Java que representa uma coleção ordenada de elementos, onde cada elemento tem uma posição específica. Em Spring Data JPA,
o méctodo findAll() do repositório retorna uma List, então utilizamos o mesmo tipo no mé t odo para assegurar compatibilidade. 
    * */
    public List<Produto> obterTodosProdutos() {

        return produtoRepository.findAll(); // Retorna todos os produtos
    }


    @GetMapping("{id}")
    public Produto obterProdutoPorId(@PathVariable("id") String id) {
        /*
        A anotação @PathVariable é utilizada em métodos de controladores em aplicações Spring (ou Spring Boot)
        para extrair valores de variáveis de caminho (path variables) na URL de uma requisição HTTP. Recebendo
        o parâmetro diretamente da URL */

        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id,
                          @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}











