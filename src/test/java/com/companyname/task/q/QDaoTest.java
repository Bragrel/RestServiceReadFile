package com.companyname.task.q;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Igor Borshchev
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
public class QDaoTest {

    @Autowired
    private QDao qDao;

    @Test
    public void testGetAll() {
        List<Q> qList = qDao.getAll();
        assertNotNull(qList);
        assertTrue(!qList.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testFileNotFound() throws IOException {
        QDao qDao = new QDao();
        ReflectionTestUtils.setField(qDao, "fileName", "wrong.txt");
        qDao.init();
    }

    @TestConfiguration
    public static class TestContextConfiguration {

        @Bean
        public QDao employeeService() {
            return new QDao();
        }
    }
}