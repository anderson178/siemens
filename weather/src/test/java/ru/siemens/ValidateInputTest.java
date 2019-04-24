package ru.siemens;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    private static final int MIN_W = 0;
    private static final int MAX_W = 90;
    private static final int MIN_L = 0;
    private static final int MAX_L = 180;
    private static final int MIN_T = -40;
    private static final int MAX_T = 40;

    @Test
    public void whenInputWidthValidateIsFalse() {
        assertThat(ValidateInput.checkWL(MIN_W, MAX_W, "1160.11212897"), is(false));
    }

    @Test
    public void whenInputWidthExistSymbolValidateIsFalse() {
        assertThat(ValidateInput.checkWL(MIN_W, MAX_W, "6P.11212897"), is(false));
    }

    @Test
    public void whenInputWidthValidateIsTrue() {
        assertThat(ValidateInput.checkWL(MIN_W, MAX_W, "60.11212897"), is(true));
    }

    @Test
    public void whenInputLongitudeValidateIsFalse() {
        assertThat(ValidateInput.checkWL(MIN_L, MAX_L, "181.11212897"), is(false));
    }

    @Test
    public void whenInputLongitudeExistSymbolValidateIsFalse() {
        assertThat(ValidateInput.checkWL(MIN_L, MAX_L, "181.1P212897"), is(false));
    }


    @Test
    public void whenInputLongitudeValidateIsTrue() {
        assertThat(ValidateInput.checkWL(MIN_L, MAX_L, "170.11212897"), is(true));
    }

    @Test
    public void whenInputTemperatureValidateIsTrue() {
        assertThat(ValidateInput.checkTemperature(MIN_T, MAX_T, "-10"), is(true));
    }

    @Test
    public void whenInputTemperatureValidateIsFalse() {
        assertThat(ValidateInput.checkTemperature(MIN_T, MAX_T, "50"), is(false));
    }


    @Test
    public void whenInputTemperatureExistSymbolValidateIsFalse() {
        assertThat(ValidateInput.checkTemperature(MIN_T, MAX_T, "1V"), is(false));
    }

}
