package com.yeditepe.newscollector.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaTypes.HAL_JSON_VALUE)
public class NewsController {

}
