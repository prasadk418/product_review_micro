package com.product.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;


@Component
public class ProductUtil {
		
	@Autowired
	private EurekaClient client;
	
	
	
	public String buildUrl(String serviceName){
		/*System.out.println(loadProperties.getProtocol() + "://"
				+ loadProperties.getReviewservicename());
		*/
		InstanceInfo instance=client.getNextServerFromEureka(serviceName, false);
		System.out.println(instance.getHomePageUrl());
		return instance.getHomePageUrl() ;
	}

	public static <T> boolean isNotPresent(Optional<T> optional){
		return !optional.isPresent();
	}


}
