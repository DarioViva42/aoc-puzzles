package tk.vivas.adventofcode.year2023.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RangeMapFlatteningUtilsTest {


    /*
     0123456789ABCDEF
      └─┐  └─┐
          └─┐   └─┐
    */
    @Test
    void flatten_firstScenario() {
        RangeMapEntry aEntry = new RangeMapEntry(3, 1, 6);
        RangeMap a = new RangeMap(List.of(aEntry));
        RangeMapEntry bEntry = new RangeMapEntry(8, 5, 7);
        RangeMap b = new RangeMap(List.of(bEntry));

        RangeMap flattened = RangeMapFlatteningUtils.flatten(a, b);

        List<RangeMapEntry> mapEntries = flattened.getMapEntries();

        assertThat(mapEntries).hasSize(2);

        assertThat(mapEntries.getFirst()).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(1);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(3);
            assertThat(entry.getRangeLength()).isEqualTo(2);
        });
        assertThat(mapEntries.getLast()).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(3);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(8);
            assertThat(entry.getRangeLength()).isEqualTo(4);
        });
    }

    /*
     0123456789ABCDEF
       └───┐   └───┐
        └┐       └┐
    */
    @Test
    void flatten_secondScenario() {
        RangeMapEntry aEntry = new RangeMapEntry(6, 2, 9);
        RangeMap a = new RangeMap(List.of(aEntry));
        RangeMapEntry bEntry = new RangeMapEntry(4, 3, 10);
        RangeMap b = new RangeMap(List.of(bEntry));

        RangeMap flattened = RangeMapFlatteningUtils.flatten(a, b);

        List<RangeMapEntry> mapEntries = flattened.getMapEntries();

        assertThat(mapEntries).hasSize(2);

        assertThat(mapEntries.getFirst()).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(2);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(7);
            assertThat(entry.getRangeLength()).isEqualTo(7);
        });
        assertThat(mapEntries.getLast()).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(9);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(13);
            assertThat(entry.getRangeLength()).isEqualTo(2);
        });
    }

    /*
     0123456789ABCDEF
          └─┐ └─┐
     ┌─────┘  ┌─────┘
    */
    @Test
    void flatten_thirdScenario() {
        RangeMapEntry aEntry = new RangeMapEntry(7, 5, 5);
        RangeMap a = new RangeMap(List.of(aEntry));
        RangeMapEntry bEntry = new RangeMapEntry(0, 6, 10);
        RangeMap b = new RangeMap(List.of(bEntry));

        RangeMap flattened = RangeMapFlatteningUtils.flatten(a, b);

        List<RangeMapEntry> mapEntries = flattened.getMapEntries();

        assertThat(mapEntries).hasSize(1);

        assertThat(mapEntries.getFirst()).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(5);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(1);
            assertThat(entry.getRangeLength()).isEqualTo(5);
        });
    }

    /*
     0123456789ABCDEF
       ┌────┘  ┌────┘
        └──┐  └──┐
    */
    @Test
    void flatten_fourthScenario() {
        RangeMapEntry aEntry = new RangeMapEntry(2, 7, 9);
        RangeMap a = new RangeMap(List.of(aEntry));
        RangeMapEntry bEntry = new RangeMapEntry(6, 3, 7);
        RangeMap b = new RangeMap(List.of(bEntry));

        RangeMap flattened = RangeMapFlatteningUtils.flatten(a, b);

        List<RangeMapEntry> mapEntries = flattened.getMapEntries();

        assertThat(mapEntries).hasSize(3);

        assertThat(mapEntries.get(0)).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(7);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(2);
            assertThat(entry.getRangeLength()).isEqualTo(1);
        });
        assertThat(mapEntries.get(1)).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(8);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(6);
            assertThat(entry.getRangeLength()).isEqualTo(7);
        });
        assertThat(mapEntries.get(2)).satisfies(entry -> {
            assertThat(entry.getSourceRangeStart()).isEqualTo(15);
            assertThat(entry.getDestinationRangeStart()).isEqualTo(10);
            assertThat(entry.getRangeLength()).isEqualTo(1);
        });
    }
}