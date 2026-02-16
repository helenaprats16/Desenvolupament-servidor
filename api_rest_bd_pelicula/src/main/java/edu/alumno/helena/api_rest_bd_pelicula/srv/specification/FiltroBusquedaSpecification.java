package edu.alumno.helena.api_rest_bd_pelicula.srv.specification;

import org.springframework.data.jpa.domain.Specification;

import edu.alumno.helena.api_rest_bd_pelicula.helper.FiltroBusqueda;
import jakarta.persistence.criteria.*;

import java.util.List;

public class FiltroBusquedaSpecification<T> implements Specification<T> {

    private final List<FiltroBusqueda> filtros;

    public FiltroBusquedaSpecification(List<FiltroBusqueda> filtros) {
        this.filtros = filtros;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filtros == null || filtros.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        Predicate predicateFinal = criteriaBuilder.conjunction();

        for (FiltroBusqueda filtro : filtros) {
            if (filtro == null || !filtro.esValido()) {
                continue;
            }

            String campo = filtro.getCampo();
            String operador = filtro.getOperador().toLowerCase();
            String valor = filtro.getValor();

            Path<String> campoPath = root.get(campo);
            Predicate predicado;

            switch (operador) {
                case "eq":
                    predicado = criteriaBuilder.equal(campoPath, valor);
                    break;
                case "ne":
                    predicado = criteriaBuilder.notEqual(campoPath, valor);
                    break;
                case "contiene":
                case "like":
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            "%" + valor.toLowerCase() + "%");
                    break;
                case "empieza":
                case "startswith":
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            valor.toLowerCase() + "%");
                    break;
                case "termina":
                case "endswith":
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            "%" + valor.toLowerCase());
                    break;
                case "gt":
                    predicado = criteriaBuilder.greaterThan(campoPath, valor);
                    break;
                case "lt":
                    predicado = criteriaBuilder.lessThan(campoPath, valor);
                    break;
                case "gte":
                    predicado = criteriaBuilder.greaterThanOrEqualTo(campoPath, valor);
                    break;
                case "lte":
                    predicado = criteriaBuilder.lessThanOrEqualTo(campoPath, valor);
                    break;
                default:
                    throw new IllegalArgumentException("Operador no válido: " + operador);
            }

            predicateFinal = criteriaBuilder.and(predicateFinal, predicado);
        }

        return predicateFinal;
    }
}
