package example.server.payload.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPageable {
    private Integer totalPages;
    private Long totalElements;
    private int page;
    private int size;
    private Object object;
}
