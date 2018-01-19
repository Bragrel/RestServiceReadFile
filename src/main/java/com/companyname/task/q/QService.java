package com.companyname.task.q;

import java.util.List;

/**
 * @author Igor Borshchev
 */
public interface QService {

    /**
     * Retrieve all qs
      * @return qs list
     */
    List<Q> getAll();
}