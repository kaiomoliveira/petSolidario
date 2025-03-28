package com.petSolidario.services;

import com.petSolidario.domains.*;
import com.petSolidario.domains.enums.Porte;
import com.petSolidario.domains.enums.SituacaoInstituicao;
import com.petSolidario.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private AnimalRepository animalRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private AdocaoRepository adocaoRepo;

    @Autowired
    private VeiculoRepository veiculoRepo;

    public void initDB(){
        Usuario usuario1 = new Usuario(null, "usuario1@gmail.com","senhauser1","usuario1","Masculino","123.456.789-09", LocalDate.of(2000,11,25));
        Usuario usuario2 = new Usuario(null, "usuario2@gmail.com","senhauser2","usuario2","Feminino","987.654.321-00", LocalDate.of(2004,5,1));

        usuarioRepo.save(usuario1);
        usuarioRepo.save(usuario2);

        Instituicao instituicao1 = new Instituicao(null, "instituicao01@gmail.com", "111111","12.345.678/0001-95","01-Instiuição","Primeira-Instiuição",usuario1, SituacaoInstituicao.APROVADA);
        Instituicao instituicao2 = new Instituicao(null, "instituicao02@gmail.com", "222222","98.765.432/0001-00","02-Instiuição","Segunda-Instiuição",usuario2, SituacaoInstituicao.EMAPROVACAO);

        instituicaoRepo.save(instituicao1);
        instituicaoRepo.save(instituicao2);

        Animal animal1 = new Animal(null,"A1","Doguinho","Cachorro","Cão de Rua", "Preto","Macho",null,instituicao1,usuario1, Porte.MEDIO);
        Animal animal2 = new Animal(null,"A2","Catzinho","Gato","Gato de Siamez", "Branco","Femea",null,instituicao2,usuario2, Porte.PEQUENO);

        animalRepo.save(animal1);
        animalRepo.save(animal2);

        Produto produto1 = new Produto(null,LocalDate.now(),"Ração para Gato 10Kg", new BigDecimal("10.1"),"CB001",instituicao1,usuario1);
        Produto produto2 = new Produto(null,LocalDate.now(),"Ração para Dogs 5Kg", new BigDecimal("5.1"),"CB002",instituicao2,usuario2);

        produtoRepo.save(produto1);
        produtoRepo.save(produto2);

        Adocao adocao1 = new Adocao(null,"Adoção do "+animal1+" por: "+usuario1,"Sendo doado pela instituição: "+ instituicao1,usuario1,instituicao1,animal1);

        adocaoRepo.save(adocao1);

        Adocao adocao2 = new Adocao(null,"Adoção do "+animal2+" por: "+usuario2,"Sendo doado pela instituição: "+ instituicao2,usuario2,instituicao2,animal2);

        adocaoRepo.save(adocao2);

        Veiculo veiculo1 = new Veiculo(null,"caro vermelho", LocalDate.now(),10.00, "Victor", "34357890821");

        veiculoRepo.save(veiculo1);
    }
}

