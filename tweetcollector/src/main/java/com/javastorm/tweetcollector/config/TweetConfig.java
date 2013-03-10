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

	public void setoAuthConsumerKey(String oAuthConsumerKey) {
		this.oAuthConsumerKey_ = oAuthConsumerKey;
	}

	public void setoAuthConsumerSecret(String oAuthConsumerSecret) {
		this.oAuthConsumerSecret_ = oAuthConsumerSecret;
	}

	public void setoAuthAccessToken(String oAuthAccessToken) {
		this.oAuthAccessToken_ = oAuthAccessToken;
	}

	public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		this.oAuthAccessTokenSecret_ = oAuthAccessTokenSecret;
	}

	public static long getPollTimeOut() {
		return pollTimeOut_ > 0 ? pollTimeOut_ : 1800;
	}

	public static void setPollTimeOut(long pollTimeOut) {
		TweetConfig.pollTimeOut_ = pollTimeOut;
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
