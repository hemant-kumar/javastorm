package com.javastorm.tweetcollector.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import twitter4j.Status;

/**
 * The TweetQueue class is intended for providing a queue for holding the tweets.
 * Max limit: 3200 .   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public abstract class TweetQueue 
{
  private static BlockingQueue<Status> queue_ = new LinkedBlockingQueue<Status>(2048);

  public static BlockingQueue<Status> getQueue() {
    return queue_;
  }

}
