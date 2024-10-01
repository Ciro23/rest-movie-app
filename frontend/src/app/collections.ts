/**
 * Null or undefined strings are considered greater than anything else,
 * so they're placed at the end of a sorted list.
 */
export function compareNullableStrings(a?: string | null, b?: string | null) {
  if (a === null || a === undefined) return 1;
  if (b === null || b === undefined) return -1;

  return a.toString().localeCompare(b.toString());
}

export function ensureArray(items?: any[]) {
  if (!items || items.length == 0) {
    return [];
  }

  return items;
}
