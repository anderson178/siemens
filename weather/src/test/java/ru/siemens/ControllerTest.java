package ru.siemens;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private Controller controller;

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void whenGetMapping() {
        List<String> list = new ArrayList<>();
        list.add("66.12302121 36.3245764 3 2019-04-23 15:52:31");
        List<String> tt = controller.getStr();
        when(controller.getStr()).thenReturn(list);
    }


}
