package com.javastorm.tweetcollector.producer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.javastorm.tweetcollector.config.TweetConfig;

/**
 * The TweetSearch class is intended for searching of tweets of any user.
 * Max limit: 3200   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class TweetSearch 
{
	Twitter twitter_;

	@Autowired
	public TweetSearch(TweetConfig tweetConfig) {
		twitter_ = tweetConfig.getTwitter();
	}

	/**
	 * Gets the time line of the specified user id.
	 * @param userid
	 * @return
	 */
	public List<Status> search(long userid) {
		List<Status> list_ = new ArrayList<Status>();
		try {
			for(int i = 1; i <= 16; i++) {
				Paging paging = new Paging(i,200);
				List<Status> statuses = twitter_.getUserTimeline(userid, paging);
				list_.addAll(statuses);
				if(statuses.size()<200)
					break;
			}
		} 
		catch (TwitterException e) {
			e.printStackTrace();
		}
		return list_;
	}
}