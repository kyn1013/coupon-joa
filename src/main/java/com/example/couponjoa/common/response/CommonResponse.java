package com.example.couponjoa.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CommonResponse<T> {
    private T data;
    private PageInfo page;

    private CommonResponse(T data) {
        this.data = data;
    }

    private CommonResponse(T data, PageInfo page) {
        this.data = data;
        this.page = page;
    }

    // Pagination이 적용안 된 response 형식
    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponse<>(data);
    }

    // Pagination이 적용된 response 형식
    public static <T> CommonResponse<T> of(T data, PageInfo page) {
        return new CommonResponse<>(data, page);
    }
}
