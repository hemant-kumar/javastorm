package com.javastorm.tweetcollector.send;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.javastorm.tweetcollector.config.TweetConfig;

/**
 * The SendMessage class is intended for sending of tweets & direct messages.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class SendMessage 
{
	private Logger LOGGER = Logger.getLogger(SendMessage.class.getName());
	private Twitter twitter;

	@Autowired
	public SendMessage(TweetConfig tweetConfig) {
		twitter = tweetConfig.getTwitter();
	}

	/**
	 * Send Direct Message
	 * @param user
	 * @param message
	 * @return
	 */
	public boolean directMessage(String user, String message) {
		if(!user.startsWith("@"))
			user = "@" + user;
		try {
			twitter.sendDirectMessage(user, message);
			LOGGER.info("Direct Message sent to " + user);
			return true;
		} 
		catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Send Tweet
	 * @param user
	 * @param message
	 * @return
	 */
	public boolean sendTweet(String user, String message) {
		if(!user.startsWith("@"))
			user = "@" + user;
		try {
			twitter.updateStatus(user + " " + message);
			LOGGER.info("Tweet sent to " + user);
			return true;
		} 
		catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
	}
}
