package com.javastorm.tweetcollector.model;

import java.util.Date;

import com.javastorm.mongoapi.mongo.domain.MongoEntity;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

/**
 * The MongoTweetStatus class is a POJO class capable of holding a Mongo DB compatible tweet.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 10/03/2013
 */
public class MongoTweetStatus extends MongoEntity
{
	private Date createdAt;
    private long id;
    private String text;
    private String source;
    private boolean isTruncated;
    private long inReplyToStatusId;
    private long inReplyToUserId;
    private boolean isFavorited;
    private String inReplyToScreenName;
    private GeoLocation geoLocation = null;
    private Place place = null;
    private long retweetCount;
    private boolean isPossiblySensitive;
    private long[] contributorsIDs;
	private Status retweetedStatus;
    private UserMentionEntity[] userMentionEntities;
    private URLEntity[] urlEntities;
    private HashtagEntity[] hashtagEntities;
    private MediaEntity[] mediaEntities;
    private long currentUserRetweetId = -1L;
    private User user = null;
    
    public MongoTweetStatus(Status status) {
    	this.setCreatedAt(status.getCreatedAt());
	    this.set_id(""+status.getId());
	    this.setId(status.getId());
	    this.setText(status.getText());
	    this.setSource(status.getSource());
	    this.setTruncated(status.isTruncated());
	    this.setInReplyToStatusId(status.getInReplyToStatusId());
	    this.setInReplyToUserId(status.getInReplyToUserId());
	    this.setFavorited(status.isFavorited());
	    this.setInReplyToScreenName(status.getInReplyToScreenName());
	    this.setGeoLocation(status.getGeoLocation());
	    this.setPlace(status.getPlace());
	    this.setRetweetCount(status.getRetweetCount());
	    this.setPossiblySensitive(status.isPossiblySensitive());
	    this.setContributorsIDs(status.getContributors());
	    this.setRetweetedStatus(status.getRetweetedStatus());
	    this.setUserMentionEntities(status.getUserMentionEntities());
	    this.setUrlEntities(status.getURLEntities());
	    this.setHashtagEntities(status.getHashtagEntities());
	    this.setMediaEntities(status.getMediaEntities());
	    this.setCurrentUserRetweetId(status.getCurrentUserRetweetId());
	    this.setUser(status.getUser());
	}

    public Date getCreatedAt() {
		return createdAt;
	}

    public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
    public long getId() {
		return id;
	}
	
    public void setId(long id) {
		this.id = id;
	}

    public String getText() {
		return text;
	}
	
    public void setText(String text) {
		this.text = text;
	}
	
    public String getSource() {
		return source;
	}
	
    public void setSource(String source) {
		this.source = source;
	}
	
    public boolean isTruncated() {
		return isTruncated;
	}
	
    public void setTruncated(boolean isTruncated) {
		this.isTruncated = isTruncated;
	}
	
    public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	
    public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	
    public long getInReplyToUserId() {
		return inReplyToUserId;
	}
	
    public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}
	
    public boolean isFavorited() {
		return isFavorited;
	}
	
    public void setFavorited(boolean isFavorited) {
		this.isFavorited = isFavorited;
	}
	
    public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}
	
    public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}
	
    public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	
    public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	
    public Place getPlace() {
		return place;
	}
	
    public void setPlace(Place place) {
		this.place = place;
	}
	
    public long getRetweetCount() {
		return retweetCount;
	}
	
    public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}
	
    public boolean isPossiblySensitive() {
		return isPossiblySensitive;
	}
	
    public void setPossiblySensitive(boolean isPossiblySensitive) {
		this.isPossiblySensitive = isPossiblySensitive;
	}
	
    public long[] getContributorsIDs() {
		return contributorsIDs;
	}
	
    public void setContributorsIDs(long[] contributorsIDs) {
		this.contributorsIDs = contributorsIDs;
	}
	
    public Status getRetweetedStatus() {
		return retweetedStatus;
	}
	
    public void setRetweetedStatus(Status retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}
	
    public UserMentionEntity[] getUserMentionEntities() {
		return userMentionEntities;
	}
	
    public void setUserMentionEntities(UserMentionEntity[] userMentionEntities) {
		this.userMentionEntities = userMentionEntities;
	}
	
    public URLEntity[] getUrlEntities() {
		return urlEntities;
	}
	
    public void setUrlEntities(URLEntity[] urlEntities) {
		this.urlEntities = urlEntities;
	}
	
    public HashtagEntity[] getHashtagEntities() {
		return hashtagEntities;
	}
	
    public void setHashtagEntities(HashtagEntity[] hashtagEntities) {
		this.hashtagEntities = hashtagEntities;
	}
	
    public MediaEntity[] getMediaEntities() {
		return mediaEntities;
	}
	
    public void setMediaEntities(MediaEntity[] mediaEntities) {
		this.mediaEntities = mediaEntities;
	}
	
    public long getCurrentUserRetweetId() {
		return currentUserRetweetId;
	}
	
    public void setCurrentUserRetweetId(long currentUserRetweetId) {
		this.currentUserRetweetId = currentUserRetweetId;
	}
	
    public User getUser() {
		return user;
	}
	
    public void setUser(User user) {
		this.user = user;
	}
}
