package com.santander.banco811.specification;

import com.santander.banco811.criteria.SearchCriteria;
import com.santander.banco811.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioSpecificationBuilder {

    private final List<SearchCriteria> params;

    public UsuarioSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public UsuarioSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Usuario> build() {
        if (params.size() == 0) return null;

        var specs = params.stream()
                .map(UsuarioSpecification::new)
                .collect(Collectors.toList());

        Specification<Usuario> result = specs.stream().findFirst().orElseThrow();

        for (int i = 0; i < params.size(); i++) {
            result = Specification.where(result.and(specs.get(i)));
        }
        return result;
    }
}
