package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    @Query("select s from Serie s WHERE s.titulo ILIKE %:nomeSerie%")
    Optional<Serie> buscaPorTitulo(String nomeSerie);

    @Query("select s from Serie s WHERE s.atores ILIKE %:nomeAtor% AND s.avaliacao >= :avaliacao")
    List<Serie> buscaPorAtorEAvaliacao(String nomeAtor, Double avaliacao);

    @Query("select s from Serie s ORDER BY s.avaliacao DESC LIMIT 5")
    List<Serie> buscarTopSeries();

    @Query("select s from Serie s WHERE s.genero = :categoria")
    List<Serie> buscaPorGenero(Categoria categoria);

    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, Double avaliacao);

    @Query("select e from Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("select e from Serie s JOIN s.episodios e WHERE s.titulo ILIKE %:serie% ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> buscarTopEpisodiosPorSerie(String serie);

    @Query("select e from Serie s JOIN s.episodios e WHERE s.titulo ILIKE %:serie% AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> buscarEpisodiosPorSerieEAno(String serie, int anoLancamento);
}
