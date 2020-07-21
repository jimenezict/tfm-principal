package com.uoctfm.principal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ExecutionController {

    @GetMapping("/execution/")
    public void doExecution();

    @GetMapping("/execution/{id}/")
    public void doExecutionById(@PathVariable("id") Integer id);

}