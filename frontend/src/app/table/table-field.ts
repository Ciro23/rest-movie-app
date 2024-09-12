export interface TableField {
  /**
   * The value used in the column header.
   */
  name: string,

  /**
   * The original value used during the sorting. If
   * {@link getFormattedValue} is not defined, this will also
   * be used to display the value in the table.
   */
  getRawValue: (row: any) => any,

  /**
   * If defined, this will be used to display the value in
   * the table. It's useful, for example, to format amounts
   * of money without affecting the sorting.
   * @param value In most cases it's the raw value obtained using
   * {@link getRawValue}, but other values are also valid for exceptional
   * cases.
   */
  getFormattedValue? : (rawValue: any) => any
}
