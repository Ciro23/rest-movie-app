package it.tino.restmovieapp;

import kotlin.Pair;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CollectionsUtility {

    /**
     * Filters the results of a SELECT query using a range for the
     * specified column.
     * @param column Where to apply the range.
     * @param range A pair with the first element as the start of range and
     *              the second one as the end. The range is inclusive. If one of
     *              the two is null, the range is treated as unbounded on that side.
     * @param onSelect A callback to execute the calculated {@link SelectDSLCompleter}.
     * @param <T> The type of both the range values and the column.
     * @param <E> The type of the elements which should be filtered.
     * @return An empty optional if both range values are null, otherwise
     * the filtered elements.
     */
    public static <T, E> Optional<List<E>> filterByRange(
        SqlColumn<T> column,
        Pair<T, T> range,
        Function<SelectDSLCompleter, List<E>> onSelect
    ) {
        T start = range.getFirst();
        T end = range.getSecond();

        if (start == null && end == null) {
            return Optional.empty();
        }

        SelectDSLCompleter sql;
        if (start != null && end != null) {
            sql = c -> c.where(
                    column,
                    SqlBuilder.isBetween(start).and(end)
            );
        } else if (start != null) {
            sql = c -> c.where(
                    column,
                    SqlBuilder.isGreaterThanOrEqualTo(start)
            );
        } else {
            sql = c -> c.where(
                    column,
                    SqlBuilder.isLessThanOrEqualTo(end)
            );
        }

        return Optional.of(onSelect.apply(sql));
    }

    /**
     * Filter by a range of two dates stored as string. The conversion
     * to {@link Date} is executed automatically using the format yyyy-MM-dd.
     * @see #filterByRange(SqlColumn, Pair, Function)
     */
    public static <E> Optional<List<E>> filterByStringDateRange(
        SqlColumn<Date> column,
        Pair<String, String> range,
        Function<SelectDSLCompleter, List<E>> onSelect
    ) throws ParseException {
        String start = range.getFirst();
        String end = range.getSecond();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDateStart = null;
        if (start != null) {
            convertedDateStart = dateFormat.parse(start);
        }

        Date convertedDateEnd = null;
        if (end != null) {
            convertedDateEnd = dateFormat.parse(end);
        }

        Pair<Date, Date> convertedRange = new Pair<>(convertedDateStart, convertedDateEnd);
        return filterByRange(column, convertedRange, onSelect);
    }

    public static <E> void addOrRetain(Collection<E> collection, Collection<E> elementsToAddOrRetain) {
        if (collection.isEmpty()) {
            collection.addAll(elementsToAddOrRetain);
        } else {
            collection.retainAll(elementsToAddOrRetain);
        }
    }
}
