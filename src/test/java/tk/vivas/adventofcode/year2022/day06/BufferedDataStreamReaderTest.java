package tk.vivas.adventofcode.year2022.day06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class BufferedDataStreamReaderTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/year2022/day06")
    void findStartOfPacketMarker(String input, int expected) {
        BufferedDataStreamReader dataStreamReader = new BufferedDataStreamReader(input);

        assertThat(dataStreamReader.findStartOfPacketMarker()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/year2022/day06")
    void findStartOfMessageMarker(String input, int expected) {
        BufferedDataStreamReader dataStreamReader = new BufferedDataStreamReader(input);

        assertThat(dataStreamReader.findStartOfMessageMarker()).isEqualTo(expected);
    }
}