package com.ttulka.ecommerce.warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AmountDiffblueTest {
    /**
     * Method under test: {@link Amount#add(Amount)}
     */
    @Test
    void testAdd() {
        // Arrange
        Amount amount = new Amount(10);

        // Act and Assert
        assertEquals(20, amount.add(new Amount(10)).value());
    }

    /**
     * Method under test: {@link Amount#subtract(Amount)}
     */
    @Test
    void testSubtract() {
        // Arrange
        Amount amount = new Amount(10);

        // Act and Assert
        assertEquals(0, amount.subtract(new Amount(10)).value());
    }

    /**
     * Method under test: {@link Amount#subtract(Amount)}
     */
    @Test
    void testSubtract2() {
        // Arrange
        Amount amount = new Amount(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> amount.subtract(new Amount(10)));
    }

    /**
     * Method under test: {@link Amount#compareTo(Amount)}
     */
    @Test
    void testCompareTo() {
        // Arrange
        Amount amount = new Amount(10);

        // Act and Assert
        assertEquals(0, amount.compareTo(new Amount(10)));
    }

    /**
     * Method under test: {@link Amount#compareTo(Amount)}
     */
    @Test
    void testCompareTo2() {
        // Arrange
        Amount amount = new Amount(1);

        // Act and Assert
        assertEquals(-1, amount.compareTo(new Amount(10)));
    }

    /**
     * Method under test: {@link Amount#compareTo(Amount)}
     */
    @Test
    void testCompareTo3() {
        // Arrange
        Amount amount = new Amount(10);

        // Act and Assert
        assertEquals(1, amount.compareTo(new Amount(1)));
    }

    /**
     * Method under test: {@link Amount#Amount(int)}
     */
    @Test
    void testNewAmount() {
        // Arrange, Act and Assert
        assertEquals(10, (new Amount(10)).value());
        assertThrows(IllegalArgumentException.class, () -> new Amount(-1));
    }
}
