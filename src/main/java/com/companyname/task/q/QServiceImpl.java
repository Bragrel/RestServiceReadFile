package com.companyname.task.q;

import com.companyname.task.Urls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Igor Borshchev
 */
@RestController
@RequestMapping(Urls.VERSION)
public class QServiceImpl implements QService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QServiceImpl.class);
    private final QDao qDao;

    @Autowired
    public QServiceImpl(QDao qDao) {
        this.qDao = qDao;
    }

    @Override
    @RequestMapping(value = Urls.GET_ALL_QS, method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Q> getAll() {
        LOGGER.info("getAll request received");
        List<Q> qList = qDao.getAll();
        LOGGER.info("getAll request successful. qList count: {}", qList.size());
        return qList;
    }
}