package nycu.lab3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Queue;
import java.util.PriorityQueue; 
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

class PriorityQueueTest {
	private Queue queue;

	@BeforeEach
	void setup() {
		this.queue = new PriorityQueue<>();
	}

	@AfterEach
	void tearDown() {
		this.queue = null;
	}

	@ParameterizedTest(name="#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
	void testPriorityQueue(int[] input, int[] ans) {
        for(int num : input) {
            this.queue.offer(num);
        }

        for(int i = 0; i < this.queue.size(); i++) {
            assertEquals(this.queue.poll(), ans[i]);
        }
		
	}

    @Test
    void testException1() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            this.queue.add(null);
        });
    }

    @Test
    void testException2() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            this.queue.offer(null);
        });
    }

    @Test
    void testException3() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            Queue queue_ = new PriorityQueue<>(-1);
        });
    }

	private static Stream<Arguments> streamProvider() {
        return Stream.of(
            Arguments.of(new int[]{1,3,2,4,5}, new int[]{5,2,3,4,1}),
            Arguments.of(new int[]{1,10,5,4,5}, new int[]{1,4,5,5,10}),
            Arguments.of(new int[]{-10,-20,5,5,5}, new int[]{-20,-10,5,5,5}),
            Arguments.of(new int[]{1,1,1,1,2}, new int[]{1,1,1,1,2}),
            Arguments.of(new int[]{5,4,3,2,1}, new int[]{1,2,3,4,5})
        );
    }
}
