package com.uoctfm.principal.controller;

import com.uoctfm.principal.flow.MainFlow;
import com.uoctfm.principal.flow.SystemFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExecutionControllerImpl implements ExecutionController {

    @Autowired
    MainFlow mainFlow;

    @Autowired
    SystemFlow systemFlow;

    private final Logger logger = LoggerFactory.getLogger(ExecutionControllerImpl.class);

    @GetMapping("/execution/")
    public void doExecution() {
        long startTime = System.currentTimeMillis();
        logger.info("Starting Main process by the execution end-point");
        mainFlow.execute();
        logger.info("Ending Main process by the execution end-point with duration {}", System.currentTimeMillis() - startTime);
    }

    @GetMapping("/execution/{id}")
    public void doExecutionById(@PathVariable("id") Integer id) {
        long startTime = System.currentTimeMillis();
        logger.info("Starting Main process by the execution end-point");
        systemFlow.executeById(id);
        logger.info("Ending Main process by the execution end-point with duration {}", System.currentTimeMillis() - startTime);
    }

}