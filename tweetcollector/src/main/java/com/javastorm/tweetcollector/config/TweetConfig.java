package com.javastorm.tweetcollector.config;

import org.springframework.beans.factory.InitializingBean;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The TweetConfig class is intended for building up the configuration using the provided tokens.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class TweetConfig implements InitializingBean 
{
	private String oAuthConsumerKey_;
	private String oAuthConsumerSecret_;
	private String oAuthAccessToken_;
	private String oAuthAccessTokenSecret_;
	private static long pollTimeOut_;
	private Configuration configuration_;

	public void setoAuthConsumerKey(String oAuthConsumerKey_) {
		this.oAuthConsumerKey_ = oAuthConsumerKey_;
	}

	public void setoAuthConsumerSecret(String oAuthConsumerSecret_) {
		this.oAuthConsumerSecret_ = oAuthConsumerSecret_;
	}

	public void setoAuthAccessToken(String oAuthAccessToken_) {
		this.oAuthAccessToken_ = oAuthAccessToken_;
	}

	public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret_) {
		this.oAuthAccessTokenSecret_ = oAuthAccessTokenSecret_;
	}

	public static long getPollTimeOut() {
		return pollTimeOut_ > 0 ? pollTimeOut_ : 1800;
	}

	public static void setPollTimeOut(long pollTimeOut_) {
		TweetConfig.pollTimeOut_ = pollTimeOut_;
	}

	public TwitterStream getTwitterStream() {
		return new TwitterStreamFactory(configuration_).getInstance();
	}

	public Twitter getTwitter() {
		return new TwitterFactory(configuration_).getInstance();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(oAuthConsumerKey_);
		cb.setOAuthConsumerSecret(oAuthConsumerSecret_);
		cb.setOAuthAccessToken(oAuthAccessToken_);
		cb.setOAuthAccessTokenSecret(oAuthAccessTokenSecret_);
		configuration_ = cb.build();
	}
}
