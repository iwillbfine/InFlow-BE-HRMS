package com.pado.inflow.vacation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDTO<T> {

    @JsonProperty("elements")
    private List<T> elements; // 요소

    @JsonProperty("page_no")
    private Integer pageNo; // 현재 페이지 번호

    @JsonProperty("page_size")
    private Integer pageSize; // 보여줄 페이지 간격 ex) 1~10, 11~20

    @JsonProperty("elements_per_page")
    private Integer elementsPerPage; // 한 페이지에 보여줄 요소 개수

    @JsonProperty("total")
    private Integer total; // 전체 요소 개수

    @JsonProperty("start_page")
    private Integer startPage; // 시작 페이지 숫자

    @JsonProperty("end_page")
    private Integer endPage; // 끝 페이지 숫자

    @JsonProperty("prev")
    private Boolean prev; // start_page 이전 숫자 존재 여부

    @JsonProperty("next")
    private Boolean next; // end_page 다음 숫자 존재 여부

    public PageDTO(List<T> elements,
                   Integer pageNo,
                   Integer pageSize,
                   Integer elementsPerPage,
                   Integer total){
        this.elements = elements;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.elementsPerPage = elementsPerPage;
        this.total = total;

        this.endPage = (int)(Math.ceil((double)pageNo / pageSize)) * pageSize;

        this.startPage = this.endPage - pageSize + 1;

        int realEnd = (int)(Math.ceil((double)total / elementsPerPage));

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;

        this.next = this.endPage < realEnd;
    }

}
