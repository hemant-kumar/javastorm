package com.javastorm.tweetcollector.producer;

import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

import com.javastorm.tweetcollector.config.TweetConfig;
import com.javastorm.tweetcollector.listener.DefaultStatusListener;

/**
 * The TweetProducer class is intended for starting up the tweet collection process.
 * It also handles filtering logic which is to be applied during tweet collection.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class TweetProducer
{
	TwitterStream twitterStream_;
	private boolean filterEnabled_;
	private String filterKeywords_;

	@Autowired
	public TweetProducer(TweetConfig tweetConfig) {
		twitterStream_ = tweetConfig.getTwitterStream();
	}

	public TwitterStream getTwitterStream() {
		return twitterStream_;
	}

	public void setTwitterStream(TwitterStream twitterStream) {
		this.twitterStream_ = twitterStream;
	}

	public boolean isFilterEnabled() {
		return filterEnabled_;
	}

	public void setFilterEnabled(boolean filterEnabled) {
		this.filterEnabled_ = filterEnabled;
	}

	public String getFilterKeywords() {
		return filterKeywords_;
	}

	public void setFilterKeywords(String filterKeywords) {
		this.filterKeywords_ = filterKeywords;
	}

	public void init() {
		twitterStream_.addListener(new DefaultStatusListener());
		if(filterEnabled_) {
			String[] words_ = filterKeywords_.split(",");
			FilterQuery filterQuery_ = new FilterQuery();
			filterQuery_.track(words_);
			twitterStream_.filter(filterQuery_);
		}
		else {
			twitterStream_.sample();
		}
	}
}
