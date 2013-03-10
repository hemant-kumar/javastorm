package com.javastorm.tweetcollector.consumer;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import twitter4j.Status;

import com.javastorm.mongoapi.mongo.config.MongoConfiguration;
import com.javastorm.mongoapi.mongo.function.impl.MongoFunctionImpl;
import com.javastorm.mongoapi.mongo.function.spi.MongoFunction;
import com.javastorm.tweetcollector.model.MongoTweetStatus;

/**
 * The MongoTweetConsumer class is intended for pushing the consumed tweet to Mongo DB.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class MongoTweetConsumer extends TweetConsumer 
{
	private Logger logger = Logger.getLogger(MongoTweetConsumer.class.getName());
	private MongoFunction func_;
	private String mongoPropsFilePath_;
	private String pojoCollMapFilePath_;
	
	public String getMongoPropsFilePath() {
		return mongoPropsFilePath_;
	}

	public void setMongoPropsFilePath(String mongoPropsFilePath) {
		this.mongoPropsFilePath_ = mongoPropsFilePath;
	}

	public String getPojoCollMapFilePath() {
		return pojoCollMapFilePath_;
	}

	public void setPojoCollMapFilePath(String pojoCollMapFilePath) {
		this.pojoCollMapFilePath_ = pojoCollMapFilePath;
	}

	@Override
	public void consume(Status status) throws Exception {
		MongoTweetStatus mongoStatus_ = new MongoTweetStatus(status);
		logger.info(status.getText());
		func_.save(mongoStatus_);
	}

	public void init() throws Exception {
		if(StringUtils.isEmpty(mongoPropsFilePath_) || StringUtils.isEmpty(pojoCollMapFilePath_)) {
			throw new Exception("mongoPropsFilePath & pojoCollMapFilePath are mandatory properties");
		}
		FileInputStream mfis_ = null;
		FileInputStream pcmfis_ = null;
		if(mongoPropsFilePath_.toLowerCase().trim().startsWith("classpath:")) {
			String path_ = mongoPropsFilePath_.trim().substring(10);
			File file_ = new File(MongoTweetConsumer.class.getClassLoader().getResource(path_).getFile());
			if(!file_.exists()) {
				throw new Exception("Invalid mongoPropsFilePath provided");
			}
			mfis_ = new FileInputStream(file_);
		}
		else {
			File file_ = new File(mongoPropsFilePath_);
			if(!file_.exists()) {
				throw new Exception("Invalid mongoPropsFilePath provided");
			}
			mfis_ = new FileInputStream(file_);
		}
			
		if(pojoCollMapFilePath_.toLowerCase().trim().startsWith("classpath:")) {
			String path_ = pojoCollMapFilePath_.trim().substring(10);
			File file_ = new File(MongoTweetConsumer.class.getClassLoader().getResource(path_).getFile());
			if(!file_.exists()) {
				throw new Exception("Invalid pojoCollMapFilePath provided");
			}
			pcmfis_ = new FileInputStream(file_);
		}
		else {
			File file_ = new File(pojoCollMapFilePath_);
			if(!file_.exists()) {
				throw new Exception("Invalid pojoCollMapFilePath provided");
			}
			pcmfis_ = new FileInputStream(file_);
		}
		func_ = new MongoFunctionImpl(new MongoConfiguration(mfis_, pcmfis_));
	}
}
