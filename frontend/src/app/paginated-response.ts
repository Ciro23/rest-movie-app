/**
 * To work with backend pagination.
 */
export interface PaginatedResponse<T> {
  data: T[];
  totalCount: number;
}
