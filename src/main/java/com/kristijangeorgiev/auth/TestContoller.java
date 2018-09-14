package com.kristijangeorgiev.auth;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestContoller {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@GetMapping("/searcher/")
	public String hello() {
		SearchQuery sq = new NativeSearchQueryBuilder().withIndices("cdftrace").withQuery(QueryBuilders.queryStringQuery("error")).build();		
		System.out.println("query ES:" + elasticsearchTemplate.count(sq));
		return "searcher";
	}

	@GetMapping("/index-configger/")
	public String hello2() {	
		return "index-configger";
	}
	
	@GetMapping("/searcher/hello")
	public String hello3() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
