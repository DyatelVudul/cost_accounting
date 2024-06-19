import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongHolderTest {
    @Test
    public void testHolder() {
        LongHolder holder = new LongHolder(1000);

        assertEquals(1000, holder.getValue());

        holder.setValue(2000);

        assertEquals(2000, holder.getValue());
    }
}
