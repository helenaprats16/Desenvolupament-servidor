package edu.alumno.helena.api_rest_bd_pelicula.srv.specification;

import org.springframework.data.jpa.domain.Specification;

import edu.alumno.helena.api_rest_bd_pelicula.security.FiltroBusqueda;
import edu.alumno.helena.api_rest_bd_pelicula.security.Operacion;
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
            Operacion operador = filtro.getOperador();
            String valor = filtro.getValor();

            Path<String> campoPath = root.get(campo);
            Predicate predicado;

            switch (operador) {
                case EQ:
                    predicado = criteriaBuilder.equal(campoPath, valor);
                    break;
                case NE:
                    predicado = criteriaBuilder.notEqual(campoPath, valor);
                    break;
                case CONTIENE:
                case LIKE:
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            "%" + valor.toLowerCase() + "%");
                    break;
                case EMPIEZA:
                case STARTSWITH:
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            valor.toLowerCase() + "%");
                    break;
                case TERMINA:
                case ENDSWITH:
                    predicado = criteriaBuilder.like(criteriaBuilder.lower(campoPath),
                            "%" + valor.toLowerCase());
                    break;
                case GT:
                    predicado = criteriaBuilder.greaterThan(campoPath, valor);
                    break;
                case LT:
                    predicado = criteriaBuilder.lessThan(campoPath, valor);
                    break;
                case GTE:
                    predicado = criteriaBuilder.greaterThanOrEqualTo(campoPath, valor);
                    break;
                case LTE:
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
