package com.companyname.task.q;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Borshchev
 */
@Component
public class QDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(QDao.class);
    private static final String QUOTE_SEPARATOR = "\t";
    private static final String CHARSET = "UTF-8";

    @Value("${qs.filename.location}")
    private String fileName;

    private List<Q> qList = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        LOGGER.debug("Initialization started");
        URL url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            LOGGER.error("File not found");
            throw new IOException("File not found");
        }

        Path path = Paths.get(url.getPath());

        Charset charset = Charset.forName(CHARSET);
        try {
            List<String> lines = Files.readAllLines(path, charset);

            for (String line : lines) {
                String quote[] = line.split(QUOTE_SEPARATOR);
                String author = quote[0] == null ? null : quote[0].trim();
                String text = quote[1] == null ? null : quote[1].trim();
                Q q = new Q(author, text);
                qList.add(q);
            }
        } catch (IOException e) {
            LOGGER.error("Can't read from file", e);
            throw e;
        }
        LOGGER.debug("Initialization successful");
    }

    public List<Q> getAll() {
        return qList;
    }
}