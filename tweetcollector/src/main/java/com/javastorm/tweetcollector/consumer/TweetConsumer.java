package com.javastorm.tweetcollector.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import twitter4j.Status;

import com.javastorm.tweetcollector.config.TweetConfig;
import com.javastorm.tweetcollector.queue.TweetQueue;

/**
 * The TweetConsumer class is intended for consuming up the tweet from the queue and passing up to 
 * consume method which need to be defined by the class extending this class.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public abstract class TweetConsumer implements Runnable
{
	BlockingQueue<Status> queue_ = TweetQueue.getQueue();
	@Override
	public void run() {
	    while(true) {
			try {
				Status status_ = queue_.poll(TweetConfig.getPollTimeOut(), TimeUnit.SECONDS);
				consume(status_);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    }
	}

	public abstract void consume(Status status) throws Exception;
}
