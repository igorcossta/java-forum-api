package io.igorcossta.util;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageableResponse {
    private Map<String, Object> response = new HashMap<>();

    public PageableResponse put(String key, Object value) {
        response.put(key, value);
        return this;
    }

    public PageableResponse build(Page<?> page) {
        put("currentPage", page.getNumber())
                .put("totalPages", page.getTotalPages())
                .put("totalItems", page.getTotalElements());
        return this;
    }
}
