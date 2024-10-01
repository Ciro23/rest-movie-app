package it.tino.restmovieapp;

import java.util.List;

public record PaginatedResponse<T>(List<T> data, long totalCount) {
}
