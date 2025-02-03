package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6f3500e9";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");
        String nomeSerie = leitura.nextLine();

        String json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

//        json = consumo.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6f3500e9");
//        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
//        System.out.println(dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
//        temporadas.forEach(System.out::println);

//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

//        System.out.println("\nTop 10 episodios");
//        dadosEpisodios.stream()
//                .filter(d -> !d.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(d -> System.out.println("Primeiro filtro: N/A " + d))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(d -> System.out.println("Ordenacao " + d))
//                .limit(10)
//                .peek(d -> System.out.println("Limitador " + d))
//                .map(d -> d.titulo().toUpperCase())
//                .peek(d -> System.out.println("Mapeamento " + d))
//                .forEach(System.out::println);

        List<Episodio> episodio = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodio.forEach(System.out::println);

//        System.out.println("Digite um trecho do episodio desejado: ");
//        String trechoTitulo = leitura.nextLine();
//
//        Optional<Episodio> episodioBuscado = episodio.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episoodio encontrado!");
//            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//        } else {
//            System.out.println("Episodio nao encontrado");
//        }
//
//        System.out.println("A partir de que ano voce deseja ver os episodios?");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodio.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episodio: " + e.getTitulo() +
//                                " Data Lancamento: " + e.getDataLancamento().format(formatter)
//                ));

        Map<Integer, Double> avaliacoesPorTemporada = episodio.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(
                        Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)
                ));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodio.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Maior avaliacao: " + est.getMax());
        System.out.println("Menor avaliacao: " + est.getMin());
        System.out.println("Media de avaliacoes: " + est.getAverage());
        System.out.println("Soma das avaliacoes: " + est.getSum());
        System.out.println("Quantidade de avaliacoes: " + est.getCount());

    }
}
