package com.companyname.task.q;

import com.companyname.task.Urls;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Igor Borshchev
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QServiceImplTest {

    @Mock
    private QDao qDao;
    private QServiceImpl quoteService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        quoteService = new QServiceImpl(qDao);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Q> expected = new ArrayList<>();
        expected.add(new Q("A. A. Milne", "Did you ever stop to think, and forget to start again?"));
        expected.add(new Q("A. A. Milne", "Tiggers don't like honey."));
        when(qDao.getAll()).thenReturn(expected);

        List<Q> actual = quoteService.getAll();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        verify(qDao, times(1)).getAll();
    }

    @Test
    public void testWebService() throws Exception {
        MockHttpServletRequestBuilder builder = get(Urls.VERSION + Urls.GET_ALL_QS);
        mockMvc.perform(builder)
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(Matchers.greaterThan(1))));
    }
}