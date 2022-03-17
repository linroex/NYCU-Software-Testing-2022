package nycu.lab2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {

    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player("notorious", -10);
    }

    @AfterEach
    void tearDown() {
		this.player = null;
    }

    @Test
    void test_a() throws InterruptedException {
        for (int hour = 0; hour < 12; hour++) {
            StrangeGameStub gameStub = new StrangeGameStub(hour);
            assertEquals("The game is not yet open!", gameStub.enterGame(this.player));
        }
    }

    @Test
    void test_b() throws InterruptedException {
        for (int hour = 12; hour < 24; hour++) {
            StrangeGameStub gameStub = new StrangeGameStub(hour);
            assertEquals("After a long period of punishment, the player can leave! :)", gameStub.enterGame(this.player));
        }
    }

    @Test
    void test_c() throws InterruptedException {
        StrangeGameStub game = new StrangeGameStub(20);

		for (int i = 0; i < 3; i++) {
            String pid = String.valueOf(i);
            Player player = new Player(pid, -1);

            game.prison.crime(player);

			assertEquals(pid, game.prison.getLog().get(i));
			assertEquals(i+1, game.prison.getLog().size());
        }
        
    }

    @Test
    void test_d() throws InterruptedException {
        StrangeGame game = new StrangeGame();
        
        game.db = mock(GAMEDb.class);
        when(game.db.getScore(this.player.getPlayerId())).thenReturn(6666);

        assertEquals(6666, game.getScore(this.player.getPlayerId()));
    }

    @Test
    void test_e() throws InterruptedException {
        StrangeGame game = new StrangeGame();
        assertEquals("Thank you", game.donate(new PaypalServiceFake()));
    }
}

class StrangeGameStub extends StrangeGame
{
    StrangeGameStub(int hour) throws InterruptedException {
        this.hour = mock(Hour.class);
        lenient().when(this.hour.getHour()).thenReturn(hour);

        Prison prison = new Prison();
        this.prison = spy(prison);
        
		lenient().doNothing().when(this.prison).imprisonment(any());
        
    }
}

class PaypalServiceFake implements paypalService
{
    @Override
    public String doDonate() {
        return "Success";
    }
}