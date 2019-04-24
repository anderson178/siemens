package ru.siemens;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInputWidthValidateIsFalse() {
        assertThat(new ValidateInput("1160.11212897=11.02145489=25").validate(), is(false));
    }

    @Test
    public void whenInputWidthExistSymbolValidateIsFalse() {
        assertThat(new ValidateInput("p0.11212897=11.02145489=25").validate(), is(false));
    }

    @Test
    public void whenInputWidthValidateIsTrue() {
        assertThat(new ValidateInput("60.11212897=11.02145489=25").validate(), is(true));
    }

    @Test
    public void whenInputLongitudeValidateIsFalse() {
        assertThat(new ValidateInput("60.11212897=243.02145489=25").validate(), is(false));
    }

    @Test
    public void whenInputLongitudeExistSymbolValidateIsFalse() {
        assertThat(new ValidateInput("10.11212897=11.0E145489=25").validate(), is(false));
    }


        @Test
    public void whenInputLongitudeValidateIsTrue() {
        assertThat(new ValidateInput("60.11212897=110.02145489=25").validate(), is(true));
    }

    @Test
    public void whenInputTemperatureValidateIsFalse() {
        assertThat(new ValidateInput("60.11212897=243.02145489=-10").validate(), is(false));
    }

    @Test
    public void whenInputTemperatureValidateIsTrue() {
        assertThat(new ValidateInput("60.11212897=110.02145489=34").validate(), is(true));
    }

    @Test
    public void whenInputTemperatureExistSymbolValidateIsFalse() {
        assertThat(new ValidateInput("40.11212897=11.02145489=2X").validate(), is(false));
    }


        @Test
    public void whenGetWidth() {
        ValidateInput validateInput = new ValidateInput("60.11212897=110.02145489=34");
        validateInput.validate();
        assertThat(validateInput.getWidth(), is(60.11212897));
    }


    @Test
    public void whenGetLongitudeTrue() {
        ValidateInput validateInput = new ValidateInput("60.11212897=110.02145489=34");
        validateInput.validate();
        assertThat(validateInput.getLongitude(), is(110.02145489));
    }

    @Test
    public void whenGetTemperature() {
        ValidateInput validateInput = new ValidateInput("60.11212897=110.02145489=34");
        validateInput.validate();
        assertThat(validateInput.getTemperature(), is(34));
    }
}
