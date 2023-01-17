package dev.jeremidigitallab.examplespringthymeleafalpine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataWithPage<T> {

    private Integer currentPage;
    private Integer nextPage;
    private Integer previousPage;
    private Integer totalPage;
    private T data;

}
