package com.javastorm.tweetcollector.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javastorm.tweetcollector.consumer.TweetConsumer;
import com.javastorm.tweetcollector.producer.TweetProducer;

/**
 * The TweetService class is intended for starting up the collection process.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class TweetService 
{
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "tweet_res/tweet.xml" });
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter no of consumers to run");
		String line = br.readLine();
		int no = Integer.parseInt(line);
		ExecutorService executor = Executors.newFixedThreadPool(no);
		TweetConsumer tweetConsumer = (TweetConsumer) context.getBean("tweetConsumer");
		for(int i=0; i<no; i++) {
			executor.submit(tweetConsumer);
		}
        TweetProducer tweetProducer = (TweetProducer) context.getBean("tweetProducer");
        tweetProducer.init();
        System.out.println("Press 0 to exit");
        line = br.readLine();
        while(true) {
        	if(line.equalsIgnoreCase("0"))
        		System.exit(0);
        	line = br.readLine();
        }
	}
}
