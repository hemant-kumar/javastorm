package com.javastorm.tweetcollector.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javastorm.tweetcollector.send.SendMessage;

public class SendMessageTest 
{
	public static void main(String[] args) throws Exception {
		ApplicationContext context =	new ClassPathXmlApplicationContext(new String[] { "tweet_res/tweet.xml" });
		SendMessage msg = (SendMessage) context.getBean("sendMessage");
		msg.sendTweet("hemantsingh1309", "Follow me on twitter");
	}
}
