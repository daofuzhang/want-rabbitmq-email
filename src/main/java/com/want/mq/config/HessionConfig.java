package com.want.mq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Repository;

import com.want.mq.biz.EmailHessianService;
import com.want.mq.biz.MongoHessianService;
import com.want.mq.email.service.IHessianEmailService;
import com.want.mq.email.service.IHessianMongoService;

@Repository
public class HessionConfig {

	//old version   begin
	@Autowired
	private IHessianEmailService hessianEmailService ;
	@Autowired
	private IHessianMongoService hessianMongoService;
	
	//发布服务
    @Bean(name = "/hessianEmailService")
    public HessianServiceExporter emailService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianEmailService);
        exporter.setServiceInterface(IHessianEmailService.class);
        return exporter;
    }
    
    //发布服务
    @Bean(name = "/hessianMongoService")
    public HessianServiceExporter mongoService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianMongoService);
        exporter.setServiceInterface(IHessianMongoService.class);
        return exporter;
    }
   //old version   end
    
    @Autowired
	private EmailHessianService emailHessianService ;
	@Autowired
	private MongoHessianService mongoHessianService; 
	
	//发布服务
    @Bean(name = "/emailHessianService")
    public HessianServiceExporter hEmailService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(emailHessianService);
        exporter.setServiceInterface(EmailHessianService.class);
        return exporter;
    }
    
    //发布服务
    @Bean(name = "/mongoHessianService")
    public HessianServiceExporter hMongoService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(mongoHessianService);
        exporter.setServiceInterface(MongoHessianService.class);
        return exporter;
    }
    
    
    
	
}
