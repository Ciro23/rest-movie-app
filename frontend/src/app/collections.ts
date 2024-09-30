export function compareNullableStrings(a?: string | null, b?: string | null) {
  if (a === null || a === undefined) return 1;
  if (b === null || b === undefined) return -1;

  return a.toString().localeCompare(b.toString());
}
