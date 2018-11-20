import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.parser.nbp.models.services.InputService;

public class InputServiceTest {

    private InputService inputService;

    @BeforeEach

    public void createInputService(){
        inputService = new InputService();
    }

    @Test
    public void checkFormatOfDateTest() {
        Assertions.assertEquals(true, inputService.checkFormatOfDate("2012-01-20", "2015-05-25"));
    }




}
