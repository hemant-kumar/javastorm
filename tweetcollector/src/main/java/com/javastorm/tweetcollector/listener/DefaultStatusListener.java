package com.javastorm.tweetcollector.listener;

import java.util.concurrent.BlockingQueue;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.javastorm.tweetcollector.queue.TweetQueue;

/**
 * The DefaultStatusListener class is intended for fetching tweets and dumping them into queue.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class DefaultStatusListener implements StatusListener 
{
	BlockingQueue<Status> queue_;
	
	public DefaultStatusListener() {
		queue_ = TweetQueue.getQueue();
	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatus(Status status) {
		try {
			queue_.put(status);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub
	}
}
